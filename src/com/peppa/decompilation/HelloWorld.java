package com.peppa.decompilation;


// javap -c -v .\HelloWorld.class > output.txt

public class HelloWorld {
    public static void main(String[] args) {

        System.out.println("Hello World");
    }

    private static void calc(){
        Object object = new Object();
        System.out.println(object);
    }
}
