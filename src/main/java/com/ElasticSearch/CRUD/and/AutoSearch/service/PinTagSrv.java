package com.ElasticSearch.CRUD.and.AutoSearch.service;

import com.ElasticSearch.CRUD.and.AutoSearch.model.PinTags;

import java.util.Iterator;
import java.util.List;

public interface PinTagSrv {

     PinTags insertPin(PinTags pinTags) ;

     List<PinTags> getAllPins();

     PinTags getPin(Integer id);

     boolean deletePin(Integer id);

    List<PinTags> searchPins(String KeyWord);

}
