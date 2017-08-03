package main;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import za.ac.wits.snake.DevelopmentAgent;

public class SnakeGame extends DevelopmentAgent {

    Point sapple = new Point();
    Point napple = new Point();
    
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
                map myMap = new map();
                String [] apple1 = line.split(" ");
                if (!apple1[0].equals("-1")) {
                    myMap.gameMap[Integer.parseInt(apple1[1])][Integer.parseInt(apple1[0])] = '*';
                    sapple.x = Integer.parseInt(apple1[1]);
                    sapple.y = Integer.parseInt(apple1[0]);
                }
                String [] apple2 = br.readLine().split(" ");
                if (!apple2[0].equals("-1")) {
                    myMap.gameMap[Integer.parseInt(apple2[1])][Integer.parseInt(apple2[0])] = '*';
                    napple.x = Integer.parseInt(apple2[1]);
                    napple.y = Integer.parseInt(apple2[0]);
                }
                int mySnakeNumber = Integer.parseInt(br.readLine());
                Snake [] snakes = new Snake [3];
                Snake mySnake;
                String mySnakeLine="";
                for (int i = 0; i < 4; i++) {
                    if (i==mySnakeNumber) {
                        //*1
                        mySnakeLine = br.readLine();
                    } else if (i < mySnakeNumber) {
                        snakes[i] = new Snake(br.readLine().split(" "));
                        myMap.addSnakeToMap(snakes[i]);
                        myMap.addBufferZone(snakes[i]);
                    } else {
                        snakes[i-1] = new Snake(br.readLine().split(" "));
                        myMap.addSnakeToMap(snakes[i-1]);
                        myMap.addBufferZone(snakes[i-1]);
                    }
                }
                //*1 This is here because java sucks
                mySnake = new Snake(mySnakeLine.split(" "));
                myMap.addSnakeToMap(mySnake);
                
                /*
                System.err.println("_________________________________________________");
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        System.err.print(myMap.gameMap[i][j]);
                    }
                    System.err.print("|\n");
                }
                System.err.println("________________________________________________");
                */
                //finished reading, calculate move:
                //System.out.println("log calculating...");
                int move = mySnake.snakeMove(myMap, napple);
                System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}