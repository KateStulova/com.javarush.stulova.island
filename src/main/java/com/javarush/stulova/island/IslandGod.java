package com.javarush.stulova.island;

import com.javarush.stulova.island.entity.*;
import com.javarush.stulova.island.entity.carnivores.*;
import com.javarush.stulova.island.entity.herbivores.*;

import java.util.Map;
import java.util.Set;


/*
    Class Holder Singleton (static, lazy initialization, thread safe).
*/
public class IslandGod {
    private ConfigParser parser;
    private Island island;

    private IslandGod() {
        this.parser = new ConfigParser("world.xml");
    }

    private static class IslandGodHolder {
        public static final IslandGod HOLDER_INSTANCE = new IslandGod();
    }


    public static IslandGod getInstance() {
        return IslandGodHolder.HOLDER_INSTANCE;
    }

    public void pair() {
    }

    public void createWorld() {
        parser.parse();
        int cellX = parser.getIslandParams().get("cellX");
        int cellY = parser.getIslandParams().get("cellY");
        island = new Island(cellX, cellY);
        for (int y = 0; y < cellY; y++) {
            for (int x = 0; x < cellX; x++) {
                Cell currentCell = island.getCell(x, y);
                currentCell.addPlant(new Plant(parser.getPlants().get("weight")));
                Set<String> animals = parser.getAnimals();
                for (String animal : animals) {
                    Map<String, Float> attributes = parser.getAttributesByAnimal(animal);
                    float weight = attributes.get("weight");
                    float maxSatiety = attributes.get("maxSatiety");
                    int maxDistance = Math.round(attributes.get("maxDistance"));
                    currentCell.addAnimal(createAnimal(animal, weight, maxDistance, maxSatiety));
                }
            }
        }
    }

    private Animal createAnimal(String species, float weight, Integer maxDistance, Float maxSatiety) {
        // TODO: beingCaughtProbability shouldn't be solid
        float beingCaughtProbability = 0.5F;
        Animal creature = switch (species) {
            case "Bear":
                yield new Bear(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Boa":
                yield new Boa(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Eagle":
                yield new Eagle(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Fox":
                yield new Fox(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Wolf":
                yield new Wolf(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Boar":
                yield new Boar(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Buffalo":
                yield new Buffalo(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Caterpillar":
                yield new Caterpillar(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Deer":
                yield new Deer(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Duck":
                yield new Duck(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Goat":
                yield new Goat(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Horse":
                yield new Horse(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Mouse":
                yield new Mouse(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Rabbit":
                yield new Rabbit(weight, maxDistance, maxSatiety, beingCaughtProbability);
            case "Sheep":
                yield new Sheep(weight, maxDistance, maxSatiety, beingCaughtProbability);
            default:
                throw new IllegalStateException("Unexpected animal species: " + species);
        };
        return creature;
    }
}
