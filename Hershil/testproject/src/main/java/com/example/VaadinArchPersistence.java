package com.example;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by habee on 2018/08/17.
 */
public class VaadinArchPersistence {

    private ArrayList myArrayList;
    public VaadinArchPersistence(){
        myArrayList = new ArrayList();
    }
    public void addItem(String item){
        if(!item.isEmpty()){
            myArrayList.add(item);
        }
    }
    public String getItems(){
        StringBuilder sb = new StringBuilder();
        Iterator myIterator = myArrayList.iterator();
        while(myIterator.hasNext()){
            String element = (String) myIterator.next();
            sb.append(element+" ");
        }
        return sb.toString();
    }
}
