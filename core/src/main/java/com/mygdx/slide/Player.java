package com.mygdx.slide;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends GameEntity{

    AssetManager manager;
    ButtonLayout joypad;
    Texture currentFrame;

    public Player(AssetManager manager)
    {

        this.manager = manager;
        currentFrame = manager.get("Piezas/W_Rook.png", Texture.class);

    }


    public void setJoypad(ButtonLayout joypad) {
        this.joypad = joypad;
    }

    public void act(float delta) {
        super.act(delta);

            if(joypad.consumePush("Up")) {

            }

            if (joypad.isPressed("Left")) {

            }

            if(joypad.consumePush("Up")) {

            }

            if (joypad.isPressed("Left")) {

            }
    }




    public void kill()
    {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


    }

    // Draw collision box
    public void drawDebug(ShapeRenderer shapes) {

    }

}
