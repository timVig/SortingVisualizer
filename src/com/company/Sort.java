package com.company;

import java.util.LinkedList;

/**
 * This class defines a constructor that all sort classes have in common for replays.
 * Any Sort can extend this class and use super(); as a constructor.
 */
public class Sort {
    LinkedList<int[]> replayFrames;
    public Sort(){
        replayFrames = new LinkedList<>();
    }
}