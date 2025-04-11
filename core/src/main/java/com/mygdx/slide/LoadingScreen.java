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

        // Add assets for loading

        // Tableros
        for(int i = 1; i < 6; i++)
            manager.load("Tableros/board_persp_0"+i+".png", Texture.class);

        // Background image
        manager.load("libgdx.png", Texture.class);
        manager.load("BG.png", Texture.class);

        for(int i =1; i<11;i++){
            manager.load("gui/lvl"+i+".png", Texture.class);
        }


        //GUI
        manager.load("gui/Button-off.png", Texture.class);
        manager.load("gui/Button-on.png", Texture.class);
        manager.load("gui/Left-off.png", Texture.class);
        manager.load("gui/Left-on.png", Texture.class);
        manager.load("gui/Right-off.png", Texture.class);
        manager.load("gui/Right-on.png", Texture.class);
        manager.load("gui/Jump-off.png", Texture.class);
        manager.load("gui/Jump-on.png", Texture.class);
        manager.load("gui/Pause-off.png", Texture.class);
        manager.load("gui/Pause-on.png", Texture.class);

        manager.load("gui/pointLeft.png", Texture.class);
        manager.load("gui/pointUP.png", Texture.class);
        manager.load("gui/pointRight.png", Texture.class);
        manager.load("gui/pointDown.png", Texture.class);

        manager.load("Piezas/B_Bishop.png",Texture.class);
        manager.load("Piezas/B_Pawn.png",Texture.class);
        manager.load("Piezas/B_Queen.png",Texture.class);
        manager.load("Piezas/B_Rook.png",Texture.class);
        manager.load("Piezas/BlackPieces.png",Texture.class);
        manager.load("Piezas/BlackPieces_Simplified.png",Texture.class);
        manager.load("Piezas/BlackPieces_WoodSimplified.png",Texture.class);
        manager.load("Piezas/W_Bishop.png",Texture.class);
        manager.load("Piezas/W_King.png",Texture.class);
        manager.load("Piezas/W_Knight.png",Texture.class);
        manager.load("Piezas/W_Pawn.png",Texture.class);
        manager.load("Piezas/W_Queen.png",Texture.class);
        manager.load("Piezas/W_Rook.png",Texture.class);
        manager.load("Piezas/WhitePieces.png",Texture.class);
        manager.load("Piezas/WhitePieces_Simplified.png",Texture.class);
        manager.load("Piezas/WhitePieces_WoodSimplified.png",Texture.class);

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
