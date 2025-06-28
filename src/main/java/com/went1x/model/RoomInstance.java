package com.went1x.model;

public class RoomInstance {
    private String id;
    private String elementType;
    private RoomConfig config;
    private int x, y, z;

    public RoomInstance(String id, RoomConfig config, int x, int y, int z) {
        this.id = id;
        this.config = config;
        this.elementType = config.getElementType();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getId() { return id; }
    public String getElementType() { return elementType; }
    public RoomConfig getConfig() { return config; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}