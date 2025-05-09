package com.mygdx.slide;

import static com.mygdx.slide.TileMap.TILE_SIZE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.slide.jsonloaders.LevelLayout;

import java.util.ArrayList;
import java.util.List;

public class Level implements Screen {
    Chess game;
    LevelLayout nivel;
    TileMap tileMap;
    Player player;
    ButtonLayout joypad;
    private List<Blockers> allyPieces;
    // Texturas
    Texture lightTile;
    Texture darkTile;
    Texture wallTile;
    private Texture kingTexture;

    public Level(Chess game, LevelLayout nivel) {
        this.game = game;
        this.nivel = nivel;


        // Inicializar el tilemap
        tileMap = new TileMap(game.manager, game.batch);
        tileMap.loadFromLevel(nivel);

        // Crear al jugador
        player = new Player(game.manager, this);
        player.setMap(tileMap);

        // Configurar controles
        joypad = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        joypad.loadFromJson("joypad.json");
        player.setJoypad(joypad);
        player.setInitialPosition(nivel.getPlayerX(), nivel.getPlayerY());


        // Cargar texturas de ajedrez
        lightTile = game.manager.get("Tableros/light-tile.png", Texture.class);
        darkTile = game.manager.get("Tableros/dark-tile.png", Texture.class);

        allyPieces = new ArrayList<>();

        // Cargar piezas aliadas (valor 1 en el tileMap)
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if(nivel.getTileMap()[y][x] == 1) {
                    allyPieces.add(new Blockers(game.manager, "Piezas/B_Pawn.png", x, y));
                }
            }
        }

        kingTexture = game.manager.get("Piezas/W_King.png", Texture.class);
    }
    public List<Blockers> getAllyPieces() {
        return allyPieces;
    }

    @Override
    public void render(float delta) {
        // Limpiar pantalla
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(Blockers blocker : allyPieces) {
            blocker.draw(game.batch, 1.0f);
        }

        // Actualizar lógica
        update(delta);

        // Dibujar el nivel
        draw();
    }

    private void update(float delta) {
        player.act(delta);
        joypad.update();

        if(player.getPosition().epsilonEquals(new Vector2(nivel.getKingX(), nivel.getKingY()), 0.1f)) {
            game.levelCompleted();
        }
    }

    private void draw() {
        game.batch.begin();

        // Dibujar tablero
        for (int y = 0; y < tileMap.height; y++) {
            for (int x = 0; x < tileMap.width; x++) {
                // Alternar tiles automáticamente
                boolean isLight = (x + y) % 2 == 0;
                tileMap.batch.draw(isLight ? lightTile : darkTile,
                    x * TILE_SIZE, y * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE);

                // Dibujar muros si corresponde
                if (tileMap.tiles[y][x] == 1) {
                    tileMap.batch.draw(wallTile, x * TILE_SIZE, y * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Dibujar al jugador
        player.draw(game.batch, 1.0f);
        game.batch.draw(kingTexture,
            nivel.getKingX() * TILE_SIZE,
            nivel.getKingY() * TILE_SIZE,
            TILE_SIZE,
            TILE_SIZE);
        game.batch.end();

        // Dibujar UI/controles
        joypad.render(game.batch, game.textBatch);
    }

    // Resto de métodos necesarios de la interfaz Screen...
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        lightTile.dispose();
        darkTile.dispose();
        wallTile.dispose();
    }

}
