package com.example.injectionexample.test;

import com.example.injectionexample.service.TestServiceA;
import com.example.injectionexample.service.TestServiceB;
import com.example.injectionexample.service.TestServiceC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FieldInjectionService {

    @Autowired
    private TestServiceA testServiceA;

    @Autowired
    private TestServiceB testServiceB;

    @Autowired
    private TestServiceC testServiceC;

    @PostConstruct
    public void printTestLog() {
        System.out.println(this.getClass().getName() +" : "+ testServiceA.getClassName());
        System.out.println(this.getClass().getName() +" : "+ testServiceB.getClassName());
        System.out.println(this.getClass().getName() +" : "+ testServiceC.getClassName());
    }
}
