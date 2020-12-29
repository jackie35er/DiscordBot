package commands.games.ticTacToe;

import enmus.Images;
import filehandler.FileHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardImageGenerator {

    private final BufferedImage CROSS = ImageIO.read(FileHandler.readFileFromRessourceAsInputStream(Images.TICTACTOE_CROSS.getFilename()));
    private final BufferedImage CIRCLE = ImageIO.read(FileHandler.readFileFromRessourceAsInputStream(Images.TICTACTOE_CIRCLE.getFilename()));
    private final BufferedImage currentBoard = ImageIO.read(FileHandler.readFileFromRessourceAsInputStream(Images.TICTACTOE_FIELD.getFilename()));

    private final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    public BoardImageGenerator() throws IOException {
    }


    public void add(int x, int y, boolean player) {
        BufferedImage imageToAdd = player ? CROSS : CIRCLE;
        int xCord = x * 500;
        int yCord = y * 500;
        Graphics g = currentBoard.getGraphics();
        g.drawImage(imageToAdd, xCord, yCord, null);
    }

    public File safeToTemp(String filename) throws IOException {
        ImageIO.write(currentBoard, "PNG", new File(TEMP_DIR + filename + ".png"));
        return new File(TEMP_DIR +filename + ".png");
    }

    public boolean deleteFromTemp(String filename) {
        File f = new File(TEMP_DIR + filename);
        return f.delete();
    }
}
