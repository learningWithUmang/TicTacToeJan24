package models;
import exceptions.InvalidBotCountException;
import exceptions.InvalidMoveException;
import exceptions.InvalidPlayerCountException;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerTurnIndex;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies; //the rules of the game
    /*
    across the rows
    across the columns
     */

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.nextPlayerTurnIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    public void printBoard(){
        board.print();
    }

    private boolean validateMove(Move dummyMove){
        int row = dummyMove.getCell().getRow();
        int col = dummyMove.getCell().getCol();

        //Boundary conditions
        if(row < 0 || col < 0 || row >= board.getDimension() || col >= board.getDimension()){
            return false;
        }

        //confirm if the cell is empty
        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return false;
        }

        return true;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerTurnIndex);

        System.out.println("It is " + currentPlayer.getName() + "'s turn");

        //Ask the player to choose the cell where he/she wants to make the move?

        Move dummyMove = currentPlayer.makeMove(board);
        //Before executing this move, first validate if the cell is not empty?
        if (!validateMove(dummyMove)) {
            throw new InvalidMoveException("The move is invalid");
        }

        //Valid move, execute it on the board? update??
        int row = dummyMove.getCell().getRow();
        int col = dummyMove.getCell().getCol();
        //update the actual cell object present at (row,col)

        Cell cell = board.getBoard().get(row).get(col);
        cell.setPlayer(currentPlayer);
        cell.setCellState(CellState.FILLED);

        //[p1,p2,p3,p4] => nextPlayerTurnIndex = 0,1,2,3,0,1,2,3,0,1,2,3,...
        nextPlayerTurnIndex = (nextPlayerTurnIndex + 1) % players.size();

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        if (checkWinner(finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }
    //N*N => n^2
    //check every cell in board is filled?

    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(move, board)){
                return true;
            }
        }
        return false;
    }

    public void undo(){
        /*

         */
        if(moves.size() == 0){
            System.out.println("Undo is not allowed since there are no moves played as of now");
            return;
        }

        Move lastMove = moves.getLast();
        moves.remove(lastMove); //undo - removing the last move?

        //update the cell state in the board?
        //update the symbols count in rows, columns, diagonals where applicable
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        //handle undo for each winning strategy
        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(lastMove);
            //handle, update the counts
        }

        nextPlayerTurnIndex = (nextPlayerTurnIndex - 1 + players.size()) % players.size();
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        //Game game = Game.getBuilder().setdimension(12).setplayers().setWinning().build();

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new InvalidBotCountException("The game has more than one bot players which is not allowed");
            }
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension - 1){
                throw new InvalidPlayerCountException("No of players should be equal to one dimension - 1");
            }
        }

        private void validateUniqueSymbolsForAllPlayers(){

        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException {
            validateBotCount();
            validatePlayerCount();
            validateUniqueSymbolsForAllPlayers();
        }

        public Game build() throws InvalidBotCountException, InvalidPlayerCountException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerTurnIndex() {
        return nextPlayerTurnIndex;
    }

    public void setNextPlayerTurnIndex(int nextPlayerTurnIndex) {
        this.nextPlayerTurnIndex = nextPlayerTurnIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}

/*
Why builder?
we would want to perform some validations before creating the game.
1. Board
2. Players

N*N board, how many players? => N - 1
 */
