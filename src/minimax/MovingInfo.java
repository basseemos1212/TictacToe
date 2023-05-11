/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

/**
 *
 * @author moham
 */
public class MovingInfo {

    public int locX;
    public int locY;
    public int prob;
    public int totalProb;
    public int bestDepth;

    public MovingInfo(int x, int y, int prob) {
        this.locX = x;
        this.locY = y;
        this.prob = prob;
    }
}
