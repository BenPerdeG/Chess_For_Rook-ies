package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadingScreen implements Screen {

    Chess game;
    float loadProgress;


    public LoadingScreen(Chess chess) {

        this.game = chess;
        AssetManager manager = game.manager;

        //Cargar todos los componentes
        manager.load("Piezas/B_Rook.png", Texture.class);
        manager.load("Piezas/B_Bishop.png", Texture.class);

        //
        loadProgress = 0f;
    }


    @Override
    public void render(float delta) {
        float currentLoadProgress = game.manager.getProgress();
        if (currentLoadProgress > loadProgress + 0.05f) {
            loadProgress = currentLoadProgress;


            ScreenUtils.clear(Color.BLACK);

            // Progress bar
            game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.shapeRenderer.setColor(Color.WHITE);
            game.shapeRenderer.rect(90, 290, 620, 100);
            game.shapeRenderer.setColor(Color.BLACK);
            game.shapeRenderer.rect(100, 300, 600, 80);
            game.shapeRenderer.setColor(Color.WHITE);
            game.shapeRenderer.rect(110, 310, 580 * loadProgress, 60);
            game.shapeRenderer.end();

            game.textBatch.begin();
            game.bigFont.draw(game.textBatch, "CARREGANT...", 120, 340);
            game.mediumFont.draw(game.textBatch, (int) (loadProgress * 100.f) + "%", 360, 160);
            game.textBatch.end();
        }
        // Update step ====================================
        if (game.manager.update()) {
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }

    }


    @Override
    public void show() {

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
