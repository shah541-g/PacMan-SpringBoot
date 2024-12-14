import javax.swing.JFrame;

// import Game.MultiPlayerGame.MultiPlayerGameBoard;
// import Game.MultiPlayerGame.MultiPlayerGame;
// import Game.MultiPlayerGame.MultiPlayerGameController;
import Game.SinglePlayerGame.SinglePayerGameController;
import Game.SinglePlayerGame.SinglePlayerGame;
import Game.SinglePlayerGame.SinglePlayerGameBoard;


// jo mujhay hal nazar aa raha ha wo ye ha k ma game ki class ko exactly sam way may jpanel say extend or keylistener or actionlistener say implement kerwaon

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("hello");

        
        JFrame fram = new JFrame("Pac Man");
        System.out.println(SinglePlayerGameBoard.getBoardWidth());
        fram.setSize(SinglePlayerGameBoard.getBoardWidth(),SinglePlayerGameBoard.getBoardHeight());
        fram.setLocationRelativeTo(null);
        fram.setResizable(false);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MultiPlayerGameController gameController = new MultiPlayerGameController();
        // MultiPlayerGame game = new MultiPlayerGame(gameController);
        SinglePayerGameController gameController = new SinglePayerGameController();
        SinglePlayerGame game = new SinglePlayerGame(gameController);
        gameController.init(game);
        gameController.startGame();
        
        fram.add(game.getGamePanel());
        fram.pack();
        game.getGamePanel().requestFocusInWindow();
        fram.setVisible(true); 
    

    }
}
