package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.LinkedList;
import java.util.Deque;

public class Solver {
    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * @param initial
     */
    public Solver(WorldState initial){
        SearchNode currentNode = new SearchNode(initial);
        searchNodePQ = new MinPQ<SearchNode>();
        searchNodePQ.insert(currentNode);
        while (!currentNode.ws.isGoal()) {
            currentNode = searchNodePQ.delMin();
            for (WorldState ws : currentNode.ws.neighbors()) {
                if (!ws.equals(currentNode))
                    searchNodePQ.insert(new SearchNode(ws, currentNode));
            }
        }
        moves = currentNode.moves;
        solution = new LinkedList<>();
        for (int i = moves; i >= 0; i--){
            solution.addFirst(currentNode.ws);
            currentNode = currentNode.prev;
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     * @return
     */
    public int moves(){
        return moves;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     * @return
     */
    public Iterable<WorldState> solution(){
        return solution;
    }

    private MinPQ<SearchNode> searchNodePQ;
    private int moves;
    private Deque<WorldState> solution;

    private class SearchNode implements Comparable<SearchNode>{
        private int moves;
        private int priority;
        private WorldState ws;
        private SearchNode prev;

        public SearchNode(WorldState initial){
            moves = 0;
            priority = 0;
            ws = initial;
            prev = null;
        }

        public SearchNode(WorldState worldState, SearchNode previous){
            ws = worldState;
            moves = previous.moves + 1;
            prev = previous;
            priority = moves + ws.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode s){
            return this.priority - s.priority;
        }
    }
}
