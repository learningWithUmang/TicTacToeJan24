package models;
import java.util.*;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public Board(int dimension){
        //5*5 => Cannot i build a List<List<Cell>> of 5*5 size?
        this.dimension = dimension;
        this.board = new ArrayList<>(); //[] -> [[cell1, cell2, cell3],[cell4....],[...],[...]]
        for(int i = 0 ; i < dimension ; i++){
            this.board.add(new ArrayList<>());
            for(int j = 0 ; j < dimension ; j++){
                this.board.get(i).add(new Cell(i,j));
            }
        }
    }

    void print(){
        //[[],[],[],[]...]
        for(List<Cell> cells: board){
           for(Cell cell: cells){
               if(cell.getCellState().equals(CellState.EMPTY)){
                   System.out.print("|   |"); //|  |
               }else{
                   System.out.print("| " + cell.getPlayer().getSymbol().getaChar() + " |"); // | O |
               }
           }
            System.out.println();
        }
    }
}
