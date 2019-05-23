package com.actor;

public class Camera {

  private float cameraX = 0F;
  private float cameraY = 0F;

  public void update(float newCamX, float newCamY){
    this.cameraX = newCamX;
    this.cameraY = newCamY;
  }

  public float getCameraX(){
    return cameraX;
  }
  public float getCameraY(){
    return cameraY;
  }
}
