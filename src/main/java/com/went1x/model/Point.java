package com.went1x.model;


public class Point {
    private int x, y, z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Геттеры
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}