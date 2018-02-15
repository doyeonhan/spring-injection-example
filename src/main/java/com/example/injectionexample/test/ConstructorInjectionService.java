package com.example.injectionexample.test;

import com.example.injectionexample.service.TestServiceA;
import com.example.injectionexample.service.TestServiceB;
import com.example.injectionexample.service.TestServiceC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.constructor.Construct;

import javax.annotation.PostConstruct;

@Component
public class ConstructorInjectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstructorInjectionService.class);

    private final TestServiceA testServiceA;
    private final TestServiceB testServiceB;
    private final TestServiceC testServiceC;

    @Autowired
    public ConstructorInjectionService(TestServiceA testServiceA, TestServiceB testServiceB, TestServiceC testServiceC) {
        this.testServiceA = testServiceA;
        this.testServiceB = testServiceB;
        this.testServiceC = testServiceC;
    }

    @PostConstruct
    public void printTestLog() {
        System.out.println(this.getClass().getName() +" : "+ testServiceA.getClassName());
        System.out.println(this.getClass().getName() +" : "+ testServiceB.getClassName());
        System.out.println(this.getClass().getName() +" : "+ testServiceC.getClassName());
    }
}
