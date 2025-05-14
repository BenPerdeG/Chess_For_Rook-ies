package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class PauseMenuScreen implements Screen {
    final Chess game;
    final Level level;
    ButtonLayout pauseMenu;

    public PauseMenuScreen(Chess game, Level level) {
        this.game = game;
        this.level = level;

        pauseMenu = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        pauseMenu.loadFromJson("pausemenu.json");
    }

    @Override
    public void render(float delta) {
        // Dibujar el nivel en pausa (oscurecido)
        level.render(delta);

        // Dibujar overlay oscuro
        game.batch.begin();
        game.batch.setColor(0, 0, 0, 0.5f);
        game.batch.draw(game.manager.get("white.png", Texture.class), 0, 0, 800, 480);
        game.batch.setColor(1, 1, 1, 1);
        game.batch.end();

        // Dibujar menú de pausa
        game.textBatch.begin();
        game.bigFont.draw(game.textBatch, "PAUSA", 300, 300);
        game.textBatch.end();

        pauseMenu.render(game.batch, game.textBatch);

        if (pauseMenu.consumeRelease("Restart")) {
            game.setScreen(new Level(game, level.nivel));
            dispose();
        }
        if (pauseMenu.consumeRelease("MainMenu")) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
