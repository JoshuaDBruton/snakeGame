/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author User
 */
public class GameMap {
    final int width = 50;
    final int height = 50;
    final int nSnakes = 4;
    Point apple1 = new Point();
    Point apple2 = new Point();
    int mySnakeNumber = 0;
    Point myHead = new Point();
    String [] snakes;
    String [][] map;
    Point [] heads = new Point [3];
    int count = 0;
    
    public GameMap(BufferedReader br, String line) throws IOException {
        String[] apple1 = line.split(" ");
        String[] apple2 = br.readLine().split(" ");
        int mySnakeNum = Integer.parseInt(br.readLine());
        String[] snakes = new String[4];
        for (int i = 0; i < 4; i++) {
            snakes[i] = br.readLine();
        }
        this.apple1.x = Integer.parseInt(apple1[0]);
        this.apple1.y = Integer.parseInt(apple1[1]);
        this.apple2.x = Integer.parseInt(apple2[0]);
        this.apple2.y = Integer.parseInt(apple2[1]);
        this.mySnakeNumber = mySnakeNum;
        
        List mySnake = new LinkedList<String>(Arrays.asList(snakes[this.mySnakeNumber].split(" ")));
        if (mySnake.get(0).toString().equals("alive")) {
            mySnake.remove(0);
            mySnake.remove(0);
            mySnake.remove(0);
        } else if (mySnake.get(0).toString().equals("invisible")) {
            mySnake.remove(0);
            mySnake.remove(0);
            mySnake.remove(0);
            mySnake.remove(0);
        }
        String shead [] = new String [2];
        shead = mySnake.get(0).toString().split(",");
        this.myHead.x = Integer.parseInt(shead[0]);
        this.myHead.y = Integer.parseInt(shead[1]);
        this.snakes = new String [4];
        this.snakes = snakes;
        this.map = createMap();
        this.count = 0;
    }
    
    public String[][] createMap() {
        String [][] map = new String[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[j][i] = " ";
            }
        }
        if (apple1.x != -1) {
            map[apple1.y][apple1.x] = "*";
        }
        if (apple2.x != -1) {
            map[apple2.y][apple2.x] = "*";
        }
        for (int i = 0; i<4; i++) {
            List mySnake = new LinkedList<String>(Arrays.asList(snakes[i].split(" ")));
            if (mySnake.get(0).toString().equals("alive")) {
                mySnake.remove(0);
                mySnake.remove(0);
                mySnake.remove(0);
            } else if (mySnake.get(0).toString().equals("invisible")) {
                mySnake.remove(0);
                mySnake.remove(0);
                mySnake.remove(0);
                mySnake.remove(0);
            } else if (mySnake.get(0).toString().equals("dead")) {
                continue;
            }
            Point pointA = new Point();
            Point pointB = new Point();
            for (int j = 1; j < mySnake.size(); j++) {
                String [] spointA = mySnake.get(j-1).toString().split(",");
                String [] spointB = mySnake.get(j).toString().split(",");
                pointA.x = Integer.parseInt(spointA[0]);
                pointA.y = Integer.parseInt(spointA[1]);
                pointB.x = Integer.parseInt(spointB[0]);
                pointB.y = Integer.parseInt(spointB[1]);
                map[pointA.y][pointA.x] = "0";
                map[pointB.y][pointB.x] = "0";
                
                if (pointA.x == pointB.x) {
                    if (pointA.y > pointB.y) {
                        for (int k = pointA.y; k > pointB.y; k--) {
                            map[k][pointA.x] = "0";
                        }
                    } else if (pointA.y < pointB.y) {
                        for (int k = pointA.y; k < pointB.y; k++) {
                            map[k][pointA.x] = "0";
                        }
                    }
                } else if (pointA.y == pointB.y) {
                    if (pointA.x > pointB.x) {
                        for (int k = pointA.x; k > pointB.x; k--) {
                            map[pointA.y][k] = "0";
                        }
                    } else if (pointA.x < pointB.x) {
                        for (int k = pointA.x; k < pointB.y; k++) {
                            map[pointA.y][k] = "0";
                        }
                    }
                }
                
                if (j==1&&i!=this.mySnakeNumber) {
                    heads[count] = pointA;
                    count++;
                    if (pointA.x+1<50) {
                        map[pointA.y][pointA.x+1]="0";
                    }    
                    if (pointA.x-1 > -1) {
                        map[pointA.y][pointA.x-1]="0";
                    }    
                    if (pointA.y+1 < 50) {
                        map[pointA.y+1][pointA.x]="0";
                    }    
                    if (pointA.y-1 > -1) {
                        map[pointA.y-1][pointA.x]="0";
                    }    
                }
            }
        }
        return map;
    }

    public Point getApple1() {
        return apple1;
    }

    public Point getApple2() {
        return apple2;
    }

    public int getMySnakeNumber() {
        return mySnakeNumber;
    }

    public String[][] getMap() {
        return map;
    }

    public Point getMyHead() {
        return myHead;
    }

    public String[] getSnakes() {
        return snakes;
    }
    
}

