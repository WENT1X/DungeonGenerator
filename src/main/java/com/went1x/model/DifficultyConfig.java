package com.went1x.model;
import java.util.Map;

public class DifficultyConfig {
    private String difficultyName;
    private int maxTotalRooms;
    private int minEnterRoomDistance;
    private Map<String, Integer> elementLimits;

    public String getDifficultyName() { return difficultyName; }
    public void setDifficultyName(String difficultyName) { this.difficultyName = difficultyName; }
    public int getMaxTotalRooms() { return maxTotalRooms; }
    public void setMaxTotalRooms(int maxTotalRooms) { this.maxTotalRooms = maxTotalRooms; }
    public int getMinEnterRoomDistance() { return minEnterRoomDistance; }
    public void setMinEnterRoomDistance(int minEnterRoomDistance) { this.minEnterRoomDistance = minEnterRoomDistance; }
    public Map<String, Integer> getElementLimits() { return elementLimits; }
    public void setElementLimits(Map<String, Integer> elementLimits) { this.elementLimits = elementLimits; }
}