package com.example.injectionexample.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceA {

    public String getClassName() {
        return this.getClass().getName();
    }
}
