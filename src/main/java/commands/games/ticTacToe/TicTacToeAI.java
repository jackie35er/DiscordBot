package commands.games.ticTacToe;

import commands.games.MinimaxAI;
import org.apache.commons.collections4.list.TreeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicTacToeAI extends MinimaxAI<Field> {
    public TicTacToeAI(List<Field> field) {
        super(new TreeList<>(field));
        if(field.size() != 9){
            throw new IllegalArgumentException("size of field has to be 9" );
        }
    }

    public TicTacToeAI() {
        List<Field> fieldList = new TreeList<>();
        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3; j++){
                fieldList.add(new Field(i,j));
            }
        }
        super.setField(fieldList);
    }

    @Override
    public boolean isGameOver(List<Field> field) {
        return isWin(field, true) || isWin(field, false) || squaresLeft(field) == 0;
    }


    @Override
    public int evaluatePosition(List<Field> field) {
        int multiplier = 0;
        if(isWin(field,true)){
            multiplier = 1;
        }
        else if(isWin(field,false)){
            multiplier = -1;
        }
        return squaresLeft(field) * multiplier;
    }

    @Override
    public List<List<Field>> getNextMoves(List<Field> field, boolean player) {
        List<List<Field>> out = new ArrayList<>();
        int state = player ? 1 : -1;

        if(squaresLeft(field) == 0){
            return out;
        }
        for(int i = 0; i< field.size();i++){
            if(field.get(i).getState() == 0){
                out.add(deepCopyField());
                out.get(out.size()-1).get(i).setState(state);
            }
        }
        return out;
    }

    public boolean isWin(List<Field> field,boolean player){
        Collections.sort(field);// not needed because field is a tree list,but just in case
        boolean winX = true;
        boolean winY = true;
        int state = player ? 1 : -1;
        //checking x
        for(int i = 0; i < 3; i++){
            for(int j = 0;j <3; j++){
                if (winX && state != field.get((3 * i) + j).getState()) {
                    winX = false;
                }
                else if(winY && state != field.get((3*j)+i).getState()){
                    winY = false;
                }
                if(!winX && !winY){
                    break;
                }
            }
            if(!winX && !winY){
                winX = true;
                winY = true;
                continue;
            }
            return true;
        }
        return false;

    }


    private int squaresLeft(List<Field> field){
        int count = 0;
        for(Field f : field){
            if(f.getState() == 0){
                count++;
            }
        }
        return count;
    }


}
