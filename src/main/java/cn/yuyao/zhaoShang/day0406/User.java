package cn.yuyao.zhaoShang.day0406;

public class User {

    // 将 "张三" 赋值给 name
    @Value("张三")
    private String name;

    // 未标记 @Value 注解，故不需要赋值
    private String gender;

    // 将 20 赋值给 age
    @Value("20")
    private Integer age;
}
