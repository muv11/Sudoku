package sudokuTest;

import org.junit.jupiter.api.Test;
import sudoku.GameModes;
import sudoku.Generator;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    Generator g = new Generator(GameModes.Modes.TEST);

    @Test
    void shiftLeftTest() {
        g.fillNumbersArr();
        int[] shiftedArr = new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3};
        assertArrayEquals(shiftedArr, g.shiftLeft(3));
    }

    @Test
    void createBaseFieldTest() {
        int[][] baseField = new int[][] {
                {1,2,3,4,5,6,7,8,9},
                {4,5,6,7,8,9,1,2,3},
                {7,8,9,1,2,3,4,5,6},
                {2,3,4,5,6,7,8,9,1},
                {5,6,7,8,9,1,2,3,4},
                {8,9,1,2,3,4,5,6,7},
                {3,4,5,6,7,8,9,1,2},
                {6,7,8,9,1,2,3,4,5},
                {9,1,2,3,4,5,6,7,8}
        };
        assertArrayEquals(baseField, g.createBaseField());
    }

    @Test
    void transposeFieldTest() {
        g.createBaseField();
        int[][] transposedField = new int[][] {
                {1,4,7,2,5,8,3,6,9},
                {2,5,8,3,6,9,4,7,1},
                {3,6,9,4,7,1,5,8,2},
                {4,7,1,5,8,2,6,9,3},
                {5,8,2,6,9,3,7,1,4},
                {6,9,3,7,1,4,8,2,5},
                {7,1,4,8,2,5,9,3,6},
                {8,2,5,9,3,6,1,4,7},
                {9,3,6,1,4,7,2,5,8}
        };
        assertArrayEquals(transposedField, g.transposeField());
    }

    @Test
    void removeCellTest() {
        final int SIZE = 9;
        int[][]field;
        g.createBaseField();
        g.mixField(20);
        g.removeCells();
        field = g.getField();
        int c = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == 0) {
                    c++;
                }
            }
        }
        assertEquals(g.removeCells(), c);
    }

}