package cn.yuyao.spi.impl;

import cn.yuyao.spi.MyService;

public class MyServiceB implements MyService {
    @Override
    public void print(String info) {
        System.out.println(MyServiceB.class.getName() + " print " + info);
    }
}
