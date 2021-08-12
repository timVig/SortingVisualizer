package com.company;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * This class contains methods pertaining to visualizing sorting algorithms and configuring the UI.
 */
public class Visualizer {

    public static int arraySize = 64;
    public static final int arrayHeight = 64;
    public static final int chartWidth = (int) ((double) Toolkit.getDefaultToolkit().getScreenSize().width * 0.8 );
    public static final int chartHeight = (int) ((double) Toolkit.getDefaultToolkit().getScreenSize().height * 0.6 );
    public static final int frameWidth = chartWidth;
    public static final int frameHeight = chartHeight + 75;

    public static final BubbleSort bubbleSort = new BubbleSort();
    public static final ExchangeSort exchangeSort = new ExchangeSort();
    public static final SelectionSort selectionSort = new SelectionSort();
    public static final InsertionSort insertionSort = new InsertionSort();
    public static final MergeSort mergeSort = new MergeSort();
    public static final Quicksort quickSort = new Quicksort();
    public static SortAlgorithm sort = bubbleSort; //current sort to run

    public static final String[] sortTypes = { "ascending", "descending" };
    public static final String[] sortingAlgorithms =
            { "bubble", "exchange", "insertion", "selection", "merge", "quick" };
    public static final SortAlgorithm[] sortImplementations = {bubbleSort, exchangeSort, selectionSort, insertionSort,
            mergeSort, quickSort };
    public static HashMap<String, SortAlgorithm> stringToAlgo = new HashMap<>();

    public static int[] index = new int[arraySize];
    public static int[] array = new int[arraySize];
    public static String searchType = "ascending";
    public static String sortingAlgo = "bubble";
    public static JLabel search;
    public static JLabel type;

    public static CategoryChart chart;
    public static SwingWrapper<CategoryChart> wrapper;
    public static JFrame frame;
    public static LinkedList<int[]> replays;

    public static Semaphore semaphore = new Semaphore(1);

    /**
     * This is the main method which starts the visualizer
     */
    public static void main(String[] args) {
        generateRandomArrays( index, array );
        initializeHashmap(stringToAlgo, sortingAlgorithms, sortImplementations );
        chart = new CategoryChartBuilder().width(chartWidth).height(chartHeight).title("Sort Algorithm Display Tool").build();
        wrapper = new SwingWrapper<>(chart);
        frame = wrapper.displayChart();
        chart.addSeries("Sort Elements", index, array );
        frame.setSize( frameWidth, frameHeight );
        frame.setLayout( new BorderLayout() );
        startUI( frame );
    }

    /**
     * Starts the User Interface by creating buttons and listeners for them
     * @param frame -> frame to add Components to
     */
    public static void startUI( JFrame frame ){
        JPanel UI = new JPanel();
        JButton genbutton = new JButton("Generate New");
        JButton jbutton = new JButton("Sort");
        JLabel sortLabel = new JLabel("Sort: " + sortingAlgo );
        JLabel typeLabel = new JLabel( "Type: " + searchType );
        search = sortLabel; type = typeLabel;
        genbutton.addActionListener( new GenerateListener() );
        UI.add( genbutton );
        jbutton.addActionListener( new PerformSortListener() );
        UI.add( jbutton );
        UI.add(sortLabel);
        UI.add(typeLabel);

        for( String s: sortTypes ){
            JButton button = new JButton(s);
            button.addActionListener( new SortTypeListener(s) );
            UI.add( button );
        }

        for( String s: sortingAlgorithms ){
            JButton button = new JButton(s);
            button.addActionListener( new SortListener(s) );
            UI.add( button );
        }
        frame.add( UI, BorderLayout.SOUTH );
    }

    /**
     * This resets the index array and generates a random array of heights for the y-axis.
     * @param index -> index array
     * @param array -> randomly generated array
     */
    public static void generateRandomArrays( int[] index, int[] array ){
        Random randomGenerator = new Random();
        for( int i = 0; i < arraySize; i++ ) { array[i] = randomGenerator.nextInt(arrayHeight); index[i] = i+1; }
    }

    /**
     * Initializes a hashmap of Strings to SortAlgorithms
     */
    public static void initializeHashmap( HashMap<String, SortAlgorithm> sorts, String[] names, SortAlgorithm[] algos ){
        for( int i = 0; i < names.length; i++ ){ sorts.put( names[i], algos[i] ); }
    }

    /**
     * Performs an ascending sort
     * @param sort -> the sort to perform
     * @param sortArr -> the array to sort
     * @return -> replay frames for visualization.
     */
    public static LinkedList<int[]> sortAscending( SortAlgorithm sort, int[] sortArr ){
        return sort.sortAscending( sortArr );
    }

    /**
     * Performs an descending sort
     * @param sort -> the sort to perform
     * @param sortArr -> the array to sort
     * @return -> replay frames for visualization.
     */
    public static LinkedList<int[]> sortDescending( SortAlgorithm sort, int[] sortArr ){
        return sort.sortDescending( sortArr );
    }

    /**
     * Visualizes a replay on the chart by using an array of replay frames(int[])
     * @param replayFrames -> Collection of replay frames
     * @param chart -> chart to update
     * @param wrapper-> wrapper to update
     * @param index-> the index array
     */
    public static void visualizeReplay( LinkedList<int[]> replayFrames, CategoryChart chart,
                                        SwingWrapper<CategoryChart> wrapper, int[] index ) throws InterruptedException {
        int sleepTime = 15000 / replayFrames.size(); //makes replay take 15 seconds to complete
        while( !replayFrames.isEmpty() ){
            int[] array = replayFrames.getFirst();
            replayFrames.removeFirst();
            LinkedList<Integer> list = new LinkedList<>();
            LinkedList<Integer> indexes = new LinkedList<>();
            for( int i = 0; i < array.length; i++ )
                list.addLast( array[i] );
            for( int i = 0; i < index.length; i++ )
                indexes.addLast( index[i] );

            chart.updateCategorySeries( "Sort Elements", indexes, list, null );
            wrapper.repaintChart();
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        }
    }

    /**
     * Executes visualization in a separate thread, releasing the semaphore when it is done.
     * @param exec -> The thread executor service
     */
    public static void executeVisuals( ExecutorService exec ){
        exec.execute( () -> {
            try { visualizeReplay( replays, chart, wrapper, index ); }
            catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
            finally {
                System.out.println("Releasing");
                semaphore.release();
            }
        });
    }

    /**
     * This class is the actionlistener which is hooked to the generate button to generate random arrays.
     */
    public static class GenerateListener implements ActionListener{
        @Override public void actionPerformed(ActionEvent e) {
            if( semaphore.tryAcquire() ){
                generateRandomArrays( index, array );
                LinkedList<Integer> list = new LinkedList<>();
                LinkedList<Integer> indexes = new LinkedList<>();
                for( int i = 0; i < array.length; i++ )
                    list.addLast( array[i] );
                for( int i = 0; i < index.length; i++ )
                    indexes.addLast( index[i] );

                chart.updateCategorySeries( "Sort Elements", indexes, list, null );
                wrapper.repaintChart();
                semaphore.release();
            }
        }
    }

    /**
     * This class is the actionlistener which is hooked to the sort button to perform the sort.
     */
    public static class PerformSortListener implements ActionListener{
        @Override public void actionPerformed(ActionEvent e) {
            ExecutorService exec = Executors.newFixedThreadPool(1);
            if( semaphore.tryAcquire() ){
                if( searchType.equals(sortTypes[0])) replays = sortAscending( sort, array );
                else replays = sortDescending( sort, array );
                executeVisuals(exec);
            }
        }
    }

    /**
     * This class is the actionlistener which is hooked to the sort selection to change the sort used.
     */
    public static class SortListener implements ActionListener{
        String sortAlgo;
        public SortListener( String s ){ this.sortAlgo = s; }

        @Override public void actionPerformed(ActionEvent e) {
            sortingAlgo = sortAlgo;
            Visualizer.search.setText(sortAlgo);
            sort = stringToAlgo.get(sortAlgo);
        }
    }

    /**
     * This class is the actionlistener which is hooked to the sort type selection to choose as/de-cending sort.
     */
    public static class SortTypeListener implements ActionListener{
        String type;
        public SortTypeListener( String s ){ this.type = s; }

        @Override public void actionPerformed(ActionEvent e) {
            searchType = this.type;
            Visualizer.type.setText( this.type );
        }
    }
}