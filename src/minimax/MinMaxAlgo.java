/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author moham
 */

public class MinMaxAlgo {

    private Move board[][];
    private MinMaxAlgo parent;
    private ArrayList<MinMaxAlgo> childrens;
    private Difficulty difficulty;
    private boolean isMax;
    public MovingInfo moveInfo;
    private Move player;
    private int depth = 0;

    public MinMaxAlgo(Move board[][], Move player, Difficulty difficulty, boolean isMax, MinMaxAlgo parent, MovingInfo currentMoveInfo) {
        this.board = board;
        this.player = (player == Move.X ? Move.O : Move.X);
        this.difficulty = difficulty;
        this.isMax = !isMax;
        this.parent = parent;
        this.moveInfo = currentMoveInfo;
        this.childrens = new ArrayList<>();

        this.moveInfo.totalProb = currentMoveInfo.prob;

        if (parent == null) {
            this.depth = 0;
        } else {
            this.depth = parent.depth + 1;
        }

        if (currentMoveInfo.prob == 0) {
            expandAll(board, (player == Move.X ? Move.O : Move.X));
            buildProbs(this);
        }
    }

    public static int evaluate(Move board[][]) {
        int win = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 1; col < 3; col++) {
                if (board[row][col] != Move.NONE && board[row][col] == board[row][col - 1]) {
                    win = 1;
                } else {
                    win = 0;
                    break;
                }
            }
            if (win != 0) {
                return win;
            }
        }

        for (int col = 0; col < 3; col++) {
            for (int row = 1; row < 3; row++) {
                if (board[row][col] != Move.NONE && board[row][col] == board[row - 1][col]) {
                    win = 1;
                } else {
                    win = 0;
                    break;
                }
            }
            if (win != 0) {
                return win;
            }
        }

        if ((board[1][1] != Move.NONE) && (board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
            return 1;
        } else if ((board[1][1] != Move.NONE) && (board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
            return 1;
        }

        return win;
    }

    private void expandAll(Move board[][], Move player) {
        // Building the tree while the probability of this node != 0 (no win or lose!)
        Move childBoard[][];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Move.NONE) {
                    childBoard = new Move[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            childBoard[i][j] = board[i][j];
                        }
                    }

                    childBoard[row][col] = (player == Move.X ? Move.O : Move.X);

                    MovingInfo nextMoveInfo = new MovingInfo(row, col, evaluate(childBoard));

                    if (nextMoveInfo.prob == 1) {
                        nextMoveInfo.bestDepth = depth + 1;
                    }

                    if (isMax) {
                        nextMoveInfo.prob = -nextMoveInfo.prob;
                    }

                    MinMaxAlgo child = new MinMaxAlgo(childBoard, player, difficulty, isMax, this, nextMoveInfo);

                    childrens.add(child);
                }
            }
        }
    }

    public void buildProbs(MinMaxAlgo child) {
        Comparator<MinMaxAlgo> comparator = Comparator.comparing(MinMaxAlgo::getProb);
        if (child.isMax && child.childrens.size() > 0) {
            MinMaxAlgo maxChildProb = Collections.max(child.childrens, Comparator.comparing(s -> s.moveInfo.prob));
            child.moveInfo.prob = maxChildProb.moveInfo.prob;
        } else if (child.childrens.size() > 0) {
            MinMaxAlgo node = Collections.min(child.childrens, Comparator.comparing(s -> s.moveInfo.prob));
            child.moveInfo.prob = node.moveInfo.prob;
        }

        if (child.childrens.size() > 0) {
            child.moveInfo.totalProb = child.childrens.stream().map(p -> p.moveInfo.totalProb).reduce(0, Integer::sum);
            List<MinMaxAlgo> bestDepthNodes = child.childrens.stream().filter(p -> p.moveInfo.bestDepth > 0).collect(toList());

            if (bestDepthNodes.size() > 0) {
                child.moveInfo.bestDepth = bestDepthNodes.stream().min(Comparator.comparing(e -> e.moveInfo.bestDepth)).get().moveInfo.bestDepth;
            }
        }
    }

    public MinMaxAlgo findNextMove(MinMaxAlgo root, Move board[][]) {
        Comparator<MinMaxAlgo> getByProb = Comparator.comparing(MinMaxAlgo::getProb);
        Comparator<MinMaxAlgo> getByTotalProb = Comparator.comparing(MinMaxAlgo::getTotalProb);
        Comparator<MinMaxAlgo> getByDepth = Comparator.comparing(MinMaxAlgo::getDepth);

        MinMaxAlgo bestNode = root;
        if (bestNode.childrens.size() > 0) {
            for (MinMaxAlgo child : bestNode.childrens) {
                boolean isSameBoard = true;
                for (int row = 0; (row < 3 && isSameBoard); row++) {
                    for (int col = 0; col < 3; col++) {
                        if (child.board[row][col] != board[row][col]) {
                            isSameBoard = false;
                            break;
                        }
                    }
                }
                if (isSameBoard) {
                    bestNode = child;
                    break;
                }
            }
        }

        if (bestNode.childrens.size() > 0) {
            if (difficulty == Difficulty.Hard) {

                List<MinMaxAlgo> bestNodes = bestNode.childrens.stream().filter(p -> p.moveInfo.prob != -1).collect(toList());
                bestNode = bestNodes.stream().max(getByTotalProb).get();
                List<MinMaxAlgo> bestDepthNodes = bestNodes.stream().filter(p -> p.moveInfo.bestDepth > 0).collect(toList());
                if (bestDepthNodes.size() > 0) {
                    MinMaxAlgo bestDepthNode = bestDepthNodes.stream().min(getByDepth).get();
                    if (bestNode.depth == bestDepthNode.moveInfo.bestDepth) {
                        bestNode = bestDepthNode;
                    }
                }

            } else if (difficulty == difficulty.Normal) {
                List<MinMaxAlgo> bestNodes = bestNode.childrens.stream().filter(p -> p.moveInfo.prob != -1).collect(toList());
                int randomNode = new Random().nextInt(bestNodes.size());
                bestNode = bestNodes.get(randomNode);
            } else if (difficulty == Difficulty.Easy) {
                int randomNode = new Random().nextInt(bestNode.childrens.size());
                bestNode = bestNode.childrens.get(randomNode);
                /*MinMaxAlgo minDepthNode = bestNode.childrens.stream().min(getByDepth).get();
                MinMaxAlgo maxDepthNode = bestNode.childrens.stream().max(getByDepth).get();
                if (minDepthNode.moveProb.bestDepth != maxDepthNode.moveProb.bestDepth) {
                    bestNode = bestNode.childrens.stream().max(getByDepth).get();
                } else {
                    int randomNode = new Random().nextInt(bestNode.childrens.size());
                    bestNode = bestNode.childrens.get(randomNode);
                }*/
            }

            bestNode.parent = null;
        } else {
            bestNode = null;
        }
        return bestNode;
    }

    public int getProb() {
        return moveInfo.prob;
    }

    public int getDepth() {
        return moveInfo.bestDepth;
    }

    public int getTotalProb() {
        return moveInfo.totalProb;
    }

}
