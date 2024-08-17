package com.arthas.out.memory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 内存泄漏：一个对象不再使用，但GC ROOT仍然引用链接，对象永远不会被回收
 *
 * @author wangxing
 * @date 2024-07-22 12:47
 */
public class Outer {

    private final byte[] bytes = new byte[1024 * 100];
    private static final String name = "测试";

    public static void main(String[] args) throws IOException {
        System.in.read();

        int count = 0;
        ArrayList<Inner> list = new ArrayList<>();
        while (true) {
            System.out.println(++count);
            list.add(new Outer().new Inner());
        }
    }

    class Inner {
        private final String name;

        public Inner() {
            this.name = Outer.name;
        }
    }

}
