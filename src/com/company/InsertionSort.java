package com.company;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class implements a insertion sort as defined in the sortAlgorithm interface.
 */
public class InsertionSort extends Sort implements SortAlgorithm {

    public InsertionSort() {
        super();
    }

    /**
     * This function defines the ascending sort of the insertion sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortAscending(int[] sortArr) {
        int j;
        for( int i = 1; i < sortArr.length; i++ ){ //start of unsorted array
            int key = sortArr[i];
            for( j = i-1; j >= 0; j-- ){ //end of sorted array
                if( sortArr[j] > key ){ //if sorted arr[j] < inserting value
                    sortArr[j+1] = sortArr[j]; //swap them
                } else { break; }
            }
            sortArr[j+1] = key;
            this.replayFrames.addLast(
                    Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        }
        return this.replayFrames;
    }

    /**
     * This function defines the descending sort of the insertion sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortDescending(int[] sortArr) {
        int j;
        for( int i = 1; i < sortArr.length; i++ ){ //start of unsorted array
            int key = sortArr[i];
            for( j = i-1; j >= 0; j-- ){ //end of sorted array
                if( sortArr[j] < key ){ //if sorted arr[j] < inserting value
                    sortArr[j+1] = sortArr[j]; //swap them
                } else { break; }
            }
            sortArr[j+1] = key;
            this.replayFrames.addLast(
                    Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        }
        return this.replayFrames;
    }
}
