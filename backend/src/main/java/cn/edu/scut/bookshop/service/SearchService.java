package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.util.BusinessException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private RestHighLevelClient elasticsearchClient;
    
    @Value("${elasticsearch-config.index}")
    private String elasticsearchIndex;
    
    private final String elasticsearchDocType = "_doc";
    
    private static final String[] SEARCH_INCLUDED_FIELDS = {"*"};
    private static final String[] SEARCH_EXCLUDED_FIELDS = {"@timestamp", "@version"};
    
    public Result search(String keyword, int size)
    {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (StringUtils.isEmpty(keyword))
            defaultSearch(searchSourceBuilder);
        else if (keyword.matches("\\s*\\d+\\s*"))
            searchISBN(searchSourceBuilder, keyword);
        else hybridSearch(searchSourceBuilder, keyword);
        
        searchSourceBuilder.size(size);
        searchSourceBuilder.fetchSource(SEARCH_INCLUDED_FIELDS, SEARCH_EXCLUDED_FIELDS);
        
//        SearchRequest searchRequest = new SearchRequest(elasticsearchIndex, "books");
        SearchRequest searchRequest = new SearchRequest(elasticsearchIndex);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse;
        try
        {
            searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            throw new BusinessException(ErrorCode.SERVER_ERROR_ELASTICSEARCH, ex.getMessage());
        }
        
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Map<String, Object>> suggestedRecords = new LinkedList<>();
        for (SearchHit searchHit : searchHits)
        {
            Map<String, Object> record = searchHit.getSourceAsMap();
            record.put("score", searchHit.getScore());
            suggestedRecords.add(record);
        }
        
        return Result.OK().data(suggestedRecords).build();
    }
    
    public void defaultSearch(SearchSourceBuilder searchSourceBuilder)
    {
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.sort("douban_score", SortOrder.DESC);
    }
    
    public void searchISBN(SearchSourceBuilder searchSourceBuilder, String keyword)
    {
        searchSourceBuilder.query(QueryBuilders.prefixQuery("ISBN", keyword));
    }
    
    public void hybridSearch(SearchSourceBuilder searchSourceBuilder, String keyword)
    {
        Map<String, Float> fields = new HashMap<>();
        fields.put("title.cn", 3f);
        fields.put("author.cn", 3f);
        fields.put("name.cn", 5f);
        searchSourceBuilder.query(
                QueryBuilders.multiMatchQuery(keyword,
                        "title", "author", "name")
                        .fields(fields)
                        .type(MultiMatchQueryBuilder.Type.MOST_FIELDS));
    }
    
}
