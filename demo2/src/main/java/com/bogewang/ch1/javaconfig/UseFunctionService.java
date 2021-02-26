package com.bogewang.ch1.javaconfig;

/**
 * Created by bogewang on 2017/7/6.
 */
public class UseFunctionService {
    private FunctionService functionService;

    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}
