package com.bogewang.ch3.conditional;

/**
 * Created by bogewang on 2017/7/10.
 */
public class LinuxListServiceImpl implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
