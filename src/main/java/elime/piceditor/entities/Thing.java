package elime.piceditor.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Created by Elime on 15-08-16.
 */

@Data
public class Thing {
    @Setter(AccessLevel.NONE) private Pic pic;
    private Sprite[] sprites;
    private int width;
    private int height;
    private PicColor bgColor;

    public Thing(Pic pic) {
        this.pic = pic;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setSprites(Sprite[] sprites) {
        if (this.sprites != null) {
            //We need to make sure the pic byte size is correct when we replace sprites
            pic.setNumberOfBytes(pic.getNumberOfBytes() - getSpriteDataByteSize());
            this.sprites = sprites;
            pic.setNumberOfBytes(pic.getNumberOfBytes() + getSpriteDataByteSize());
        } else {
            this.sprites = sprites;
        }
    }

    public int getSpriteDataByteSize() {
        int nBytes = 0;
        for (Sprite sprite : sprites) {
            nBytes += sprite.getPixelData().length + Integer.BYTES + Short.BYTES;
        }
        return nBytes;
    }

    public int getPixelWidth() {
        return width * Sprite.DEFAULT_W_H;
    }

    public int getPixelHeight() {
        return height * Sprite.DEFAULT_W_H;
    }

}
