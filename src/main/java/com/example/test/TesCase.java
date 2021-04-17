package com.example.test;



import java.util.HashMap;

public class TesCase {
    private int NumberOfPoints;
    private HashMap<Integer, Island> setOfIslands;
    private HashMap<Integer, Lineas> setOfLines;


    public void addIslands(Island isla) {
        setOfIslands.put(isla.hashCode(), isla);
    }

    public HashMap<Integer, Island> getSetOfIslands() {
        return setOfIslands;
    }

    public void setSetOfIslands(HashMap<Integer, Island> setOfIslands) {
        this.setOfIslands = setOfIslands;
    }

    public TesCase(int numberOfPoints) {
        NumberOfPoints = numberOfPoints;
        setOfIslands = new HashMap<>();

    }
}
