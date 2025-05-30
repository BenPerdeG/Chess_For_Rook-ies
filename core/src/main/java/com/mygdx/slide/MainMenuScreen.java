package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    final Chess game;

    ButtonLayout mainMenu;

    public MainMenuScreen(Chess chess) {
        game = chess;
        mainMenu = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        mainMenu.loadFromJson("mainmenu.json");
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
        game.bigFont.draw(game.textBatch,"Chess for Rook-ies", 30, 480 - 60);
        game.smallFont.draw(game.textBatch,"(c) Puig Castellar 2025", 100, 480 - 420);
        game.textBatch.end();

        mainMenu.render(game.batch, game.textBatch);


//        // Start the game!
        if(mainMenu.consumeRelease("Start"))
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
