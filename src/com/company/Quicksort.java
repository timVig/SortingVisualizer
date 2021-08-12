package com.company;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class implements a quick sort as defined in the sortAlgorithm interface.
 */
public class Quicksort extends Sort implements SortAlgorithm {

    public Quicksort() {
        super();
    }

    /**
     * This function defines the ascending sort of the quick sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortAscending(int[] sortArr) {
        return qSortAscending( sortArr, 0, sortArr.length - 1 );
    }

    /**
     * This function gets the partition of the array and performs recursive calls using it, in order to perform the
     * ascending quicksort on the input array.
     * @param sortArr -> the array to sort
     * @param low -> low index
     * @param high -> high index
     * @return -> replay frame collection
     */
    public LinkedList<int[]> qSortAscending( int[] sortArr, int low, int high ){
        if( low < high ){
            int partitionIndex = ascendingPartition( sortArr, low, high );
            qSortAscending( sortArr, low, partitionIndex - 1 );
            qSortAscending( sortArr, partitionIndex + 1, high );
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
            return replayFrames;
        } else return null;
    }

    /**
     * This method gets the ascending partition of the input array
     * @param sortArr -> input array
     * @param low -> low index
     * @param high -> high index
     * @return -> the index of the ascending partition
     */
    public int ascendingPartition( int[] sortArr, int low, int high ){
        int pivot = sortArr[high];
        int index = low - 1;
        for( int j = low; j <= high - 1; j++ ){
            if( sortArr[j] < pivot ){
                index++;
                int temp = sortArr[index];
                sortArr[index] = sortArr[j];
                sortArr[j] = temp;
                this.replayFrames.addLast(
                        Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
            }
        }
        index++;
        int temp = sortArr[index];
        sortArr[index] = sortArr[high];
        sortArr[high] = temp;
        return index;
    }

    /**
     * This function defines the descending sort of the quick sort algorithm.
     * @param sortArr -> the array to be sorted.
     * @return -> replay frames for later visualization.
     */
    @Override public LinkedList<int[]> sortDescending(int[] sortArr) {
        return qSortDescending( sortArr, 0, sortArr.length-1 );
    }

    /**
     * This function gets the partition of the array and performs recursive calls using it, in order to perform the
     * descending quicksort on the input array.
     * @param sortArr -> the array to sort
     * @param low -> low index
     * @param high -> high index
     * @return -> replay frame collection
     */
    public LinkedList<int[]> qSortDescending( int[] sortArr, int low, int high ){
        if( low < high ){
            int partitionIndex = DescendingPartition( sortArr, low, high );
            qSortDescending( sortArr, low, partitionIndex - 1 );
            qSortDescending( sortArr, partitionIndex + 1, high );
            this.replayFrames.addLast( Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
            return replayFrames;
        } else return null;
    }

    /**
     * This method gets the descending partition of the input array
     * @param sortArr -> input array
     * @param low -> low index
     * @param high -> high index
     * @return -> the index of the descending partition
     */
    public int DescendingPartition( int[] sortArr, int low, int high ){
        int pivot = sortArr[high];
        int index = low - 1;
        for( int j = low; j <= high - 1; j++ ){
            if( sortArr[j] > pivot ){
                index++;
                int temp = sortArr[index];
                sortArr[index] = sortArr[j];
                sortArr[j] = temp;
                this.replayFrames.addLast(
                        Arrays.copyOfRange(sortArr, 0, sortArr.length ) );
            }
        }
        index++;
        int temp = sortArr[index];
        sortArr[index] = sortArr[high];
        sortArr[high] = temp;
        return index;
    }
}
