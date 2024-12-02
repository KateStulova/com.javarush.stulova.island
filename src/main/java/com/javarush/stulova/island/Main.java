package com.javarush.stulova.island;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        IslandGod islandGod = IslandGod.getInstance();
        islandGod.createWorld();

        ConfigParser p = new ConfigParser("world.xml");
        p.parse();
        System.out.println(p.getPlants());
        System.out.println(p.getIslandParams());
    }
}