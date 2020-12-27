package commands.games;

import interfaces.Copyable;
import net.dv8tion.jda.internal.utils.tuple.ImmutablePair;
import net.dv8tion.jda.internal.utils.tuple.MutablePair;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.apache.commons.collections4.list.TreeList;


import java.util.ArrayList;
import java.util.List;

public abstract class  MinimaxAI<F extends Copyable<F>> {

    private List<F> field = new ArrayList<>();

    public MinimaxAI(List<F> field) {
        this.field = field;
    }
    public MinimaxAI(){

    }

    public List<F> getField() {
        return new TreeList<>(field);
    }

    public void setField(List<F> field) {
        this.field = field;
    }

    /**
     * Checkt ob das Game zu ende ist
     * @return true bei Unentschieden oder bei einem Gewinner, sonst false
     */
    public abstract boolean isGameOver(List<F> field);

    public abstract int evaluatePosition(List<F> field);

    /**
     * Returns all possible Moves for the given Field
     * @param field field to calculate the moves for
     * @return List of the moves
     */
    public abstract List<List<F>> getNextMoves(List<F> field,boolean player);

    public Pair<Integer,List<F>> minimax(List<F> field, int depth,int alpha,int beta, boolean player){
        if(depth == 0 || this.isGameOver(field)){
            return Pair.of(evaluatePosition(field),field);
        }
        if(player){
            int maxEval = Integer.MIN_VALUE;
            List<F> maxFieldState = null;
            for(List<F> curField : getNextMoves(field,true)){
                Pair<Integer,List<F>> eval = minimax(curField,depth-1,alpha,beta, false);
                if(maxEval < eval.getLeft()){
                    maxEval = eval.getLeft();
                    maxFieldState = eval.getRight();
                }
                alpha = Integer.max(alpha,eval.getLeft());
                if(beta <= alpha){
                    break;
                }
            }
            return new MutablePair<>(maxEval,maxFieldState);
        }
        else{
            int minEval = Integer.MAX_VALUE;
            List<F> minFieldState = null;
            for(List<F> curField : getNextMoves(field,false)){
                Pair<Integer,List<F>> eval = minimax(curField,depth-1,alpha,beta,true);
                if(minEval > eval.getLeft()){
                    minEval = eval.getLeft();
                    minFieldState = eval.getRight();
                }
                beta = Integer.min(beta,eval.getLeft());
                if(beta <= alpha){
                    break;
                }
            }
            return new MutablePair<>(minEval,minFieldState);
        }

    }
    public Pair<Integer,List<F>> minimax(List<F> field,int depth, boolean player){
        return minimax(field,depth,Integer.MIN_VALUE,Integer.MAX_VALUE,player);
    }
    public List<F> nextMove(List<F> field,int depth, boolean player){
        return minimax(field,depth,player).getRight();
    }

    public List<F> deepCopyField(){
        List<F> out = new TreeList<>();
        for (F f : field) {
            out.add(f.copy());
        }
        return out;
    }
}
