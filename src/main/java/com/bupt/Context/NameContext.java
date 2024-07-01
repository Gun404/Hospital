package com.bupt.Context;

/**
 * 线程变量记录当前操作者的姓名
 */
public class NameContext {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentName(String name) {
        threadLocal.set(name);
    }

    public static String getCurrentName() {
        return threadLocal.get();
    }

    public static void removeCurrentName() {
        threadLocal.remove();
    }

}
