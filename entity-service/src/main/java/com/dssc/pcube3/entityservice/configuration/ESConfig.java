package com.dssc.pcube3.entityservice.configuration;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ESConfig {

    @Value("${spring.elasticsearch.rest.uris}")
    private List<String> uris;

    // 暂时无密码，不需要鉴权
//    @Value("${spring.elasticsearch.rest.password}")
//    private String userName;
//
//    @Value("${spring.elasticsearch.rest.username}")
//    private String password;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost[] httpHosts = createHosts();
        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts)
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    //credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(userName,password));
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                });
        return new RestHighLevelClient(restClientBuilder);
    }

    // 支持ES分布式
    private HttpHost[] createHosts() {
        HttpHost[] httpHosts = new HttpHost[uris.size()];
        for (int i = 0; i < uris.size(); i++) {
            String hostStr = uris.get(i);
            String[] host = hostStr.split(":");
            httpHosts[i] = new HttpHost(StringUtils.trim(host[0]),Integer.parseInt(StringUtils.trim(host[1])));
        }
        return httpHosts;
    }
}
