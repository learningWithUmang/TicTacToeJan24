import controllers.GameController;
import exceptions.InvalidBotCountException;
import exceptions.InvalidMoveException;
import exceptions.InvalidPlayerCountException;
import models.*;
import strategies.winningStrategies.ColWinningStrategyAlgo;
import strategies.winningStrategies.DiagonalWinningStrategyAlgo;
import strategies.winningStrategies.RowWinningStrategyAlgo;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidMoveException {
        int dimension = 3;
        Player p1 = new Player("Umang", new Symbol('O'), PlayerType.HUMAN);
        Player p2 = new Bot("BOT", new Symbol('X'), BotDifficultyLevel.EASY);

        List<Player> players = List.of(p1,p2);

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategyAlgo());
        winningStrategies.add(new ColWinningStrategyAlgo());
        winningStrategies.add(new DiagonalWinningStrategyAlgo());


        /*
        interact with the controller
         */

        GameController gameController = new GameController();

        Game game = gameController.startGame(dimension, players,winningStrategies);

        Scanner scanner = new Scanner(System.in);

        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            /*
            1. Print the board
            2. Ask the current user to choose where to play? - make the move
             */
            System.out.println("If you want to undo the last move? (y/n)");
            String response = scanner.next();

            if(response.equalsIgnoreCase("y")){
                gameController.unDo(game);
                continue;
            }

            gameController.printBoard(game);
            gameController.makeMove(game);
        }



        if(gameController.getGameState(game).equals(GameState.ENDED)){
            gameController.printBoard(game);
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        }else{
            System.out.println("Game is a draw");
        }

        /*
        understand the high level scenarios you want your clients to use?
        Controller
         */

    }
}
