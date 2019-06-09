package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        if (inputNumbers.contains(null)) throw new CannotBuildPyramidException();
        try {
            Collections.sort(inputNumbers);
        } catch (java.lang.OutOfMemoryError error) {
            throw new CannotBuildPyramidException();
        }
        int countofAll = 0;
        int height = 0;
        while (countofAll != inputNumbers.size()) {
            height++;
            countofAll += height;
            if (countofAll > inputNumbers.size()) throw new CannotBuildPyramidException();
        }
        int width = 2 * height - 1;
        int[][] arr = new int[height][width];
        int index = inputNumbers.size() - 1;
        for (int i = 0; i < arr.length; i++) {
            int s = width - 1 - i;
            int k = height - i - 1;
            while (s >= i) {
                arr[k][s] = inputNumbers.get(index--);
                if (index <= 0) break;
                s -= 2;
            }
        }
        return arr;
    }




}




