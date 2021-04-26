package com.smart.life.config;

public final class BranchContextHolder {

    private BranchContextHolder(){
        throw new IllegalStateException("Branch Context Holder");
    }

    private static ThreadLocal<String> context = new InheritableThreadLocal<>();

    public static String get(){
        return context.get();
    }

    public static void set(String branchId){
        context.set(branchId);
    }

    public static void clear(){
        context.remove();
    }
}
