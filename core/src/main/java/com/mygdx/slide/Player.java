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
    private float slideSpeed = 10f; // Velocidad de deslizamiento

    public Player(AssetManager manager) {
        this.manager = manager;
        this.currentFrame = manager.get("Piezas/W_Rook.png", Texture.class);
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
            startSlide(0, -1); // Arriba (eje Y invertido en LibGDX)
        }
        else if(joypad.consumePush("Down")) {
            startSlide(0, 1);
        }
        else if(joypad.consumePush("Left")) {
            startSlide(-1, 0);
        }
        else if(joypad.consumePush("Right")) {
            startSlide(1, 0);
        }
    }

    // En Player.java
    private void startSlide(int dirX, int dirY) {
        if(state != State.IDLE) return;

        // Calcular posición de destino
        int newX = (int)position.x;
        int newY = (int)position.y;

        // Buscar hasta el próximo obstáculo
        while(true) {
            int nextX = newX + dirX;
            int nextY = newY + dirY;

            // Verificar colisión con bordes, obstáculos o piezas aliadas
            if(!map.isWalkable(nextX, nextY) ||
                map.hasAllyPiece(nextX, nextY)) { // Necesitarás implementar este método
                break;
            }

            // Verificar si encontramos al rey enemigo (victoria)

            newX = nextX;
            newY = nextY;
        }

        // Si hay movimiento posible
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
        batch.draw(currentFrame,
            position.x * TileMap.TILE_SIZE,
            position.y * TileMap.TILE_SIZE,
            TileMap.TILE_SIZE,
            TileMap.TILE_SIZE);
    }

    // Métodos auxiliares para colisiones
    public Vector2 getPosition() {
        return position;
    }

    public boolean isSliding() {
        return state == State.SLIDING;
    }
}
