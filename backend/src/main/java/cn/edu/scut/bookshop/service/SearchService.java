package cn.edu.scut.bookshop.service;

import cn.edu.scut.bookshop.domain.ErrorCode;
import cn.edu.scut.bookshop.domain.Result;
import cn.edu.scut.bookshop.util.BusinessException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    
    //    private final String elasticsearchDocType = "_doc";
    
    private static final String[] SEARCH_INCLUDED_FIELDS = {"*"};
    private static final String[] SEARCH_EXCLUDED_FIELDS = {"@timestamp", "@version"};
    
    private Result processSearchRequest(SearchRequest searchRequest)
    {
        try
        {
            SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            List<Map<String, Object>> records = new LinkedList<>();
            for (SearchHit searchHit : searchHits)
            {
                Map<String, Object> record = searchHit.getSourceAsMap();
                record.put("score", searchHit.getScore());
                records.add(record);
            }
            return Result.OK().data(records).build();
        } catch (IOException ex)
        {
            ex.printStackTrace();
            throw new BusinessException(ErrorCode.SERVER_ERROR_ELASTICSEARCH, ex.getMessage());
        }
    }
    
    public Result hybridSearch(String keyword, int size)
    {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.fetchSource(SEARCH_INCLUDED_FIELDS, SEARCH_EXCLUDED_FIELDS);
        
        if (StringUtils.isEmpty(keyword))
        {
            defaultSearchBuild(searchRequest, searchSourceBuilder);
        } else if (keyword.matches("\\s*\\d+\\s*"))
            searchISBNBuild(searchRequest, searchSourceBuilder, keyword);
        else hybridSearchBuild(searchRequest, searchSourceBuilder, keyword);
        
        return processSearchRequest(searchRequest);
    }
    
    public Result searchBooks(String keyword, int size)
    {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.fetchSource(SEARCH_INCLUDED_FIELDS, SEARCH_EXCLUDED_FIELDS);
        searchBooksBuild(searchRequest, searchSourceBuilder, keyword);
        return processSearchRequest(searchRequest);
    }
    
    public void searchBooksBuild(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, String keyword)
    {
        searchRequest.indices("books");
        if (StringUtils.isEmpty(keyword))
        {
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchSourceBuilder.sort("douban_score", SortOrder.DESC);
        } else
        {
            Map<String, Float> fields = new HashMap<>();
            fields.put("title.cn", 3f);
            fields.put("author.cn", 3f);
            searchSourceBuilder.query(
                    QueryBuilders.multiMatchQuery(keyword,
                            "title", "author")
                            .fields(fields)
                            .type(MultiMatchQueryBuilder.Type.MOST_FIELDS));
        }
        searchRequest.source(searchSourceBuilder);
    }
    
    public Result searchISBN(String keyword, int size)
    {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.fetchSource(SEARCH_INCLUDED_FIELDS, SEARCH_EXCLUDED_FIELDS);
        searchISBNBuild(searchRequest, searchSourceBuilder, keyword);
        return processSearchRequest(searchRequest);
    }
    
    public Result searchAuthors(String keyword, int size)
    {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.fetchSource(SEARCH_INCLUDED_FIELDS, SEARCH_EXCLUDED_FIELDS);
        searchAuthorsBuild(searchRequest, searchSourceBuilder, keyword);
        return processSearchRequest(searchRequest);
    }
    
    private void searchAuthorsBuild(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, String keyword)
    {
        searchRequest.indices("authors");
        if (StringUtils.isEmpty(keyword))
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        else
        {
            Map<String, Float> fields = new HashMap<>();
            fields.put("name.cn", 3f);
            searchSourceBuilder.query(
                    QueryBuilders.multiMatchQuery(keyword,
                            "name")
                            .fields(fields)
                            .type(MultiMatchQueryBuilder.Type.MOST_FIELDS));
        }
        searchRequest.source(searchSourceBuilder);
    }
    
    private void defaultSearchBuild(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder)
    {
        searchRequest.indices("books");
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.sort("douban_score", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
    }
    
    private void searchISBNBuild(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, String keyword)
    {
        searchRequest.indices("books");
        if (StringUtils.isEmpty(keyword))
        {
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchSourceBuilder.sort("douban_score", SortOrder.DESC);
        } else
            searchSourceBuilder.query(QueryBuilders.prefixQuery("ISBN", keyword));
        searchRequest.source(searchSourceBuilder);
    }
    
    private void hybridSearchBuild(SearchRequest searchRequest, SearchSourceBuilder searchSourceBuilder, String keyword)
    {
        searchRequest.indices("books", "authors");
        Map<String, Float> fields = new HashMap<>();
        fields.put("title.cn", 3f);
        fields.put("author.cn", 3f);
        fields.put("name.cn", 5f);
        searchSourceBuilder.query(
                QueryBuilders.multiMatchQuery(keyword,
                        "title", "author", "name")
                        .fields(fields)
                        .type(MultiMatchQueryBuilder.Type.MOST_FIELDS));
        searchRequest.source(searchSourceBuilder);
    }
    
}
