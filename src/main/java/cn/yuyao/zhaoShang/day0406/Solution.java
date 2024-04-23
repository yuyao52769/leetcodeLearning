package cn.yuyao.zhaoShang.day0406;

import java.lang.reflect.Field;

public class Solution {

    public static void main(String[] args) throws Exception {
        User user = Solution.injectValue(User.class);
        System.out.println(user);
    }


    /**
     * 将 clazz 类型的类中标有 @Value 注解的成员变量进行赋值，并返回该类的对象
     *
     * @param clazz 需要被赋值的类的Class类型
     * @param <T> 返回对象的范型
     * @return 赋值后的对象
     */
    public static <T> T injectValue(Class<T> clazz) throws Exception {
        T t = clazz.getConstructor(null).newInstance(null);
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                String value = annotation.value();
                if (value != null) {
                    Class<?> type = field.getType();
                    if (type.equals(String.class)){
                        field.set(t, value);
                    } else if (type.equals(Integer.class)) {
                        field.set(t, Integer.valueOf(value));
                    }
                }
            }
        }

        return t;
    }
}


