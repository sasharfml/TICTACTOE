package tictactoe;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

public enum Seed {
    CROSS("X", "src/tictactoe/A.png", "src/tictactoe/topi.png", "src/tictactoe/telorhijau.png"),
    NOUGHT("O", "src/tictactoe/G.png", "src/tictactoe/tongkat.png", "src/tictactoe/telorpink.png"),
    NO_SEED(" ", "src/tictactoe/correct.gif", null, null);

    private String displayName;
    private Image defaultImg;
    private Image alternateImg;
    private Image alternateDua;
    private Image currentImg;

    private Seed(String name, String defaultImageFilename, String alternateImageFilename, String alternateDuaFileName) {
        this.displayName = name;
        this.defaultImg = loadImage(defaultImageFilename);
        this.alternateImg = loadImage(alternateImageFilename);
        this.alternateDua = loadImage(alternateDuaFileName);
        this.currentImg = defaultImg;
    }

    private Image loadImage(String imageFilename) {
        if (imageFilename != null) {
            File imgFile = new File(imageFilename);
            if (imgFile.exists()) {
                return new ImageIcon(imageFilename).getImage();
            } else {
                System.err.println("Couldn't find file " + imageFilename);
            }
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Image getImage() {
        return currentImg;
    }

    public void toggleImage() {
        currentImg=alternateImg;
    }

    public void toggleImageDua() {
        currentImg=alternateDua;
    }

    public void toggleImageSatu() {
        currentImg=defaultImg;
    }
}