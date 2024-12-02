package com.javarush.stulova.island;

import com.javarush.stulova.island.entity.Animal;
import com.javarush.stulova.island.entity.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cell {
    private Map<String, ArrayList<Animal>> animals;
    private Plant plants;

    public Cell() {
        this.animals = new HashMap<>();
    }

    public void addAnimal(Animal animal) {
       animals.computeIfAbsent(animal.getSpecies(), v -> new ArrayList<Animal>()).add(animal);
    }

    public void addPlant(Plant plant) {
        if (this.plants != null) {
            this.plants = plant;
        }
    }

    public Set<String> getAllSpecies() {
        return animals.keySet();
    }

    public ArrayList<Animal> getAnimalsBySpecies(String animalSpecies){
        return animals.get(animalSpecies);
    }
}
