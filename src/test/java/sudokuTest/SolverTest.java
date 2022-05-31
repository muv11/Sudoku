package sudokuTest;

import org.junit.jupiter.api.Test;
import sudoku.Solver;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    Solver s = new Solver();
    int[][] fieldA = new int[][] {
            {7,1,4,8,2,5,9,3,6},
            {8,0,5,9,3,6,1,4,7},
            {9,3,6,1,4,7,2,5,8},
            {4,7,1,5,8,2,6,9,3},
            {6,9,3,7,1,0,8,2,5},
            {5,8,2,6,9,3,7,1,4},
            {3,6,9,4,7,1,5,8,2},
            {2,0,8,3,6,9,4,7,1},
            {1,4,7,2,5,8,3,6,9}
    };
    int[][] fieldB = new int[][] {
            {1,7,4,3,6,0,2,5,8},
            {3,9,6,5,8,2,4,7,1},
            {2,8,5,4,7,1,3,6,9},
            {4,1,7,6,9,3,0,8,2},
            {5,2,8,7,1,4,6,9,3},
            {6,3,9,8,2,5,7,1,4},
            {7,4,0,9,3,6,8,2,5},
            {8,5,2,1,4,7,9,3,6},
            {9,6,3,2,5,8,1,4,7}
    };
    int[][] fieldC = new int[][] {
            {7,1,4,8,2,5,9,3,6},
            {8,2,5,9,0,6,1,4,7},
            {9,3,6,1,4,7,2,5,8},
            {1,4,7,2,5,8,3,6,9},
            {2,5,8,3,6,9,4,7,1},
            {3,6,9,4,7,1,5,8,2},
            {4,7,0,5,8,2,6,9,3},
            {5,8,2,6,9,3,7,1,4},
            {6,9,3,7,1,4,8,2,0}
    };

    @Test
    void isValueInRowTest() {
        assertTrue(s.isValueInRow(fieldA, 8, 1));
    }

    @Test
    void isValueInColumnTest() {
        assertTrue(s.isValueInColumn(fieldA, 0, 1));
    }

    @Test
    void isValueInBlockTest() {
        assertTrue(s.isValueInBlock(fieldA, 0, 0, 6));
    }

    @Test
    void isValueValidTest() {
        assertFalse(s.isValueValid(fieldA, 2, 8, 8));
    }

    @Test
    void isFieldValidTestA() {
        assertTrue(s.isFieldValid(fieldA));
    }

    @Test
    void isOneSolutionTestA() {
        assertTrue(s.isOneSolution(fieldA));
    }

    @Test
    void isSolvedTestA() {
        assertTrue(s.solveSudoku(fieldA));
    }

    @Test
    void isSolvedReversedTestA() {
        assertTrue(s.solveSudokuReverse(fieldA));
    }

    @Test
    void solutionTestA() {
        s.isOneSolution(fieldA);
        int[][] answer = new int[][] {
                {7,1,4,8,2,5,9,3,6},
                {8,2,5,9,3,6,1,4,7},
                {9,3,6,1,4,7,2,5,8},
                {4,7,1,5,8,2,6,9,3},
                {6,9,3,7,1,4,8,2,5},
                {5,8,2,6,9,3,7,1,4},
                {3,6,9,4,7,1,5,8,2},
                {2,5,8,3,6,9,4,7,1},
                {1,4,7,2,5,8,3,6,9}
        };
        assertArrayEquals(answer, s.getSolution());
    }

    @Test
    void isFieldValidTestB() {
        assertTrue(s.isFieldValid(fieldB));
    }

    @Test
    void isOneSolutionTestB() {
        assertTrue(s.isOneSolution(fieldB));
    }

    @Test
    void isSolvedTestB() {
        assertTrue(s.solveSudoku(fieldB));
    }

    @Test
    void isSolvedReversedTestB() {
        assertTrue(s.solveSudokuReverse(fieldB));
    }

    @Test
    void solutionTestB() {
        s.isOneSolution(fieldB);
        int[][] answer = new int[][] {
                {1,7,4,3,6,9,2,5,8},
                {3,9,6,5,8,2,4,7,1},
                {2,8,5,4,7,1,3,6,9},
                {4,1,7,6,9,3,5,8,2},
                {5,2,8,7,1,4,6,9,3},
                {6,3,9,8,2,5,7,1,4},
                {7,4,1,9,3,6,8,2,5},
                {8,5,2,1,4,7,9,3,6},
                {9,6,3,2,5,8,1,4,7}
        };
        assertArrayEquals(answer, s.getSolution());
    }

    @Test
    void isFieldValidTestC() {
        assertTrue(s.isFieldValid(fieldC));
    }

    @Test
    void isOneSolutionTestC() {
        assertTrue(s.isOneSolution(fieldC));
    }

    @Test
    void isSolvedTestC() {
        assertTrue(s.solveSudoku(fieldC));
    }

    @Test
    void isSolvedReversedTestC() {
        assertTrue(s.solveSudokuReverse(fieldC));
    }

    @Test
    void solutionTestC() {
        s.isOneSolution(fieldC);
        int[][] answer = new int[][] {
                {7,1,4,8,2,5,9,3,6},
                {8,2,5,9,3,6,1,4,7},
                {9,3,6,1,4,7,2,5,8},
                {1,4,7,2,5,8,3,6,9},
                {2,5,8,3,6,9,4,7,1},
                {3,6,9,4,7,1,5,8,2},
                {4,7,1,5,8,2,6,9,3},
                {5,8,2,6,9,3,7,1,4},
                {6,9,3,7,1,4,8,2,5}
        };
        assertArrayEquals(answer, s.getSolution());
    }
}