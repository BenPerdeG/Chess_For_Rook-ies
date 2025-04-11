package com.mygdx.slide;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.slide.jsonloaders.LevelLayout;

public class TileMap {
    public static final int TILE_SIZE = 64;
    int width;
    int height;
    byte tiles[][];
    AssetManager manager;
    SpriteBatch batch;

    public TileMap(AssetManager manager, SpriteBatch batch)
    {
        this.manager = manager;
        this.batch = batch;
    }


    void loadFromLevel(LevelLayout l)
    {
        // Load from json file
        width = l.getMapWidth();
        height = l.getMapHeight();

        tiles = new byte[height][];

        for(int i = 0; i < height; i++)
        {
            tiles[i] = new byte[width];
        }

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = l.getTileMap()[i][j];
            }
        }
    }

}
