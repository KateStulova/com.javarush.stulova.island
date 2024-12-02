package com.javarush.stulova.island;

public class Island {
    private Cell[][] islandMap;

    public Island(int x, int y) {
        this.islandMap = new Cell[y][x];
    }

    public Cell getCell(int x, int y) {
        return islandMap[y][x];
    }
}
