package com.tsystems.javaschool.tasks.calculator;

import com.tsystems.javaschool.tasks.pyramid.PyramidBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PyramidBuilder p = new PyramidBuilder();
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, 5, 10, 8, 7, 6);
        System.out.println(Arrays.deepToString(p.buildPyramid(input)));




    }

}
