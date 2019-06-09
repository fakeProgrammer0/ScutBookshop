package cn.edu.scut.bookshop;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@SpringBootApplication
public class BookshopApplication
{
    @Value("${elasticsearch-config.host}")
    private String elasticsearchHost;
    
    @Value("${elasticsearch-config.port}")
    private int elasticsearchPort;
    
    private Logger logger = LoggerFactory.getLogger(BookshopApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }
    
    @Bean
    public RestHighLevelClient elasticsearchClient()
    {
        logger.debug("elasticsearch host: " + elasticsearchHost + ";port: " + elasticsearchPort);
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(elasticsearchHost, elasticsearchPort, "http")));
    }
}
