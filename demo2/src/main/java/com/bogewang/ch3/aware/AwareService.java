package com.bogewang.ch3.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by bogewang on 2017/7/10.
 */
@Service
public class AwareService implements BeanNameAware,ResourceLoaderAware {        //1 实现BeanName,ResourceLoaderAware 接口,获得Bean名称和资源加载服务
    private String beanName;
    private ResourceLoader loader;
    @Override
    public void setBeanName(String s) {     //3 实现BeanNameAware
        this.beanName = s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {      //2 实现ResourceLoaderAware需要重写setResourceLoader;
        this.loader = resourceLoader;
    }

    public void outputResult(){
        System.out.println("Bean的名称为:" + beanName);
        Resource resource = loader.getResource("classpath:com/bogewang/ch3/aware/test.txt");
        try {
            System.out.println("ResourceLoader加载的文件内容为:" + IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
