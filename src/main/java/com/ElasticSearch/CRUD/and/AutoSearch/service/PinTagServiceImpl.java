package com.ElasticSearch.CRUD.and.AutoSearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.ElasticSearch.CRUD.and.AutoSearch.model.PinTags;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PinTagServiceImpl implements PinTagSrv {

    private final ElasticsearchClient elasticsearchClient;

    public PinTagServiceImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @Override
    public PinTags insertPin(PinTags pinTags) {
        try {
            // Auto-generate ID if not provided
            if (pinTags.getId() == null) {
                pinTags.setId((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
            }
            elasticsearchClient.index(i -> i
                    .index("pintags")
                    .id(pinTags.getId().toString())
                    .document(pinTags)
            );
            return pinTags;
        } catch (Exception e) {
            throw new RuntimeException("Insert failed " + e.getMessage());
        }
    }


    @Override
    public List<PinTags> getAllPins() {
        try {
            var response = elasticsearchClient.search(s -> s
                            .index("pintags")
                            .query(q -> q.matchAll(m -> m)),
                    PinTags.class
            );

            return response.hits().hits()
                    .stream()
                    .map(hit -> hit.source())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Fetch failed " + e.getMessage());
        }
    }

    @Override
    public PinTags getPin(Integer id) {
        try {
            var response = elasticsearchClient.get(g -> g
                            .index("pintags")
                            .id(id.toString()),
                    PinTags.class
            );

            if (response.found()) {
                return response.source();
            }

            throw new RuntimeException("Pin not found");

        } catch (Exception e) {
            throw new RuntimeException("Get failed " + e.getMessage());
        }
    }

    @Override
    public boolean deletePin(Integer id) {
        try {
            elasticsearchClient.delete(d -> d
                    .index("pintags")
                    .id(id.toString())
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<PinTags> searchPins(String keyword) {
        try {
            var response = elasticsearchClient.search(s -> s
                            .index("pintags")
                            .query(q -> q
                                    .multiMatch(m -> m
                                            .fields("title", "tags")
                                            .query(keyword)
                                    )
                            ),
                    PinTags.class
            );

            return response.hits().hits()
                    .stream()
                    .map(hit -> hit.source())
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Search Failed " + e.getMessage());
        }
    }
}