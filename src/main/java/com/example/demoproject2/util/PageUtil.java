package com.example.demoproject2.util;

public class PageUtil {
    public static int findOffset(Integer page, Integer pageSize) {
        return pageSize * page;
    }
}
