package sudoku;

public class MainSudoku {

    static int[][] field = {
            {1,7,4,2,8,5,3,9,6},
            {2,8,5,3,9,6,4,1,7},
            {9,6,3,1,7,4,2,8,5},
            {4,1,7,5,2,9,6,3,8},
            {5,2,8,6,3,0,7,4,1},
            {0,0,6,4,1,7,5,0,0},
            {8,5,2,9,0,3,1,7,4},
            {7,4,1,8,5,0,9,6,3},
            {0,3,9,7,4,1,8,5,2}
    };

    public static void main(String[] args) {

        /*Generator generator = new Generator(DifficultyLevels.Levels.HARD);
        generator.generateSudoku();*/

        /*Solver solver = new Solver();
        System.out.println(solver.isOneSolution(field));
        System.out.println(solver.solveSudoku(field));
        System.out.println(Arrays.deepToString(solver.getSolution()));*/

        /*DifficultyLevels l = new DifficultyLevels();
        DifficultyLevels.chooseLevel(DifficultyLevels.Levels.EASY);
        System.out.println(l.getFreeCells());*/

        App app = new App();
        app.setText();
        app.setStartScreen();
        app.setLevels();
        app.setGame();

    }
}
