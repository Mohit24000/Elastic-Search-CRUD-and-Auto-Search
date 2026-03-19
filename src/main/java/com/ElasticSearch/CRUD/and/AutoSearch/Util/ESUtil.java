package com.ElasticSearch.CRUD.and.AutoSearch.Util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ESUtil {

    public static MatchQuery createAutoSuggestMatchQuery(String partialName){

            val autoSuggestQuery = new MatchQuery.Builder();
            return autoSuggestQuery.field("tags").query(partialName).analyzer("standard").build();

    }

    public static Supplier<Query> createSupplierAutoSuggest(String partialName) {
        return () -> Query.of(q -> q
                .matchPhrasePrefix(m -> m
                        .field("tags")
                        .query(partialName)
                )
        );
    }

}
