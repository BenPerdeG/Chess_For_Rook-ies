package com.mygdx.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Blockers extends GameEntity {
    private Texture texture;
    private Vector2 position;

    public Blockers(AssetManager manager, String texturePath, int x, int y) {
        if (!manager.isLoaded(texturePath)) {
            Gdx.app.error("Blockers", "Textura no cargada: " + texturePath);
            texture = new Texture("fallback.png");
        } else {
            texture = manager.get(texturePath, Texture.class);
        }
        this.position = new Vector2(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float aspectRatio = (float)texture.getWidth() / texture.getHeight();
        float drawWidth = TileMap.TILE_SIZE;
        float drawHeight = TileMap.TILE_SIZE / aspectRatio;

        batch.draw(texture,
            position.x * TileMap.TILE_SIZE,
            position.y * TileMap.TILE_SIZE + (TileMap.TILE_SIZE - drawHeight)/2,
            drawWidth,
            drawHeight,
            0, 0,
            texture.getWidth(),
            texture.getHeight(),
            false, true);
    }

    public Vector2 getPosition() {
        return position;
    }
}
