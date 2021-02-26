package com.bogewang.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by bogewang on 2017/7/6.
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
