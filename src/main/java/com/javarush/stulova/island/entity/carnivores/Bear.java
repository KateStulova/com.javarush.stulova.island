package com.javarush.stulova.island.entity.carnivores;

import com.javarush.stulova.island.entity.Carnivore;

public class Bear extends Carnivore {
    public Bear(float weight, int maxDistance, float maxSatiety, float beingCaughtProbability) {
        super(weight, maxDistance, maxSatiety, beingCaughtProbability);
    }

    @Override
    public String getSpecies() {
        return Bear.class.getSimpleName();
    }
}
