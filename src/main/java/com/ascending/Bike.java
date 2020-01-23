package com.ascending;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

public class Bike {
    public static void main(String[] args){
        List<Integer> sourceList = Lists.newArrayList(0, 1, 2, 3, 4, 5);
        int[] targetArray = Ints.toArray(sourceList);
    }
}
