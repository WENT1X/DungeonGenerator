package com.went1x;


import com.went1x.model.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DungeonGenerator {
    private List<RoomConfig> roomConfigs;
    private DungeonLayout layout;
    private Random random;
    private static final int MAX_ATTEMPTS = 100;

    public DungeonGenerator(List<RoomConfig> roomConfigs, DifficultyConfig config) {
        this.roomConfigs = roomConfigs;
        this.layout = new DungeonLayout(config.getMaxTotalRooms(), config.getElementLimits(), config.getMinEnterRoomDistance());
        this.random = new Random();
    }

    public DungeonLayout generate() {
        placeEnterRooms();
        generateRooms();
        return layout;
    }

    private void placeEnterRooms() {
        int enterLimit = layout.getElementLimits().getOrDefault("ENTER", 0);
        for (int i = 0; i < enterLimit; i++) {
            int attempts = 0;
            while (attempts < MAX_ATTEMPTS) {
                RoomConfig config = roomConfigs.stream()
                        .filter(c -> c.getElementType().equals("ENTER"))
                        .findAny()
                        .orElse(null);
                if (config == null) break;
                int x = random.nextInt(1000) - 500;
                int y = 50;
                int z = random.nextInt(1000) - 500;
                RoomInstance room = new RoomInstance("room_" + layout.getRoomCount(), config, x, y, z);
                if (checkMinDistance(room, layout.getRooms())) {
                    layout.addRoom(room);
                    layout.addConnectionPoint(new Point(x, y, z));
                    break;
                }
                attempts++;
            }
        }
    }

    private void generateRooms() {
        while (layout.getRoomCount() < layout.getMaxTotalRooms() && !layout.getOpenConnectionPoints().isEmpty()) {
            Point connection = layout.getOpenConnectionPoints().get(
                    random.nextInt(layout.getOpenConnectionPoints().size()));
            RoomConfig config = selectNextRoomConfig();
            if (config == null) {
                layout.removeConnectionPoint(connection);
                continue;
            }
            RoomInstance room = new RoomInstance(
                    "room_" + layout.getRoomCount(), config, connection.getX(), connection.getY(), connection.getZ());
            layout.addRoom(room);
            layout.removeConnectionPoint(connection);
            layout.addConnectionPoint(new Point(connection.getX(), connection.getY(), connection.getZ()));
        }
    }

    private RoomConfig selectNextRoomConfig() {
        List<RoomConfig> available = roomConfigs.stream()
                .filter(config -> !config.getElementType().equals("ENTER"))
                .filter(config -> layout.getRoomCounts().getOrDefault(config.getElementType(), 0) <
                        layout.getElementLimits().getOrDefault(config.getElementType(), 0))
                .collect(Collectors.toList());
        if (available.isEmpty()) return null;
        int totalWeight = available.stream().mapToInt(RoomConfig::getWeight).sum();
        int randomWeight = random.nextInt(totalWeight);
        int currentWeight = 0;
        for (RoomConfig config : available) {
            currentWeight += config.getWeight();
            if (randomWeight < currentWeight) return config;
        }
        return available.get(available.size() - 1);
    }

    private boolean checkMinDistance(RoomInstance room, List<RoomInstance> rooms) {
        for (RoomInstance existing : rooms) {
            if (existing.getElementType().equals("ENTER") &&
                    calculateDistance(room, existing) < layout.getMinEnterRoomDistance()) {
                return false;
            }
        }
        return true;
    }

    private double calculateDistance(RoomInstance roomA, RoomInstance roomB) {
        return Math.sqrt(Math.pow(roomA.getX() - roomB.getX(), 2) +
                Math.pow(roomA.getY() - roomB.getY(), 2) +
                Math.pow(roomA.getZ() - roomB.getZ(), 2));
    }
}