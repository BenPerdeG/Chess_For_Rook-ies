package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.mygdx.slide.jsonloaders.LevelLayout;

public class Level implements Screen {

    Chess game;
    LevelLayout nivel;

    public Level(Chess game, LevelLayout nivel) {
        this.game = game;
        this.nivel = nivel;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
