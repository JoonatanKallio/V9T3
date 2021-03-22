package com.example.v9t3;

import java.util.ArrayList;

public class listOfData {
    ArrayList<data> arrayList = new ArrayList<data>();

    public void appendToList(data x) {
        arrayList.add(x);
    }

    public void print(){
        for (data x : arrayList) {
            System.out.println(x.id + " " + x.name);
        }
    }

    public int getLength(){
        return arrayList.size();
    }

    public String getName(int x) {
        return arrayList.get(x).name;
    }

    public String getID(String name) {
        int i = 0;
        String returnID = null;
        for (data x : arrayList) {
            if (name.equals(arrayList.get(i).name)) {
                returnID = arrayList.get(i).id;
            }
            i +=1;
        }
        return returnID;
    }
}
