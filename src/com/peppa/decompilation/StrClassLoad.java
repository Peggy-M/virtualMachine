package com.peppa.decompilation;



public class StrClassLoad {
    public static void main(String[] args) {

        //AppClassLoader
        System.out.println(new Worker().getClass().getClassLoader());

        //ExtClassLoader
        System.out.println(new Worker().getClass().getClassLoader().getParent());

        //Bootstrap ClassLoader
        System.out.println(new Worker().getClass().getClassLoader().getParent().getParent());
        System.out.println(new String().getClass().getClassLoader());
    }
}
