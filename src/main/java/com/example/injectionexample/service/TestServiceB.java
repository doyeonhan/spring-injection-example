package com.example.injectionexample.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceB {

    public String getClassName() {
        return this.getClass().getName();
    }
}
