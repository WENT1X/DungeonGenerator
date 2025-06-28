package com.went1x.model;

public class RoomConfig {
    private String id;
    private String elementType;
    private int weight;
    private int[] dimensions;

    public RoomConfig(String id, String elementType, int weight, int[] dimensions) {
        this.id = id;
        this.elementType = elementType;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    public String getId() { return id; }
    public String getElementType() { return elementType; }
    public int getWeight() { return weight; }
    public int[] getDimensions() { return dimensions; }
}