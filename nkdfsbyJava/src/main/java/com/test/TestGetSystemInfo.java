package com.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestGetSystemInfo {
    public static void main(String[] args) {
        Properties prop = System.getProperties();
        Set<Map.Entry<Object, Object>> entrySet = prop.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<Object, Object> entry = iterator.next();
            System.out.println(entry.getKey()+" == "+entry.getValue());
        }

    }
}
