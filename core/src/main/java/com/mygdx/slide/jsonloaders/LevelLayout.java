package com.mygdx.slide.jsonloaders;

public class LevelLayout {
    int mapWidth;
    int mapHeight;
    byte[][] tileMap;
    int playerX;
    int playerY;
    int kingX;
    int kingY;

    public LevelLayout() {}

    public byte[][] getTileMap() {
        return tileMap;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getKingX() {
        return kingX;
    }

    public int getKingY() {
        return kingY;
    }
}
