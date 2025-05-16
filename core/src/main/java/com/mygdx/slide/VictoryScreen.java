// VictoryScreen.java
package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;

public class VictoryScreen implements Screen, InputProcessor {
    final Chess game;

    public VictoryScreen(Chess game) {
        this.game = game;
        Gdx.input.setInputProcessor(this); // Solo responde a toques
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        game.batch.draw(game.manager.get("BG.png", Texture.class), -10, 20, 800, 500, 0,0, 800, 480, false, true);
        game.batch.end();


        game.textBatch.begin();
        game.bigFont.draw(game.textBatch, "¡VICTORIA!", 225, 240);
        game.textBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    // Método clave: Cambia de pantalla al tocar cualquier parte
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen(new LevelSelect(game));
        return true; // Indica que el evento fue procesado
    }

    // Métodos vacíos requeridos (solo necesitamos touchDown)
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
