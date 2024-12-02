package com.javarush.stulova.island.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private float weight;
    int maxDistance;
    float maxSatiety;
    float actualSatiety;
    boolean isAlive = true;
    float beingCaughtProbability;
    Set<String> edibleAnimals;

    public Animal(float weight, int maxDistance, float maxSatiety, float beingCaughtProbability) {
        this.weight = weight;
        this.maxDistance = maxDistance;
        this.maxSatiety = maxSatiety;
        this.beingCaughtProbability = beingCaughtProbability;
    }

    public float getWeight() {
        return this.weight;
    }

    public abstract String getSpecies();

    public void passiveCaloriesBurn() {
        float random = ThreadLocalRandom.current().nextFloat(0.1F);
        this.actualSatiety -= this.maxSatiety * random;
        if (this.actualSatiety < 0) {
            die();
        }
    }

    public abstract void eat(Object obj);

    public abstract void move(Object obj);

    public abstract void chooseDirection(Object obj);

    public Object reproduce(Object obj) {
        return new Object();
    }

    public void die() {
        this.isAlive = false;
    }

    public boolean canBeReproduced() {
        return this.actualSatiety == this.maxSatiety;
    }

    public boolean isCaught() {
        float random = ThreadLocalRandom.current().nextFloat();
        return random < this.beingCaughtProbability;
    }

    public static Set<String> findClosestFood(Set<String> edibleAnimals, Set<String> allSpeciesAtCell) {
        Set<String> result = new HashSet<>(edibleAnimals);
        result.retainAll(allSpeciesAtCell);
        return result;
    }
}
