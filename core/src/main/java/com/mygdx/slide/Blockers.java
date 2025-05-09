package com.mygdx.slide;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Blockers extends GameEntity {
    private Texture texture;
    private Vector2 position;

    public Blockers(AssetManager manager, String texturePath, int x, int y) {
        this.texture = manager.get(texturePath, Texture.class);
        this.position = new Vector2(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.begin();
        batch.draw(texture,
            position.x * TileMap.TILE_SIZE,
            position.y * TileMap.TILE_SIZE,
            TileMap.TILE_SIZE,
            TileMap.TILE_SIZE);
        batch.end();
    }

    public Vector2 getPosition() {
        return position;
    }
}
