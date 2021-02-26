package com.bogewang.ch3.conditional;

/**
 * Created by bogewang on 2017/7/10.
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
