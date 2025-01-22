package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategyAlgo implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> colHashmaps = new HashMap<>();
    //({(rowNo, ()} , {} , {} )
    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        //0

        //putting a hashmap for the row
        if(!colHashmaps.containsKey(col)){
            colHashmaps.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> hm = colHashmaps.get(col);
        //if symbol doesn't exist, use 0
        hm.put(symbol, hm.getOrDefault(symbol, 0) + 1);

        int count = hm.get(symbol);
        return count == board.getDimension();
    }

    @Override
    public void handleUndo(Move move) {
        //HW
    }
}
