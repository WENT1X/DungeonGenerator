package com.went1x;


import com.went1x.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DungeonGeneratorTest {
    @Test
    public void testDungeonGeneration() {
        // Подготовка тестовых данных
        List<RoomConfig> roomConfigs = Arrays.asList(
                new RoomConfig("enter_1", "ENTER", 10, new int[]{16, 8, 16}),
                new RoomConfig("corridor_1", "CORRIDOR", 50, new int[]{8, 4, 8}),
                new RoomConfig("treasure_1", "TREASURE", 20, new int[]{12, 6, 12})
        );
        DifficultyConfig config = new DifficultyConfig();
        config.setMaxTotalRooms(10);
        config.setMinEnterRoomDistance(20);
        Map<String, Integer> limits = new HashMap<>();
        limits.put("ENTER", 2);
        limits.put("CORRIDOR", 5);
        limits.put("TREASURE", 3);
        config.setElementLimits(limits);

        // Генерация
        DungeonGenerator generator = new DungeonGenerator(roomConfigs, config);
        DungeonLayout layout = generator.generate();

        // Проверки
        assertNotNull(layout);
        assertTrue(layout.getRoomCount() <= config.getMaxTotalRooms());
        assertEquals(2, layout.getRoomCounts().getOrDefault("ENTER", 0));
        assertTrue(layout.getRoomCounts().getOrDefault("CORRIDOR", 0) <= 5);
        assertTrue(layout.getRoomCounts().getOrDefault("TREASURE", 0) <= 3);
    }
}