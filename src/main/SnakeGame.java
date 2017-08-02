package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import za.ac.wits.snake.DevelopmentAgent;

public class SnakeGame extends DevelopmentAgent {

    public static void main(String args[]) throws IOException {
        SnakeGame agent = new SnakeGame();
        SnakeGame.start(agent, args);
    }

    @Override
    public void run() {
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            
            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }
                GameMap map = new GameMap(br, line);
                //finished reading, calculate move:
                //System.out.println("log calculating...");
                Snake snake = new Snake();
                int move = snake.getMove(map.getMap(), map.myHead, map);
                System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}