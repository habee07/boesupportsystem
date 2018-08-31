package jetbrains;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Laila on 18/08/2018.
 */


public class Persistence {

    private java.util.ArrayList<java.lang.String> myArrayList;

    public Persistence(){
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