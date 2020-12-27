package enmus;


public enum Images {
    TICTACTOE_CROSS("images/TicTacToe/cross.png"),
    TICTACTOE_FIELD("images/TicTacToe/field.png"),
    TICTACTOE_CIRCLE("images/TicTacToe/circle.png");


    private String filename;



    private Images(String filename){
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        return filename;
    }
}
