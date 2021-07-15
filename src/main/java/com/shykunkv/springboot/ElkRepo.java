package com.shykunkv.springboot;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElkRepo extends ElasticsearchRepository<Sentence, String> {
    List<Sentence> findByValue(String value);
}
