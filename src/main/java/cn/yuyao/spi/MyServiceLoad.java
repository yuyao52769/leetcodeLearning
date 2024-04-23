package cn.yuyao.spi;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

public class MyServiceLoad {

    /**
     * 使用SPI机制加载所有的Class
     */
    public static <S> ServiceLoader<S> loadAll(final Class<S> clazz) {
        return ServiceLoader.load(clazz);
    }

    public static void main(String[] args) {
        ServiceLoader<MyService> loader = MyServiceLoad.loadAll(MyService.class);
        Iterator<MyService> iterator = loader.iterator();
        while (iterator.hasNext()) {
            MyService next = iterator.next();
            next.print("123");
        }
         StreamSupport.stream(loader.spliterator(), false).forEach(s -> s.print("Hello World"));
    }


}
