package com.company;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class implements a bubble sort as defined in the sortAlgorithm interface.
 */
public class BubbleSort extends Sort implements SortAlgorithm {

    public BubbleSort(){
        super();
    }

    /**
     * This would define the ascending sort version of the bubble sort algorithm.
     * @param sortArr -> the array to sort.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortAscending( int[] sortArr ){
        replayFrames.clear();
        boolean didSwap;
        do{
            didSwap = false;
            for( int i = 1; i < sortArr.length; i++ ){
                if( sortArr[i-1] > sortArr[i] ){
                    int temporary = sortArr[i-1];
                    sortArr[i-1] = sortArr[i];
                    sortArr[i] = temporary;
                    didSwap = true;
                }
            }
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        } while( didSwap );
        return this.replayFrames;
    }

    /**
     * This would define the descending sort version of the bubble sort algorithm.
     * @param sortArr -> the array to sort.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortDescending( int[] sortArr ){
        replayFrames.clear();
        boolean didSwap;
        do{
            didSwap = false;
            for( int i = 1; i < sortArr.length; i++ ){
                if( sortArr[i-1] < sortArr[i] ){
                    int temporary = sortArr[i-1];
                    sortArr[i-1] = sortArr[i];
                    sortArr[i] = temporary;
                    didSwap = true;
                }
            }
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        } while( didSwap );
        return this.replayFrames;
    }
}
