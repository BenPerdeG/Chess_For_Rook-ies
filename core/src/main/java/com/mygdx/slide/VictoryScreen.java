// VictoryScreen.java
package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class VictoryScreen implements Screen {
    final Chess game;
    ButtonLayout buttons;

    public VictoryScreen(Chess game) {
        this.game = game;
        buttons = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        // Configurar botones de "Volver a jugar" y "Salir"
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(game.manager.get("BG.png", Texture.class), 0, 0);
        game.batch.end();

        game.textBatch.begin();
        game.bigFont.draw(game.textBatch, "¡VICTORIA!", 300, 240);
        game.textBatch.end();

        buttons.render(game.batch, game.textBatch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
