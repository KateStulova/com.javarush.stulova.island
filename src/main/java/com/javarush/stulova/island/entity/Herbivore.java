package com.javarush.stulova.island.entity;

public class Herbivore extends Animal {

    public Herbivore(float weight, int maxDistance, float maxSatiety, float beingCaughtProbability) {
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
        } else if (obj instanceof Plant) {
            Plant plant = (Plant) obj;
            this.actualSatiety += plant.graze(this.maxSatiety - this.actualSatiety);
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
        return "UnknownHerbivore";
    }
}
