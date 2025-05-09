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
        level.mapHeight = 8;
        level.mapWidth = 8;
        level.tileMap = new byte[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                switch(rawMap[y][x]) {
                    case 8: // Jugador
                        level.playerX = x;
                        level.playerY = y;
                        level.tileMap[y][x] = 0;
                        break;
                    case 3: // Rey enemigo
                        level.kingX = x;
                        level.kingY = y;
                        level.tileMap[y][x] = 0;
                        break;
                    case 5: // Pieza aliada
                        level.tileMap[y][x] = 1;
                        break;
                    default:
                        level.tileMap[y][x] = 0;
                }
            }
        }
        return level;
    }
}
