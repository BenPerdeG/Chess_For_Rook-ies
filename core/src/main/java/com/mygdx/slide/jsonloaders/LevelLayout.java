package com.mygdx.slide.jsonloaders;

public class LevelLayout {
    private int mapWidth;
    private int mapHeight;

    private byte tileMap[][];


    public LevelLayout() {
    }
    public LevelLayout(byte[][] tileMap) {
        this.tileMap = tileMap;
    }

    public byte[][] getTileMap() {
        return tileMap;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
