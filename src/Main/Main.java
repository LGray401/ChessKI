package Main;
import java.util.logging.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1000;
        String fenString = "r3k2r/pp6/2p3Pb/2N1pP2/Q2p4/4P3/PP1K4/7R";
        //Benchmark benchmark = new Benchmark();
        //System.out.println(benchmark.benchmarkOneMove(fenString, 100000) );

        Board.simulateGame(fenString, player1, player2, amountOfMoves);

        // Create a Logger
        //Logger logger
          //      = Logger.getLogger(
             //   Main.class.getName());
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("C:/temp/test/MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Hi How r u?");

        // log messages using log(Level level, String msg)
      //  logger.log(Level.INFO, "This is message 1");
     //   logger.log(Level.WARNING, "This is message 2");
      //  logger.addHandler(new StreamHandler(System.out, new SimpleFormatter()));


    }

}