package sudoku;

public class MainSudoku {

    static int[][] field = {
            {6,5,4,1,2,0,7,8,9},
            {0,8,0,0,5,6,0,2,0},
            {0,2,1,7,8,9,4,5,6},
            {1,9,8,5,6,7,2,3,4},
            {7,6,0,2,0,4,8,9,0},
            {4,3,2,8,0,1,0,6,7},
            {0,1,9,0,7,0,3,4,5},
            {8,7,6,3,4,5,9,1,2},
            {5,4,3,9,1,2,6,0,8}
    };

    public static void main(String[] args) {
        App app = new App();
        app.startSudoku();
    }
}
