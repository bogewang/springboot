package com.bogewang.ch2.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by bogewang on 2017/7/6.
 */
@Service
public class DemoService {
    @Value("其他类的属性")    //1 此处注入普通的字符串;
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
