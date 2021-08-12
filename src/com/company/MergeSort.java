package com.company;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class implements a merge sort as defined in the sortAlgorithm interface.
 */
public class MergeSort extends Sort implements SortAlgorithm {

    public MergeSort() {
        super();
    }

    /**
     * This function defines the ascending sort of the merge sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortAscending(int[] sortArr) {
        return mergeAscending( sortArr, 0, sortArr.length-1 );
    }

    /**
     * This function defines the descending sort of the merge sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortDescending(int[] sortArr) {
        return mergeDescending( sortArr, 0, sortArr.length - 1 );
    }

    /**
     * This function defines the splitting of the array into two parts, and makes the recursive calls to the left and
     * the right sides of the array, at the end of each subproblem the merge method will be called and begin building
     * the sorted array upwards.
     * @param sortArr -> array to split
     * @param left -> left index
     * @param right -> right index
     * @return -> replayFrames
     */
    public LinkedList<int[]> mergeAscending( int[] sortArr, int left, int right ){
        if( right > left ){
            int middle = left + ( right - left ) / 2;
            mergeAscending( sortArr, left, middle );
            mergeAscending( sortArr, middle+1, right );
            mergeA( sortArr, left, middle, right );
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
            return replayFrames;
        } else
            return null;
    }

    /**
     * This method is called to merge two sorted arrays together in ascending order
     * @param sortArr -> the main array holding the two subarrays.
     * @param l -> the left index
     * @param m -> the center index
     * @param r -> the right index
     */
    public void mergeA( int[] sortArr, int l, int m, int r ){
        //Derive the sizes of the two arrays in the merge process
        int sizeOne = (m - l) + 1;
        int sizeTwo = (r - m);
        int[] arrOne = new int[sizeOne];
        int[] arrTwo = new int[sizeTwo];

        int startFirst = 0; int startSecond = 0;
        int count = l;

        //Fill the two arrays with their respective sections of the main array.
        for( int i = 0; i < sizeOne; i++ )
            arrOne[i] = sortArr[l+i];
        for( int i = 0; i < sizeTwo; i++ )
            arrTwo[i] = sortArr[m+1+i];

        //Perform a mergesort on the two arrays which were created, reorganizing the
        //main array in the process.
        while( startFirst < sizeOne && startSecond < sizeTwo ){
            if( arrOne[startFirst] <= arrTwo[startSecond] )
                sortArr[count++] = arrOne[startFirst++];
            else
                sortArr[count++] = arrTwo[startSecond++];
        }

        //Get any remaining elements out of the arrays which may still exist there.
        while( startFirst < sizeOne )
            sortArr[count++] = arrOne[startFirst++];
        while (startSecond < sizeTwo )
            sortArr[count++] = arrTwo[startSecond++];
    }

    /**
     * This function defines the splitting of the array into two parts, and makes the recursive calls to the left and
     * the right sides of the array, at the end of each subproblem the merge method will be called and begin building
     * the sorted array upwards.
     * @param sortArr -> array to split
     * @param left -> left index
     * @param right -> right index
     * @return -> replayFrames
     */
    public LinkedList<int[]> mergeDescending( int[] sortArr, int left, int right ){
        if( right > left ){
            int middle = left + ( right - left ) / 2;
            mergeDescending( sortArr, left, middle );
            mergeDescending( sortArr, middle+1, right );
            mergeD( sortArr, left, middle, right );
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
            return replayFrames;
        } else
            return null;
    }

    /**
     * This method is called to merge two sorted arrays together in descending order
     * @param sortArr -> the main array holding the two subarrays.
     * @param l -> the left index
     * @param m -> the center index
     * @param r -> the right index
     */
    public void mergeD( int[] sortArr, int l, int m, int r ){
        //Derive the sizes of the two arrays in the merge process
        int sizeOne = (m - l) + 1;
        int sizeTwo = (r - m);
        int[] arrOne = new int[sizeOne];
        int[] arrTwo = new int[sizeTwo];

        int startFirst = 0; int startSecond = 0;
        int count = l;

        //Fill the two arrays with their respective sections of the main array.
        for( int i = 0; i < sizeOne; i++ )
            arrOne[i] = sortArr[l+i];
        for( int i = 0; i < sizeTwo; i++ )
            arrTwo[i] = sortArr[m+1+i];

        //Perform a mergesort on the two arrays which were created, reorganizing the
        //main array in the process.
        while( startFirst < sizeOne && startSecond < sizeTwo ){
            if( arrOne[startFirst] >= arrTwo[startSecond] )
                sortArr[count++] = arrOne[startFirst++];
            else
                sortArr[count++] = arrTwo[startSecond++];
        }

        //Get any remaining elements out of the arrays which may still exist there.
        while( startFirst < sizeOne )
            sortArr[count++] = arrOne[startFirst++];
        while (startSecond < sizeTwo )
            sortArr[count++] = arrTwo[startSecond++];
    }
}