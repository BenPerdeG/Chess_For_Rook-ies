package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LevelSelect implements Screen {
    Chess game;
    ButtonLayout levelSelect;

    public LevelSelect(Chess game) {
        this.game= game;
        levelSelect = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        levelSelect.loadFromJson("levelSelect.json");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(game.manager.get("BG.png", Texture.class), -10, 20, 800, 500, 0,0, 800, 480, false, true);
        game.batch.end();

        game.textBatch.begin();
        game.bigFont.draw(game.textBatch,"Level Select", 30, 480 - 60);
        game.smallFont.draw(game.textBatch,"(c) Puig Castellar 2025", 100, 480 - 420);
        game.textBatch.end();

        levelSelect.render(game.batch, game.textBatch);


//        // Start the game!
        if(levelSelect.consumeRelease("Start"))
        {

            game.setScreen(new LevelSelect(game));
            this.dispose();
        }

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
