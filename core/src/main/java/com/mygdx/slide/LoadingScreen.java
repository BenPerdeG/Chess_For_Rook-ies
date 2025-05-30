package com.mygdx.slide;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;



public class LoadingScreen implements Screen {

    Chess game;
    float loadProgress;

    LoadingScreen(Chess game)
    {
        this.game = game;
        AssetManager  manager = game.manager;



        // Tableros
        manager.load("Tableros/light-tile.png", Texture.class);
        manager.load("Tableros/dark-tile.png", Texture.class);

        // Background image
        manager.load("libgdx.png", Texture.class);
        manager.load("BG.png", Texture.class);
        manager.load("white.png", Texture.class);
        manager.load("fallback.png", Texture.class);

        for(int i =1; i<11;i++){
            manager.load("gui/lvl"+i+".png", Texture.class);
        }


        //GUI
        manager.load("gui/Button-off.png", Texture.class);
        manager.load("gui/Button-on.png", Texture.class);
        manager.load("gui/pointLeft.png", Texture.class);
        manager.load("gui/pointRight.png", Texture.class);
        manager.load("gui/pointDown.png", Texture.class);
        manager.load("gui/pointUp.png", Texture.class);
        manager.load("gui/Pause-off.png", Texture.class);
        manager.load("gui/Pause-on.png", Texture.class);

        manager.load("Piezas/B_Pawn.png",Texture.class);
        manager.load("Piezas/B_Rook.png",Texture.class);
        manager.load("Piezas/W_King.png",Texture.class);


        loadProgress = 0f;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Render step =============================================
        float currentLoadProgress = game.manager.getProgress();
        if(currentLoadProgress > loadProgress + 0.05f)
        {
            loadProgress = currentLoadProgress;

            game.camera.update();
            game.batch.setProjectionMatrix(game.camera.combined);
            game.textBatch.setProjectionMatrix(game.textCamera.combined);
            game.shapeRenderer.setProjectionMatrix(game.camera.combined);

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
        if(game.manager.update())
        {
            game.setScreen(new MainMenuScreen(game));
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
