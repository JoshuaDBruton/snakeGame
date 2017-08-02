/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Point;
import static java.lang.Math.*;
import java.util.Random;

/**
 *
 * @author User
 */
public class Snake {
    public int getMove (String[][] map, Point myHead, GameMap myMap) {
        boolean safe = false;
        int move = 0;
        Point target = new Point();
        
        if (minDistance(myHead, myMap)) {
            if (myMap.apple1.x!=-1) {
                target.x = myMap.apple1.x;
                target.y = myMap.apple1.y;
            } else {
                target.x = myMap.apple2.x;
                target.y = myMap.apple2.y;
            }
            move = astar(myHead, target, map);
        } 
        
        while (safe == false) {
            switch (move) {
                case 0 :
                        System.out.println("log" + myHead.y);
                        if (myHead.y-1>-1&&(map[myHead.y-1][myHead.x].equals(" ")||map[myHead.y-1][myHead.x].equals("*"))) {
                            safe = true;
                        } else {
                            move = 1;
                        }
                    break;
                case 1 :
                        if (myHead.y+1<50&&(map[myHead.y+1][myHead.x].equals(" ")||map[myHead.y+1][myHead.x].equals("*"))) {
                            safe = true;
                        } else {
                            move = 2;
                        }
                    break;
                case 2 :
                        if (myHead.x-1>-1&&(map[myHead.y][myHead.x-1].equals(" ") || map[myHead.y][myHead.x-1].equals("*"))) {
                            safe = true;
                        } else {
                            move = 3;
                        }
                    break;
                case 3 :
                        if (myHead.x+1<50&&(map[myHead.y][myHead.x+1].equals(" ") || map[myHead.y][myHead.x+1].equals("*"))) {
                            safe = true;
                        } else {
                            move = 0;
                        }
                    break;
                default : move = 2;
                          safe = true;
                    break;
            }
        }
        return move;
    }
    public boolean minDistance (Point myHead, GameMap myMap) {
        boolean safe = false;
        Point apple = new Point();
        if (myMap.apple1.x!=-1) {
            apple.x = myMap.apple1.x;
            apple.y = myMap.apple1.y;
        } else {
            apple.x = myMap.apple2.x;
            apple.y = myMap.apple2.y;
        }
       
        double myDistance = sqrt(pow((apple.x-myHead.x),2)+pow((apple.y-myHead.y),2));
        
        Point [] heads = new Point[3];
        heads = myMap.heads;
        double minD = myDistance;
        
        for (int i = 0; i < 3; i++) {
            double currD = sqrt(pow((apple.x-heads[i].x),2)+pow((apple.y-heads[i].y),2));
            if (currD < minD) {
                minD = currD;
            } else {}
        }
        
        if (minD == myDistance) {
            safe = true;
        }
        
        return safe;
    }
    
    public int astar(Point myHead, Point target, String[][] map) {
        return 0;
    }
}