package com.openworld.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GettercollisionLayer {

    private final TiledMap tiledMap;
    private final Array<Collision_Area> collision_areas;

    public static final String TAG = Map.class.getSimpleName();

    public GettercollisionLayer(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        collision_areas = new Array<Collision_Area>();

        parseCollisionLayer();
    }

    private void parseCollisionLayer() {
        final MapLayer collisionLayer = tiledMap.getLayers().get("Collision");
        if(collisionLayer == null){
            Gdx.app.debug(TAG, "Error no collision layer");
            return;
        }

        final MapObjects mapObjects = collisionLayer.getObjects();
        if (mapObjects==null){
            Gdx.app.debug(TAG, "There are no collision define !!");
            return;
        }
        for (final MapObject mapObject : mapObjects){
            if (mapObject instanceof RectangleMapObject){
                final RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
                final Rectangle rectangle = rectangleMapObject.getRectangle();
                final float[] rectVerticies = new float[10];
                //left bot
                rectVerticies[0] = 0;
                rectVerticies[1] = 0;
                //left top
                rectVerticies[2] = 0;
                rectVerticies[3] = rectangle.height;
                //right bot
                rectVerticies[4] = rectangle.width;
                rectVerticies[5] = rectangle.height;
                //right bot
                rectVerticies[6] = rectangle.width;
                rectVerticies[7] = 0;
                //left bot
                rectVerticies[8] = 0;
                rectVerticies[9] = 0;

                collision_areas.add(new Collision_Area(rectangle.x, rectangle.y, rectVerticies));

            } else {
                Gdx.app.debug(TAG, "Error, MapObject non supported by the layer");
                return;
            }
        }
    }
    public Array<Collision_Area> getCollision_areas() { return collision_areas; }
}
