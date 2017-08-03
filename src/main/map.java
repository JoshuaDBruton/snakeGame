/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author joshua
 */
public class map {
    
   char [][] gameMap;

    public map() {
        this.gameMap = new char [50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j< 50; j++) {
                this.gameMap[i][j] = ' ';
            }
        }
    }
    
    public void addSnakeToMap (Snake snake) {
        if (!snake.state.equals("dead")) {
            for (int i = 1; i < snake.body.length; i++) {
                if (snake.body[i-1].x == snake.body[i].x) {
                    if (snake.body[i-1].y < snake.body[i].y) {
                        for (int k = snake.body[i-1].y; k < snake.body[i].y; k++) {
                            gameMap[snake.body[i].x][k] = '0'; 
                        }
                    } else {
                        for (int k = snake.body[i-1].y; k > snake.body[i].y; k--) {
                            gameMap[snake.body[i].x][k] = '0'; 
                        }
                    }
                } else {
                    if (snake.body[i-1].x < snake.body[i].x) {
                        for (int k = snake.body[i-1].x; k < snake.body[i].x; k++) {
                            gameMap[k][snake.body[i].y] = '0';
                        }
                    } else {
                        for (int k = snake.body[i-1].x; k > snake.body[i].x; k--) {
                            gameMap[k][snake.body[i].y] = '0';
                        }
                    }
                }
            }
        }    
    }
    
    public void addBufferZone (Snake snake) {
        if (!(snake.state.equals("dead"))) {    
            if (snake.body[0].x+1 < 50) {
                gameMap[snake.body[0].x+1][snake.body[0].y] = '0';
            }
            if (snake.body[0].x-1 > -1) {
                gameMap[snake.body[0].x-1][snake.body[0].y] = '0';
            }
            if (snake.body[0].y+1 < 50) {
                gameMap[snake.body[0].x][snake.body[0].y+1] = '0';
            }
            if (snake.body[0].y-1 > -1) {
                gameMap[snake.body[0].x][snake.body[0].y-1] = '0';
            }
        }
    }
}
