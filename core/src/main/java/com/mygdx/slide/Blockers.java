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
        float drawWidth = TileMap.TILE_SIZE * 0.8f; // Ajustar tamaño si es necesario
        float drawHeight = drawWidth / aspectRatio; // Mantener proporción

        // Calcular posición centrada
        float centerX = position.x * TileMap.TILE_SIZE + TileMap.TILE_SIZE / 2f;
        float centerY = position.y * TileMap.TILE_SIZE + TileMap.TILE_SIZE / 2f;

        // Dibujar desde el centro del sprite
        batch.draw(texture,
            centerX - drawWidth / 2f,  // X: centro - mitad del ancho
            centerY - drawHeight / 2f, // Y: centro - mitad del alto
            drawWidth,
            drawHeight);
    }

    public Vector2 getPosition() {
        return position;
    }
}
