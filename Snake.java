/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Point;
import java.util.Arrays;

/**
 *
 * @author joshua
 */
public class Snake {

    String state;
    int length;
    int kills;
    int invisT;
    Point [] body;
    
    public Snake(String [] snakeData) {
        if (snakeData[0].equals("alive") || snakeData[0].equals("dead")) {
            this.state = snakeData [0];
            this.length = Integer.parseInt(snakeData[1]);
            this.kills = Integer.parseInt(snakeData[2]);
            this.invisT = 0;
            String [] strBody = Arrays.copyOfRange(snakeData, 3, snakeData.length);
            this.body = new Point[strBody.length];
            for (int i = 0; i < strBody.length; i++) {
                String [] strPoint = strBody[i].split(",");
                this.body[i] = new Point();
                this.body[i].x = Integer.parseInt(strPoint[1]);
                this.body[i].y = Integer.parseInt(strPoint[0]);
            }
        } else if (snakeData[0].equals("invisible")) {
            this.state = snakeData[0];
            this.length = Integer.parseInt(snakeData[1]);
            this.kills = Integer.parseInt(snakeData[2]);
            this.invisT = Integer.parseInt(snakeData[3]);
            String [] strBody = Arrays.copyOfRange(snakeData, 4, snakeData.length);
            this.body = new Point[strBody.length];
            for (int i = 0; i < strBody.length; i++) {
                String [] strPoint = strBody[i].split(",");
                this.body[i] = new Point();
                this.body[i].x = Integer.parseInt(strPoint[1]);
                this.body[i].y = Integer.parseInt(strPoint[0]);
            }
        }
    }
    
    public int snakeMove(map thisMap, Point apple) {
        
        Point head = this.body[0];
        
        if (head.x > apple.x && (thisMap.gameMap[head.x-1][head.y]==' '||thisMap.gameMap[head.x-1][head.y]=='*')) {
            return 0;
        }
        if (head.x < apple.x && (thisMap.gameMap[head.x+1][head.y]==' '||thisMap.gameMap[head.x+1][head.y]=='*')) {
            return 1;
        }
        if (head.y > apple.y && (thisMap.gameMap[head.x][head.y+1]==' '||thisMap.gameMap[head.x][head.y+1]=='*')) {
            return 2;
        }
        if (head.y < apple.y && (thisMap.gameMap[head.x][head.y-1]==' '||thisMap.gameMap[head.x][head.y-1]=='*')) {
            return 3;
        }
        return 2;
    }
    
}