package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

    private static final int BLANK=0;
    private int N;
    private int hamming;
    private int manhattan;
    private int[][] board;
    private int[][] goal;


    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     * @param tiles
     */
    public Board(int[][] tiles){
        N = tiles.length;
        board = new int[N][N];
        goal = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
                goal[i][j] = i * N + j + 1;
            }
        goal[N - 1][N - 1] = BLANK;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != goal[i][j] && board[i][j] != BLANK) hamming++;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int num = board[i][j];
                if (num != BLANK) {
                    int trueI = (num - 1) / N;
                    int trueJ = (num - 1) % N;
                    manhattan += Math.abs(i - trueI) + Math.abs(j - trueJ);
                }
            }
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j){
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1)
            throw new IndexOutOfBoundsException();
        return board[i][j];
    }

    /**
     * Returns the board size N
     * @return
     */
    public int size(){
        return N;
    }

    /**
     * Returns the neighbors of the current board
     * @return
     */
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * Hamming estimate described below
     * @return
     */
    public int hamming(){
        return hamming;
    }

    /**
     * Manhattan estimate described below
     * @return
     */
    public int manhattan(){
        return manhattan;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to Gradescope.
     * @return
     */
    public int estimatedDistanceToGoal(){
        return manhattan;
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     * @param y
     * @return
     */
    public boolean equals(Object y){
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board that = (Board) y;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != that.board[i][j]) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
    /**
     * Returns the string representation of the board.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
