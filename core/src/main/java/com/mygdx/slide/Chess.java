package com.mygdx.slide;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.slide.jsonloaders.LevelJsonLoader;
import com.mygdx.slide.jsonloaders.LevelLayout;

public class Chess extends Game {
    public ShapeRenderer shapeRenderer;
    public SpriteBatch batch, textBatch;
    BitmapFont smallFont, mediumFont, bigFont;
    AssetManager manager;
    public OrthographicCamera camera, textCamera;
    public int currentLevel = 1;
    public static final int TOTAL_LEVELS = 10;
    @Override
    public void create() {

        manager = new AssetManager();
        batch = new SpriteBatch();
        textBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        FreeTypeFontGenerator generator =
            new FreeTypeFontGenerator(Gdx.files.internal("8bitOperatorPlus-Bold.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter params =
            new FreeTypeFontGenerator.FreeTypeFontParameter();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        params.size = 10;
        params.borderWidth = 2;
        params.borderColor = Color.BLACK;
        params.color = Color.WHITE;
        smallFont = generator.generateFont(params);

        params.size = 22;
        params.borderWidth = 4;
        params.borderColor = Color.BLACK;
        params.color = Color.WHITE;
        mediumFont = generator.generateFont(params); // font size 22 pixels

        params.size = 50;
        params.borderWidth = 5;
        bigFont = generator.generateFont(params); // font size 50 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 480);

        textCamera = new OrthographicCamera();
        textCamera.setToOrtho(false, 800, 480);
        textCamera.translate(-400,-240);

        textBatch.setProjectionMatrix(textCamera.projection);
        this.setScreen(new LoadingScreen(this));
        //this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    public void levelCompleted() {
        currentLevel++;
        if(currentLevel <= TOTAL_LEVELS) {
            LevelLayout nextLevel = LevelJsonLoader.loadLevel("level" + currentLevel + ".json");
            setScreen(new Level(this, nextLevel));
        } else {
            setScreen(new VictoryScreen(this)); // Necesitarás crear esta pantalla
        }
    }
}
