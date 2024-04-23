package cn.yuyao.proxy;

public class ReallyUser implements InterUser{
    @Override
    public String hello() {
        System.out.println("真实对象");
        return "really";
    }
}
