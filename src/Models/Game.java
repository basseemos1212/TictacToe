/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Bassem
 */
public class Game {

    private Integer gameID;
    private String player1Img="/assets/avatar.png";
    private String player2Img="/assets/avatar.png";
    private String p1UserName;
    private String p2UserName;

    public String getP1UserName() {
        return p1UserName;
    }

    public void setP1UserName(String p1UserName) {
        this.p1UserName = p1UserName;
    }

    public String getP2UserName() {
        return p2UserName;
    }

    public void setP2UserName(String p2UserName) {
        this.p2UserName = p2UserName;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getPlayer1Img() {
        return player1Img;
    }

    public void setPlayer1Img(String player1Img) {
        this.player1Img = player1Img;
    }

    public String getPlayer2Img() {
        return player2Img;
    }

    public void setPlayer2Img(String player2Img) {
        this.player2Img = player2Img;
    }

}
