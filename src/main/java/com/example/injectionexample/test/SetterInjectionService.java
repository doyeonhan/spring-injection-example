package com.example.injectionexample.test;

import com.example.injectionexample.service.TestServiceA;
import com.example.injectionexample.service.TestServiceB;
import com.example.injectionexample.service.TestServiceC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SetterInjectionService {

    private TestServiceA testServiceA;
    private TestServiceB testServiceB;
    private TestServiceC testServiceC;

    @Autowired
    public void setTestServiceA(TestServiceA testServiceA) {
        this.testServiceA = testServiceA;
    }

    @Autowired
    public void setTestServiceB(TestServiceB testServiceB) {
        this.testServiceB = testServiceB;
    }

    @Autowired
    public void setTestServiceC(TestServiceC testServiceC) {
        this.testServiceC = testServiceC;
    }

    @PostConstruct
    public void printTestLog() {
        System.out.println(this.getClass().getName() +" : "+ testServiceA.getClassName());
        System.out.println(this.getClass().getName() +" : "+ testServiceB.getClassName());
        System.out.println(this.getClass().getName() +" : "+ testServiceC.getClassName());
    }
}
