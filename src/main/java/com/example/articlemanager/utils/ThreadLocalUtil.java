package com.example.articlemanager.utils;

public class ThreadLocalUtil {


    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();


    //根據鍵獲取值
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }

    //存儲鍵值對
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }
    //清除ThreadLocal
    public static void remove(){
        THREAD_LOCAL.remove();
    }

}
