package com.ElasticSearch.CRUD.and.AutoSearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PinTags {

    private Integer  Id;
    private  String title;
    private List<String> tags;
    private Integer qty;
}
