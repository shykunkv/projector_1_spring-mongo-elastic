package com.shykunkv.springboot;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("test")
public class RandomNumber {
    public String id;
    public int value;

    public RandomNumber(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
