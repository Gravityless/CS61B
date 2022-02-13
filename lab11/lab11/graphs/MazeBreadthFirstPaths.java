package lab11.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int src;
    private int dst;
    private boolean find;
    private Queue<Integer> nodes;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        src = maze.xyTo1D(sourceX, sourceY);
        dst = maze.xyTo1D(targetX, targetY);
        distTo[src] = 0;
        edgeTo[src] = src;
        nodes = new Queue<>();
        nodes.enqueue(src);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        announce();

        if (v == dst) {
            find = true;
            return;
        }

        while(!nodes.isEmpty()){
            v = nodes.dequeue();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    nodes.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                }
                if (w == dst) {
                    find = true;
                    return;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(src);
    }
}

