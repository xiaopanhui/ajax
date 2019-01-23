package com.example.ajax;

import sun.applet.Main;

import java.util.*;

public class TEs1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for(int i=0;i<10;i++){
            list.add(random.nextInt(100));
        }

//        Collections.sort(list,new Comparator<Integer>(){
//            public int compare(Integer o1, Integer o2){
//                return o2-o1;
//            }
//        });
        		Collections.sort(list,(o1,o2) -> o1-o2);
    }







}
