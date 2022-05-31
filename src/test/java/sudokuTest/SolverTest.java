package sudokuTest;

import org.junit.jupiter.api.Test;
import sudoku.Solver;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    Solver s = new Solver();
    int[][] field = new int[][] {
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
        assertFalse(s.isValueInBlock(field, 0, 0, 9));
    }

    @Test
    void isValueValidTest() {
        assertTrue(s.isValueValid(field, 1, 0, 9));
    }

    @Test
    void isFieldValidTest() {
        int[][] arr = new int[][] {
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
        assertFalse(s.isFieldValid(arr));
    }

    @Test
    void isOneSolutionTest() {
        assertTrue(s.isOneSolution(field));
    }

    @Test
    void isSolvedTest() {
        assertTrue(s.solveSudoku(field));
    }

    @Test
    void isSolvedReversedTest() {
        assertTrue(s.solveSudokuReverse(field));
    }

    @Test
    void solutionTest() {
        s.isOneSolution(field);
        int[][] answer = new int[][] {
                {6,5,4,1,2,3,7,8,9},
                {9,8,7,4,5,6,1,2,3},
                {3,2,1,7,8,9,4,5,6},
                {1,9,8,5,6,7,2,3,4},
                {7,6,5,2,3,4,8,9,1},
                {4,3,2,8,9,1,5,6,7},
                {2,1,9,6,7,8,3,4,5},
                {8,7,6,3,4,5,9,1,2},
                {5,4,3,9,1,2,6,7,8}
        };
        assertArrayEquals(answer, s.getSolution());
    }
}