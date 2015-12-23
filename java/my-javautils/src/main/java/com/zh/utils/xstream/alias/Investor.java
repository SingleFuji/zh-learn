package com.zh.utils.xstream.alias;

/** 
* Created by IntelliJ IDEA.<br> 
* <b>User</b>: leizhimin<br> 
* <b>Date</b>: 2008-5-21 10:51:08<br> 
* <b>Note</b>: 投资人 
*/ 
public class Investor { 
    private int id; 
    private String name; 
    private int age; 

    public Investor(int id, String name) { 
        this.id = id; 
        this.name = name; 
    } 

    public Investor(int id, String name, int age) { 
        this.id = id; 
        this.name = name; 
        this.age = age; 
    } 

    public String toString() { 
        return "Investor{" + 
                "id=" + id + 
                ", name='" + name + '\'' + 
                ", age='" + age + '\'' + 
                '}'; 
    } 
}