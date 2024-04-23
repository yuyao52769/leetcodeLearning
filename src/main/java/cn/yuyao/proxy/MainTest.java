package cn.yuyao.proxy;

import java.lang.reflect.Proxy;

public class MainTest {

    public static void main(String[] args) {
        InterUser user = (InterUser) Proxy.newProxyInstance(MainTest.class.getClassLoader(), new Class[]{InterUser.class}, new ProxyHandler(new ReallyUser()));
        String hello = user.hello();
        System.out.println(hello);
    }
}
