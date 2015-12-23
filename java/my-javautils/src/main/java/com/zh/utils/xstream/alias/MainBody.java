package com.zh.utils.xstream.alias;

import java.util.ArrayList; 
import java.util.List; 

/** 
* Created by IntelliJ IDEA.<br> 
* <b>User</b>: leizhimin<br> 
* <b>Date</b>: 2008-5-21 10:48:43<br> 
* <b>Note</b>: 主体信息 
*/ 
public class MainBody { 
    private int id; 
    private String name; 
    private List<Investor> investorList = new ArrayList<Investor>(); 

    public MainBody(int id, String name, List<Investor> investorList) { 
        this.id = id; 
        this.name = name; 
        this.investorList = investorList; 
    } 


    public String toString() { 
        return "MainBody{" + 
                "id=" + id + 
                ", name='" + name + '\'' + 
                ", investorList=" + investorList + 
                '}'; 
    } 
}