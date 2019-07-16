package src;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackaging {
    public static void main(String[] args){
        TexturePacker.process("Ressources/TilesetGamme/", "assets/", "TilesetGame");
    }
}