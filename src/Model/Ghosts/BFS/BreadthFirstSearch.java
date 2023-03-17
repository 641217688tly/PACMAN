package Model.Ghosts.BFS;

import Model.PACMAN_Game;
import java.util.ArrayList;

public class BreadthFirstSearch {
    public static final int MAPLENGTH = PACMAN_Game.getStringMap().size();
    public static final int MAPWIDTH = PACMAN_Game.getStringMap().get(0).length();
    private int[][] availableWay = new int[MAPLENGTH][MAPWIDTH]; //Used to record whether each node is a wall
    private boolean[][] visited = new boolean[MAPLENGTH][MAPWIDTH]; //Records whether each node has been accessed
    private boolean[][] visitedCopy = new boolean[MAPLENGTH][MAPWIDTH]; //This backup is used to record whether each node has been visited, and is not changed for refreshing visited
    private ArrayList<Node> queue = new ArrayList<Node>();//Simulate a queue with a dynamic array
    private int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; //Indicates left, right, down, and up

    public BreadthFirstSearch() {
        initNodeMap();
    }

    public void initNodeMap() {
        for (int y = 0; y < 24; y++) { //y<24
            for (int x = 0; x < 16; x++) { //x<16
                if (PACMAN_Game.getStringMap().get(y).charAt(x) == 'W') {
                    availableWay[y][x] = 1;
                    visited[y][x] = false;
                    visitedCopy[y][x] = false;
                } else {
                    availableWay[y][x] = 0;
                    visited[y][x] = false;
                    visitedCopy[y][x] = false;
                }
            }
        }
    }

    public ArrayList<Node> bfsFindWay(Node startNode, Node endNode) {
        visited = visitedCopy;
        this.queue.clear();

        if (startNode.x == endNode.x && startNode.y == endNode.y) { //The end coincides with the beginning
            return null;
        }
        visited[startNode.y][startNode.x] = true;
        queue.add(startNode); //Entry to team
        while (!queue.isEmpty()) {
            Node firstNode = queue.get(0); //Get the team leader element
            for (int i = 0; i < direction.length; i++) { //Start searching in four directions
                Node tempNode = new Node(firstNode.x + direction[i][0], firstNode.y + direction[i][1], firstNode);
                if (visited[tempNode.y][tempNode.x] == false && availableWay[tempNode.y][tempNode.x] == 0) { //Judgment goes well and doesn't cross the line
                    if (tempNode.x == endNode.x && tempNode.y == endNode.y) { //Determine whether it is an exit
                        ArrayList<Node> nodePath = new ArrayList<>();
                        while (!(tempNode == null)) {
                            nodePath.add(0, tempNode);
                            tempNode = tempNode.previousNode;
                        }
                        return nodePath;
                    }
                    visited[tempNode.y][tempNode.x] = true; //It's passable, but it's not an exit. Records have been accessed
                    queue.add(tempNode); //All right, but not the exit. Get in line
                }
            }
            queue.remove(0); //The first element of the team is out
        }
        return null;
    }

}


