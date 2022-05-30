package sudokuTest;

import org.junit.jupiter.api.Test;
import sudoku.Solver;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    Solver s = new Solver();
    int[][] field = new int[][] {
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {2,3,4,5,6,7,8,9,1},
            {5,6,7,8,9,1,2,3,4},
            {8,9,1,2,3,4,5,6,7},
            {3,4,5,6,7,8,9,0,2},
            {6,7,8,9,1,2,3,4,5},
            {9,1,2,3,4,5,6,7,8}
    };

    @Test
    void isValueInRowTest() {
        assertTrue(s.isValueInRow(field, 0, 5));
    }

    @Test
    void isValueInColumnTest() {
        assertTrue(s.isValueInColumn(field, 0, 5));
    }

    @Test
    void isValueInBlockTest() {
        assertFalse(s.isValueInBlock(field, 6, 7, 1));
    }

    @Test
    void isValueValidTest() {
        assertTrue(s.isValueValid(field, 6, 7, 1));
    }

    @Test
    void isOneSolutionTest() {
        assertTrue(s.isOneSolution(field));
    }

}