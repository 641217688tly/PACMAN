package Model.Ghosts.BFS;

public class Node {
    public int x; //x of nodes in nodeMap, that is, from 0 to 15
    public int y; //y of nodes in nodeMap, that is, the value ranges from 0 to 23
    public Node previousNode; //Record the previous Node in your path
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, Node previousNode) {
        this.x = x;
        this.y = y;
        this.previousNode = previousNode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}