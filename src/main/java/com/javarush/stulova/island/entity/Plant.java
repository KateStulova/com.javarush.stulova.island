package com.javarush.stulova.island.entity;

import java.util.concurrent.ThreadLocalRandom;

public class Plant {
    float actualWeight;
    float maxWeight;

    public Plant(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void grow() {
        float random = ThreadLocalRandom.current().nextFloat();
        actualWeight = Math.min(maxWeight, (random * maxWeight) + actualWeight);
    }

    public float graze(float maxPlantsSatiety) {
        float random = ThreadLocalRandom.current().nextFloat();
        float actuallyGrazedWeight = Math.min(maxPlantsSatiety, actualWeight * random);
        actualWeight -= actuallyGrazedWeight;
        return actuallyGrazedWeight;
    }
}
