/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Stack;

/**
 *
 * @author tadamski
 */
public class Node {
    
    private Board board;
    private Node previous;
    private int g;
    private int h;
    
    
    public Node(Node n){
        this.board = n.getBoard();
        this.previous = n.getPrevious();
        this.g = n.g();
        this.h = n.h();
    }
    
    public Node(Board b, Node prev, int m){
        this.board = b;
        this.previous = prev;
        this.g = m;
    }

    public Board getBoard() {
        return board;
    }

    public Node getPrevious() {
        return previous;
    }

    public int f() {
        return g+h;
    }
    
    public int g() {
        return g;
    }

    public int h() {
        return h1();
    }
    
    private int h1(){
       int h = 0;
       int[][] block = this.board.getBlock();
       int size = block.length;
       
       for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if ((block[i][j] != 0) && (block[i][j] != ((i*size)+j+1))){
                    h++;
                }
            }
        }
       
       /*
       À la fin quand deux cases sont mal placées il n'y a qu'un mouvement à faire.
       Pour ne pas surestimer h, on retranche 1
       */
       /*
       if(h>0)
           h--;
       */
       return h;
    }
    
    private int h2(){
        //TODO
        return 0;
    }
    
    public boolean isGoal(){
        if(h1() == 0)
            return true;
        else
            return false;
    }
    
    public boolean equals(Node n){
        if(this.board.equals(n.board))
            return true;
        else
            return false;
    }
    
    
    public Stack<Board> neighbors(){
        
        Stack<Board> neighbors = new Stack<Board>();
        
        int size = board.getSize();
        int zeroCol = board.getZeroCol();
        int zeroRow = board.getZeroRow();
        
        //Déplacement vers la gauche
        if(zeroCol != (size-1)){
            int[][] swapGauche = board.copy();
            swapGauche[zeroRow][zeroCol] = swapGauche[zeroRow][zeroCol+1];
            swapGauche[zeroRow][zeroCol+1] = 0;
            neighbors.add(new Board(swapGauche));
        }
        
        //Déplacement vers la droite
        if(zeroCol != 0){
            int[][] swapDroite = board.copy();
            swapDroite[zeroRow][zeroCol] = swapDroite[zeroRow][zeroCol-1];
            swapDroite[zeroRow][zeroCol-1] = 0;
            neighbors.add(new Board(swapDroite));
        }
        
        //Déplacement vers le haut
        if(zeroRow != 0){
            int[][] swapHaut = board.copy();
            swapHaut[zeroRow][zeroCol] = swapHaut[zeroRow-1][zeroCol];
            swapHaut[zeroRow-1][zeroCol] = 0;
            neighbors.add(new Board(swapHaut));
        }
        
        //Déplacement vers le bas
        if(zeroRow != (size-1)){
            int[][] swapHaut = board.copy();
            swapHaut[zeroRow][zeroCol] = swapHaut[zeroRow+1][zeroCol];
            swapHaut[zeroRow+1][zeroCol] = 0;
            neighbors.add(new Board(swapHaut));
        }
        
        
        return neighbors;
    }
    
    
}
