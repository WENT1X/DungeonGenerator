package com.went1x;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import com.went1x.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            // Загрузка конфигурации сложности
            InputStreamReader difficultyReader = new InputStreamReader(
                    Main.class.getClassLoader().getResourceAsStream("configs/difficulty.json"));
            DifficultyConfig difficultyConfig = gson.fromJson(difficultyReader, DifficultyConfig.class);

            // Загрузка конфигурации комнат
            InputStreamReader roomsReader = new InputStreamReader(
                    Main.class.getClassLoader().getResourceAsStream("configs/rooms.json"));
            List<RoomConfig> roomConfigs = gson.fromJson(roomsReader, new TypeToken<List<RoomConfig>>(){}.getType());

            // Генерация подземелья
            DungeonGenerator generator = new DungeonGenerator(roomConfigs, difficultyConfig);
            DungeonLayout layout = generator.generate();

            // Вывод результата
            System.out.println("Generated Dungeon:");
            for (RoomInstance room : layout.getRooms()) {
                System.out.printf("Room ID: %s, Type: %s, Position: (%d, %d, %d)%n",
                        room.getId(), room.getElementType(), room.getX(), room.getY(), room.getZ());
            }
            System.out.printf("Total rooms: %d%n", layout.getRoomCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}