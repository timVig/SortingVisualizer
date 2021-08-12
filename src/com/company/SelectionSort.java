package com.company;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class implements a selection sort as defined in the sortAlgorithm interface.
 */
public class SelectionSort extends Sort implements SortAlgorithm {

    public SelectionSort() {
        super();
    }

    /**
     * This function defines the ascending sort of the selection sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortAscending(int[] sortArr) {
        int min;
        int minIndex;

        for( int i = 0; i < sortArr.length; i++ ){
            min = sortArr[i];
            minIndex = i;
            for( int j = i+1; j < sortArr.length; j++ ){
                if( sortArr[j] < min ){
                    min = sortArr[j];
                    minIndex = j;
                }
            }
            int temp = sortArr[i];
            sortArr[i] = sortArr[minIndex];
            sortArr[minIndex] = temp;
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        }
        return this.replayFrames;
    }

    /**
     * This function defines the descending sort of the selection sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortDescending(int[] sortArr) {
        int max;
        int maxIndex;

        for( int i = 0; i < sortArr.length; i++ ){
            max = sortArr[i];
            maxIndex = i;
            for( int j = i+1; j < sortArr.length; j++ ){
                if( sortArr[j] > max ){
                    max = sortArr[j];
                    maxIndex = j;
                }
            }
            int temp = sortArr[i];
            sortArr[i] = sortArr[maxIndex];
            sortArr[maxIndex] = temp;
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        }
        return this.replayFrames;
    }
}
