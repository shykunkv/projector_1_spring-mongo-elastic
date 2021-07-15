package com.shykunkv.springboot;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "sentences")
public class Sentence {

    @Id
    public String id;

    public String value;

    public Sentence(String value) {
        this.value = value;
    }
}
