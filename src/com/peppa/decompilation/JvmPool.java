package com.peppa.decompilation;

import org.openjdk.jol.info.ClassLayout;

public class JvmPool {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.setName("peppa");
        worker.setAge(18);
        worker.setPassWord("123456");
        System.out.println(ClassLayout.parseInstance(worker).toPrintable());
    }
}
