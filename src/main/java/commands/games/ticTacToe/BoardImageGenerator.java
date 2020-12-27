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

    private final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    private final BufferedImage currentBoard = ImageIO.read(FileHandler.readFileFromRessourceAsInputStream(Images.TICTACTOE_FIELD.getFilename()));
    public BoardImageGenerator() throws IOException {
    }


    public void add(int x, int y,boolean player){
        BufferedImage imageToAdd = player ? CROSS : CIRCLE;
        int xCord = x * 500;
        int yCord = y * 500;
        Graphics g = currentBoard.getGraphics();
        g.drawImage(imageToAdd,xCord,yCord,null);
    }

    public void safeToTemp(String filename){
        try {
            ImageIO.write(currentBoard,"PNG",new File(TEMP_DIR + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFromTemp(String filename){
        File f = new File(TEMP_DIR + filename);
        return f.delete();
    }
}
