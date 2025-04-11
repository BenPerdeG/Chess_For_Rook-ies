package com.mygdx.slide;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.slide.jsonloaders.LevelLayout;

import java.awt.Rectangle;

public class GameEntity extends Actor
{

    protected TileMap map;

    public GameEntity()
    {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

}
