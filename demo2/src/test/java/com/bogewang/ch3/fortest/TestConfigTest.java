package com.bogewang.ch3.fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by bogewang on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //1
@ContextConfiguration(classes = {TestConfig.class})     //2 指定配置类
@ActiveProfiles("dev")     //3 指定profile 模式
public class TestConfigTest {
    @Autowired      //4 注入需要加载的bean;
    private TestBean testBean;
    @Test
    public void testProdMod() throws Exception {
        String expected = "dev";
        String actual = testBean.getContent();
        Assert.assertEquals(expected,actual);
    }

}