import controllers.GameController;
import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayerCountException;
import models.*;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException {
        int dimension = 4;
        Player p1 = new Player("Abhinav", new Symbol('A'), PlayerType.HUMAN);
        Player p2 = new Player("Bharghav", new Symbol('X'), PlayerType.BOT);
        Player p3 = new Player("Charlie", new Symbol('O'), PlayerType.HUMAN);


        List<Player> players = List.of(p1,p2,p3);

        List<WinningStrategy> winningStrategies = new ArrayList<>();


        /*
        interact with the controller
         */

        GameController gameController = new GameController();

        Game game = gameController.startGame(4, players,winningStrategies);



        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            /*
            1. Print the board
            2. Ask the current user to choose where to play? - make the move
             */
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
