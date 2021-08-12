package com.company;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class implements a exchange sort as defined in the sortAlgorithm interface.
 */
public class ExchangeSort extends Sort implements SortAlgorithm {

    public ExchangeSort(){
        super();
    }

    /**
     * This function defines the ascending sort of the exchange sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortAscending(int[] sortArr) {
        for( int i = 0; i < sortArr.length; i++ ){
            for( int j = i+1; j < sortArr.length; j++ ){
                if( sortArr[i] > sortArr[j] ){
                    int temp = sortArr[i];
                    sortArr[i] = sortArr[j];
                    sortArr[j] = temp;
                }
            } this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        } return this.replayFrames;
    }

    /**
     * This function defines the descending sort of the exchange sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortDescending(int[] sortArr) {
        for( int i = 0; i < sortArr.length; i++ ){
            for( int j = i+1; j < sortArr.length; j++ ){
                if( sortArr[i] < sortArr[j] ){
                    int temp = sortArr[i];
                    sortArr[i] = sortArr[j];
                    sortArr[j] = temp;
                }
            } this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
        } return this.replayFrames;
    }
}
