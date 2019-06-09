package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (x == null || y == null) throw new IllegalArgumentException();
        else if ((x.isEmpty() && y.isEmpty()) || (x.isEmpty() && y.size() >= 1)) return true;
        else if (x.size() > y.size()) return false;
        else return checkIsValid(x, y);
    }

    public boolean checkIsValid(List x, List y){
        boolean isValid = true;
        y.retainAll(x);
                for (int i = 0; i < y.size() - 1; i ++){
                    if (y.get(i).equals(x.get(i))){

                    }else {
                        y.remove(i);
                    }
                }
            if (!y.containsAll(x)){
                isValid = false;
            }

        return isValid;
    }
}

