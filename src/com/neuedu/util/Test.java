package com.neuedu.util;

public class Test {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            System.out.print("a");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
