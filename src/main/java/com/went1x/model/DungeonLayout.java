package com.went1x.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonLayout {
    private List<RoomInstance> rooms;
    private Map<String, Integer> roomCounts;
    private List<Point> openConnectionPoints;
    private int maxTotalRooms;
    private Map<String, Integer> elementLimits;
    private int minEnterRoomDistance;

    public DungeonLayout(int maxTotalRooms, Map<String, Integer> elementLimits, int minEnterRoomDistance) {
        this.rooms = new ArrayList<>();
        this.roomCounts = new HashMap<>();
        this.openConnectionPoints = new ArrayList<>();
        this.maxTotalRooms = maxTotalRooms;
        this.elementLimits = elementLimits;
        this.minEnterRoomDistance = minEnterRoomDistance;
    }

    public void addRoom(RoomInstance room) {
        rooms.add(room);
        roomCounts.put(room.getElementType(), roomCounts.getOrDefault(room.getElementType(), 0) + 1);
    }

    public void addConnectionPoint(Point point) {
        openConnectionPoints.add(point);
    }

    public void removeConnectionPoint(Point point) {
        openConnectionPoints.remove(point);
    }

    public int getRoomCount() {
        return rooms.size();
    }

    public List<RoomInstance> getRooms() {
        return rooms;
    }

    public List<Point> getOpenConnectionPoints() {
        return openConnectionPoints;
    }

    public int getMaxTotalRooms() {
        return maxTotalRooms;
    }

    public Map<String, Integer> getElementLimits() {
        return elementLimits;
    }

    public int getMinEnterRoomDistance() {
        return minEnterRoomDistance;
    }

    public Map<String, Integer> getRoomCounts() {
        return roomCounts;
    }
}