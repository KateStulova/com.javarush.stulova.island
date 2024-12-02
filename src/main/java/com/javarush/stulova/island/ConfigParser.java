package com.javarush.stulova.island;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ConfigParser {
    private URL configURL;
    private Document doc;
    private Map<String, Map<String, Map<String, Float>>> animalData;
    private Map<String, Integer> islandData;
    private Map<String, Float> plantData;


    public ConfigParser(String filename) {
        this.configURL = ConfigParser.class.getResource("/" + filename);
        this.animalData = new HashMap<>();
        this.islandData = new HashMap<>();
        this.plantData = new HashMap<>();
    }

    public void parse() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(configURL.toString());
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Ooops, something went wrong ...");
        }
        parseAnimals();
        parseIsland();
        parsePlants();
    }

    public void parsePlants() {
        NodeList plantNodes = doc.getElementsByTagName("plants");
        for (int i = 0; i < plantNodes.getLength(); i++) {
            Node plantNode = plantNodes.item(i);
            NodeList innerPlantNodes = plantNode.getChildNodes();
            for (int j = 0; j < innerPlantNodes.getLength(); j++) {
                Node innerPlantNode = innerPlantNodes.item(j);
                if (innerPlantNode.getNodeType() == Node.ELEMENT_NODE) {
                    Float value = Float.parseFloat(innerPlantNode.getAttributes().item(i).getNodeValue());
                    plantData.put(innerPlantNode.getAttributes().item(i).getNodeName(), value);
                }
            }
        }
    }

    public void parseIsland() {
        NodeList islandNodes = doc.getElementsByTagName("island");
        for (int i = 0; i < islandNodes.getLength(); i++) {
            Node islandNode = islandNodes.item(i);
            if (islandNode.getNodeType() == Node.ELEMENT_NODE) {
                int islandAttrNumber = islandNode.getAttributes().getLength();
                String key;
                Integer value;
                for (int j = 0; j < islandAttrNumber; j++) {
                    Attr attr = (Attr) islandNode.getAttributes().item(j);
                    key = attr.getNodeName();
                    value = Integer.parseInt(attr.getNodeValue());
                    islandData.put(key, value);
                }
            }
        }
    }

    public void parseAnimals() {
        Map<String, Map<String, Float>> memoLevel1;
        Map<String, Float> memoLevel0;
        String dataKey = null;
        NodeList animalNodes = doc.getElementsByTagName("animals");
        for (int i = 0; i < animalNodes.getLength(); i++) {
            Node animalNode = animalNodes.item(i);
            NodeList innerAnimalNodes = animalNode.getChildNodes();
            for (int j = 0; j < innerAnimalNodes.getLength(); j++) {
                memoLevel1 = new HashMap<>();
                Node innerAnimalNode = innerAnimalNodes.item(j);
                if (innerAnimalNode.getNodeType() == Node.ELEMENT_NODE) {
                    memoLevel0 = new HashMap<>();
                    int animalAttrNumber = innerAnimalNode.getAttributes().getLength();
                    for (int z = 0; z < animalAttrNumber; z++) {
                        Attr attr = (Attr) innerAnimalNode.getAttributes().item(z);
                        if (attr.getNodeName().equals("species")) {
                            dataKey = attr.getNodeValue();
                        } else {
                            //TODO: Some values should be Integer not Float, check
                            memoLevel0.put(attr.getNodeName(), Float.parseFloat(attr.getNodeValue()));
                        }
                    }
                    memoLevel1.put("Attributes", memoLevel0);
                    NodeList foodListNodes = innerAnimalNode.getChildNodes();
                    memoLevel0 = new HashMap<>();
                    for (int x = 0; x < foodListNodes.getLength(); x++) {
                        Node foodNode = foodListNodes.item(x);
                        NodeList foodNodes = foodNode.getChildNodes();
                        for (int y = 0; y < foodNodes.getLength(); y++) {
                            Node lastChildNode = foodNodes.item(y);
                            if (lastChildNode.getNodeType() == Node.ELEMENT_NODE) {
                                int foodAttrNumber = lastChildNode.getAttributes().getLength();
                                String firstValue = null;
                                Float secondValue = null;
                                for (int n = 0; n < foodAttrNumber; n++) {
                                    Attr attr = (Attr) lastChildNode.getAttributes().item(n);
                                    if (attr.getNodeName().equals("species")) {
                                        firstValue = attr.getNodeValue();
                                    } else if (attr.getNodeName().equals("eatProbability")) {
                                        secondValue = Float.parseFloat(attr.getNodeValue());
                                    }
                                }
                                memoLevel0.put(firstValue, secondValue);
                            }
                        }
                    }
                    memoLevel1.put("Food", memoLevel0);
                    animalData.put(dataKey, memoLevel1);
                }
            }
        }
    }

    public Set<String> getAnimals() {
        return animalData.keySet();
    }

    public Map<String, Float> getAttributesByAnimal(String animalSpecies) {
        return animalData.get(animalSpecies).get("Attributes");
    }

    public Map<String, Float> getFoodByAnimal(String animalSpecies) {
        return animalData.get(animalSpecies).get("Food");
    }

    public Map<String, Integer> getIslandParams() {
        return islandData;
    }

    public Map<String, Float> getPlants() {
        return plantData;
    }
}

