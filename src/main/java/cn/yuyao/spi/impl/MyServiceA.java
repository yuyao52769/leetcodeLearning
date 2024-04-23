package cn.yuyao.spi.impl;

import cn.yuyao.spi.MyService;

public class MyServiceA implements MyService {
    @Override
    public void print(String info) {
        System.out.println(MyServiceA.class.getName() + " print " + info);
    }
}
