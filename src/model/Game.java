/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;

/**
 *
 * @author Bassem
 */
public class Game {

    private String gameName;
    private String playerName1;
    private String playerName2;
    private String winner;
    Vector<String> moves;

    public Vector<String> getMoves() {
        return moves;
    }
   

    public Game(String gameName, String playerName1, String playerName2, String winner,Vector<String> moves) {
        this.gameName = gameName;
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.winner = winner;
        this.moves=moves;
        
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPlayerName1() {
        return playerName1;
    }

    public void setPlayerName1(String playerName1) {
        this.playerName1 = playerName1;
    }

    public String getPlayerName2() {
        return playerName2;
    }

    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }


}
