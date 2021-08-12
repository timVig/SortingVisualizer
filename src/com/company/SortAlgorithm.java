package com.company;

import java.util.LinkedList;

/**
 * This interface defines the two methods a sort algorithm should have
 */
public interface SortAlgorithm {
    /**
     * This function defines the ascending sort of a sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    LinkedList<int[]> sortAscending(int[] sortArr);

    /**
     * This function defines the descending sort of a sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    LinkedList<int[]> sortDescending( int[] sortArr );
}
