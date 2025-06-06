package com.mygdx.slide;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameEntity {
    public enum State {
        IDLE, SLIDING
    }

    private AssetManager manager;
    private ButtonLayout joypad;
    private Texture currentFrame;
    private Vector2 position;
    private Vector2 targetPosition;
    private State state;
    private float slideSpeed = 10f;
    private Level currentLevel;

    public Player(AssetManager manager,  Level level) {
        this.manager = manager;
        this.currentLevel = level;
        this.currentFrame = manager.get("Piezas/B_Rook.png", Texture.class);
        this.position = new Vector2();
        this.targetPosition = new Vector2();
        this.state = State.IDLE;
    }

    public void setJoypad(ButtonLayout joypad) {
        this.joypad = joypad;
    }

    public void setInitialPosition(int x, int y) {
        this.position.set(x, y);
        this.targetPosition.set(x, y);
    }

    @Override
    public void act(float delta) {
        switch(state) {
            case IDLE:
                handleInput();
                break;
            case SLIDING:
                updateSlide(delta);
                break;
        }
    }

    private void handleInput() {
        if(joypad.consumePush("Up")) {
            startSlide(0, 1);
            currentLevel.setMovimientos(currentLevel.getMovimientos()+1);
        }
        else if(joypad.consumePush("Down")) {
            startSlide(0, -1);
            currentLevel.setMovimientos(currentLevel.getMovimientos()+1);
        }
        else if(joypad.consumePush("Left")) {
            startSlide(-1, 0);
            currentLevel.setMovimientos(currentLevel.getMovimientos()+1);
        }
        else if(joypad.consumePush("Right")) {
            startSlide(1, 0);
            currentLevel.setMovimientos(currentLevel.getMovimientos()+1);
        }

    }


    private void startSlide(int dirX, int dirY) {
        if(state != State.IDLE) return;


        int newX = (int)position.x;
        int newY = (int)position.y;

        while(true) {
            int nextX = newX + dirX;
            int nextY = newY + dirY;

            // Verificar límites del tablero
            if(nextX < 0 || nextX >= 8 || nextY < 0 || nextY >= 8) {
                break;
            }

            // Verificar colisiones con piezas aliadas
            boolean blocked = false;
            for(Blockers blocker : currentLevel.getAllyPieces()) {
                if((int)blocker.getPosition().x == nextX &&
                    (int)blocker.getPosition().y == nextY) {
                    blocked = true;
                    break;
                }
            }
            if(blocked) break;

            newX = nextX;
            newY = nextY;
        }

        if(newX != position.x || newY != position.y) {
            targetPosition.set(newX, newY);
            state = State.SLIDING;
        }
    }

    private void updateSlide(float delta) {
        // Mover hacia el objetivo
        Vector2 direction = new Vector2(targetPosition).sub(position).nor();
        position.mulAdd(direction, slideSpeed * delta);

        // Verificar si llegamos al destino
        if(position.epsilonEquals(targetPosition, 0.1f)) {
            position.set(targetPosition);
            state = State.IDLE;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float aspectRatio = (float)currentFrame.getWidth() / currentFrame.getHeight();
        float drawWidth = TileMap.TILE_SIZE * 0.9f;
        float drawHeight = drawWidth / aspectRatio;

        // Ajuste similar para la torre
        float offsetY = (TileMap.TILE_SIZE - drawHeight) * 1.2f;

        batch.draw(currentFrame,
            position.x * TileMap.TILE_SIZE + (TileMap.TILE_SIZE - drawWidth) / 2f,
            position.y * TileMap.TILE_SIZE + offsetY, // Ajuste vertical
            drawWidth,
            drawHeight);
    }
    public Vector2 getPosition() {
        return position;
    }

}
