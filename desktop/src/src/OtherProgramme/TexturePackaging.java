package src.OtherProgramme;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackaging {
    public static void main(String[] args){
        TexturePacker.process("RessourcesTiles/", "RessourcesTileset/", "textures");
    }
}