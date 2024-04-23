package cn.yuyao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private InterUser user;

    public ProxyHandler(InterUser user) {
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> aClass = proxy.getClass();
        System.out.println(aClass);
        method.invoke(user, args);
        System.out.println("hello");
        return "123";
    }
}
