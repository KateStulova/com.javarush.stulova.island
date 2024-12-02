package com.javarush.stulova.island.entity;

public class Carnivore extends Animal {

    public Carnivore(float weight, int maxDistance, float maxSatiety, float beingCaughtProbability) {
        super(weight, maxDistance, maxSatiety, beingCaughtProbability);
    }

    @Override
    public void eat(Object obj) { // TODO: как определять кого можно есть
        if (obj instanceof Animal) {
            Animal animal = (Animal) obj;
            if (animal.isCaught()) {
                this.actualSatiety = Math.min(this.maxSatiety, animal.getWeight() + this.actualSatiety);
                animal.die();
            }
        } else {
            throw new RuntimeException("Stop eating plants!!");
        }
    }

    @Override
    public void move(Object obj) {

    }

    @Override
    public void chooseDirection(Object obj) {

    }

    @Override
    public String getSpecies() {
        return "UnknownCarnivore";
    }
}
