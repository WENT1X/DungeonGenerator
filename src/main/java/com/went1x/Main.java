package com.went1x;
import com.went1x.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            InputStreamReader difficultyReader = new InputStreamReader(
                    Main.class.getClassLoader().getResourceAsStream("configs/difficulty.json"));
            DifficultyConfig difficultyConfig = gson.fromJson(difficultyReader, DifficultyConfig.class);

            InputStreamReader roomsReader = new InputStreamReader(
                    Main.class.getClassLoader().getResourceAsStream("configs/rooms.json"));
            List<RoomConfig> roomConfigs = gson.fromJson(roomsReader, new TypeToken<List<RoomConfig>>(){}.getType());

            DungeonGenerator generator = new DungeonGenerator(roomConfigs, difficultyConfig);
            DungeonLayout layout = generator.generate();

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
