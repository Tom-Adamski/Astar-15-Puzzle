/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Arrays;

/**
 *
 * @author tadamski
 */
public class Board {
    
    private int[][] block;
    private int size;
    private int zeroCol;
    private int zeroRow;
    

    public Board(int[][] block){
        this.size = block.length;
        this.block = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if (block[i][j] == 0){
                    this.zeroRow = i;
                    this.zeroCol = j;
                }
                this.block[i][j] = block[i][j];
            }
        }
    }

    
    public int[][] getBlock() {
        return block;
    }
    
    public int getZeroCol() {
        return zeroCol;
    }

    public int getZeroRow() {
        return zeroRow;
    }
    
    public int getSize() {
        return size;
    }
    
    
    public int[][] copy(){
        int copy[][] = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                copy[i][j] = this.block[i][j];
            }
        }
        return copy;
    }
    
    public boolean equals(Object y){
        if(y instanceof Board){
            if(y == null)
                return false;
            else if(this == y)
                return true;
            else if(((Board)y).getSize() == this.getSize())
                return Arrays.deepEquals(this.block, ((Board) y).getBlock());
            else
                return false;
        }
        else{
            return false;
        }
    }
    
    public String toString(){
        String s = "";
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                s += this.block[i][j] + "\t";
            }
            s += "\n";
        }
        return s;
    }
    
}
