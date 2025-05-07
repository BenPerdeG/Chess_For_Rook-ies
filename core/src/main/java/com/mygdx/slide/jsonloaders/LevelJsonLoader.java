package com.mygdx.slide.jsonloaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class LevelJsonLoader {
    public static LevelLayout loadLevel(String fileName) {
        Json json = new Json();
        FileHandle file = Gdx.files.internal(fileName);


        int[][] rawMap = json.fromJson(int[][].class, file);

        LevelLayout level = new LevelLayout();
        level.mapHeight = rawMap.length;
        level.mapWidth = rawMap[0].length;
        level.tileMap = new byte[level.getMapHeight()][level.getMapWidth()];

        for (int y = 0; y < level.mapHeight; y++) {
            for (int x = 0; x < level.mapWidth; x++) {
                switch(rawMap[y][x]) {
                    case 8:
                        level.playerX = x;
                        level.playerY = y;
                        level.tileMap[y][x] = 0;
                        break;
                    case 3: // Rey
                        level.kingX = x;
                        level.kingY = y;
                        level.tileMap[y][x] = 0;
                        break;
                    case 5:
                        level.tileMap[y][x] = 1;
                        break;
                    default: // Vacío
                        level.tileMap[y][x] = 0;
                }
            }
        }

        return level;
    }
}
