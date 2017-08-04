package com.timeyang.jkes.core.elasticsearch.indices;

import com.timeyang.jkes.core.elasticsearch.EsRestClient;
import com.timeyang.jkes.core.support.Config;
import com.timeyang.jkes.core.support.JkesProperties;
import com.timeyang.jkes.entity.PersonGroup;
import com.timeyang.jkes.core.support.DefaultJkesPropertiesImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author chaokunyang
 */
public class IndicesAdminClientTest {
    private EsRestClient esRestClient;

    private IndicesAdminClient indicesAdminClient;

    @Before
    public void setUp() {
        JkesProperties jkesProperties = new DefaultJkesPropertiesImpl() {
            @Override
            public String getEsBootstrapServers() {
                return "http://e1:9200,http://e2:9200,http://e3:9200";
            }
        };
        Config.setJkesProperties(jkesProperties);

        esRestClient = new EsRestClient(jkesProperties);
        indicesAdminClient = new IndicesAdminClient(esRestClient, jkesProperties);
    }

    @Test
    public void start() throws Exception {
        indicesAdminClient.init();
    }

    @Test
    public void createIndex() throws Exception {
        if(!indicesAdminClient.checkExists("my_index"))
            indicesAdminClient.createIndex(PersonGroup.class);
    }

    @Test
    public void checkExists() throws Exception {
        indicesAdminClient.checkExists("my_index");
    }

    @After
    public void destroy() {
        esRestClient.close();
    }

}