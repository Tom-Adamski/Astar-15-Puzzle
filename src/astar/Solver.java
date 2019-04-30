/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author tadamski
 */
public class Solver {

    private Node goalNode;  // noeud but 
    private static List<Node> openList; // liste des noeuds ouverts (à explorer)
    private static List<Node> closedList; // liste des noeuds fermés (explorés)

    /* constructeur implémente l'algorithme A*/
    public Solver(Board initial) {

    }

    /* TODO
    
    // recontruit le chemin de la configuration initiale à la configuration but
    public List<Board> getPathSolution(){
    
    }
    
     */
 /* recharge un fichier puzzle*/
    public static int[][] loadFile(String fileName) {

        int[][] block = null;
        try {
            File f = new File(fileName);
            Scanner scanner = new Scanner(f);

            // la première ligne : taille du puzzle
            Scanner sc = new Scanner(scanner.nextLine());
            int N = sc.nextInt();

            block = new int[N][N];

            int i = 0;

            while (scanner.hasNext()) {
                sc = new Scanner(scanner.nextLine());
                int j = 0;

                while (sc.hasNext()) {

                    block[i][j] = sc.nextInt();

                    j++;

                }
                i++;
            }

            // les autres lignes la grille du puzzle
            sc.close();

        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }

        return block;

    }

    public static void main(String[] args) {

        // créer un nouveau board à partir d'un fichier
        //int [][] block = loadFile("./puzzles/puzzle04.txt");
        
        //Résoluble en 3 mouvements
        //int[][] block = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}};
        int[][] block = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};

        //3 mvmts
        //int[][] block = { {2,0},{1,3} };
        Board initial = new Board(block);

        openList = new ArrayList<Node>();
        closedList = new ArrayList<Node>();

        openList.add(new Node(initial, null, 0));

        // résoudre le puzzle
        while (!openList.isEmpty()) {
            
            //chercher le meilleur noeud de la liste ouverte
            Node bestNode = new Node(openList.get(0));
            for (Node n : openList) {
                if (n.f() < bestNode.f()) {
                    bestNode = new Node(n);
                }
            }

            if (bestNode.isGoal()) {
                //Sortir de la boucle
                break;
            } 
            else {
                
                System.out.println("Best node is "+bestNode.f()+" "+bestNode.g());
                System.out.println(bestNode.getBoard());
                
                //le mettre dans la liste fermée et le retirer de la liste ouverte
                closedList.add(bestNode);
                
                for (int i = 0; i < openList.size(); i++) {
                    if (openList.get(i).equals(bestNode)) {
                        openList.remove(i);
                    }
                }
                
                //générer les voisins
                Stack<Board> neighbors = bestNode.neighbors();

                //pour chaque voisin
                for (int i=0; i<neighbors.size(); i++) {
                
                    Board neighbor = neighbors.get(i);
                    
                    //vérifier s'il n'est pas dans la liste fermée
                    boolean inClosedList = false;
                    for (Node closed : closedList) {
                        if (closed.getBoard().equals(neighbor)) {
                            inClosedList = true;
                        }
                    }

                    if (!inClosedList) {
                        // s'il est dans la liste ouverte on le met à jour
                        boolean inOpenList = false;
                        for (int j=0; j<openList.size(); j++) {

                            Node open = openList.get(j);
                            
                            if (open.getBoard().equals(neighbor)) {
                                inOpenList = true;
                                Node temp = new Node(neighbor,bestNode,bestNode.g()+1);
                                if (temp.f() > (open.f())) {
                                    openList.remove(j);
                                    openList.add(new Node(neighbor, bestNode, bestNode.g() + 1));
                
                                }
                            }
                        }
                        // sinon on le met dans la liste ouverte 
                        if (!inOpenList) {
                            openList.add(new Node(neighbor, bestNode, bestNode.g() + 1));
                        }
                    }
                }
            }
        }
        // imprimer la solution si elle existe

        //chercher le meilleur noeud de la liste ouverte
        Node bestNode = new Node(openList.get(0));
        for (Node n : openList) {
            if (n.f() < bestNode.f()) {
                bestNode = new Node(n);
            }
        }

        if (bestNode.isGoal()) {
            System.out.println("Success");
            System.out.println("--------------");
            
            ArrayList<Node> successChain = new ArrayList<>();
            successChain.add(bestNode);
            Node n = bestNode;
            while(n.getPrevious() != null)
            {
                successChain.add(n.getPrevious());
                n = n.getPrevious();
            }
            
            for(int i=successChain.size()-1; i>=0; i--)
            {
                System.out.println(successChain.get(i).getBoard());
            }
            
        } 
        else {
            System.out.println("Impossible");
        }
        
        
    }
}
