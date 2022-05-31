package sudoku;

public class Generator {

    private int[][] field;
    private int[][] copyField;
    private final int[] numbers;
    private final int FIELD_SIZE = 9;
    private final GameModes.Modes MODE;
    Solver solver = new Solver();
    long m = System.currentTimeMillis();

    public int[][] getField() {
        return field;
    }

    public Generator(GameModes.Modes MODE) {
        this.MODE = MODE;
        field = new int[FIELD_SIZE][FIELD_SIZE];
        copyField = new int[FIELD_SIZE][FIELD_SIZE];
        numbers = new int[FIELD_SIZE];
    }

    //заполнение массива чисел от 1 до 9
    public void fillNumbersArr() {
        for(int i=0; i<FIELD_SIZE; i++) {
            numbers[i] = i+1;
        }
    }

    //случайное число
    private int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /* сдвиг в массиве чисел влево на заданное число */
    public int[] shiftLeft(int lengthShift) {
        int[] tempArrEnd = new int[lengthShift];
        for(int i=0; i<lengthShift; i++) {
            tempArrEnd[i] = numbers[i];
        }

        int[] tempArrBegin = new int[FIELD_SIZE-lengthShift];
        for(int i=0, k=lengthShift; i<FIELD_SIZE-lengthShift; i++, k++) {
            tempArrBegin[i] = numbers[k];
        }

        for(int i=0; i<tempArrBegin.length; i++) {
            numbers[i] = tempArrBegin[i];
        }
        for(int i=tempArrBegin.length, k=0; i<FIELD_SIZE; i++, k++) {
            numbers[i] = tempArrEnd[k];
        }
        return numbers;
    }

    /* создание базового поля:
    * в соответствие с правилами судоку
    * каждую строку сдвигаем на 3;
    * при переходе в новый горизонатльный район,
    * сдвигаем строку на 4*/
    public int[][] createBaseField() {
        fillNumbersArr();
        int counter = 0;

        for(int i=0; i<FIELD_SIZE; i++) {
            for(int j=0; j<FIELD_SIZE; j++) {
                field[i][j] = numbers[j];
            }

            counter++;
            if(counter % 3 != 0) {
                shiftLeft(3);
            }
            if(counter % 3 == 0) {
                shiftLeft(4);
            }

        }
        return field;
    }

    /* 1ый метод для перемешивания поля:
    * транспонирование матрицы */
    public int[][] transposeField() {
        for(int i=0; i<FIELD_SIZE; i++) {
            for(int j=i+1; j<FIELD_SIZE; j++) {
                int temp = field[i][j];
                field[i][j] = field[j][i];
                field[j][i] = temp;
            }
        }
        return field;
    }

    //случайная строка или столбец; от 0 до 8 в зависимости от района
    private int randomRowOrColumn(int district) {
        int max = 8;
        int min = 0;

        if(district == 1) {
            max = 2;
        }
        if(district == 2) {
            max = 5;
            min = 3;
        }
        if(district == 3) {
            min = 6;
        }

        return (int) (Math.random() * (max - min + 1) + min);
    }

    /* 2ой метод для перемешивания поля:
    * обмен строк внутри горизонтального района*/
    private void swapRowsInDistrict() {
        int district = randomNumber(1, 3); //номера районов, получаем случайный
        int row1 = randomRowOrColumn(district);
        int row2 = randomRowOrColumn(district);

        for(int j=0; j<FIELD_SIZE; j++) {
            int temp = field[row1][j];
            field[row1][j] = field[row2][j];
            field[row2][j] = temp;
        }
    }

    /* 3ий метод для перемешивания поля:
     * обмен столбцов внутри вертикального района*/
    private void swapColumnsInDistrict() {
        int district = randomNumber(1, 3); //номера районов, получаем случайный
        int column1 = randomRowOrColumn(district);
        int column2 = randomRowOrColumn(district);

        for(int i=0; i<FIELD_SIZE; i++) {
            int temp = field[i][column1];
            field[i][column1] = field[i][column2];
            field[i][column2] = temp;
        }
    }

    //с какой строки начинать обмен районов
    private int checkDistrict(int district) {
        int p = -1;
        if(district == 1) { //если 1 район
            p = 0; //то начинаем с 0 строки
        }
        if(district == 2) {
            p = 3;
        }
        if(district == 3) {
            p = 6;
        }
        return p;
    }

    /* 4ый метод для перемешивания поля:
     * обмен горизонтальных районов*/
    public void swapHorizontalDistricts() {
        final int district1 = randomNumber(1, 3);
        final int district2 = randomNumber(1, 3);
        int i1 = -1;
        int i2 = -1;

        i1 = checkDistrict(district1);
        i2 = checkDistrict(district2);

        int N = i1 + 3;
        for(int i=i1; i<N; i++, i1++, i2++) {
            for(int j=0; j<FIELD_SIZE; j++) {
                int temp = field[i1][j];
                field[i1][j] = field[i2][j];
                field[i2][j] = temp;
            }
        }
    }

    /* 5ый метод для перемешивания поля:
     * обмен вертикальных районов*/
    public void swapVerticalDistricts() {
        final int district1 = randomNumber(1, 3);
        final int district2 = randomNumber(1, 3);
        int j1 = -1;
        int j2 = -1;

        j1 = checkDistrict(district1);
        j2 = checkDistrict(district2);

        int N = j1 + 3;
        final int J1 = j1;
        final int J2 = j2;
        for(int i=0; i<FIELD_SIZE; i++) {
            for (int j = j1; j < N; j++, j1++, j2++) {
                int temp = field[i][j1];
                field[i][j1] = field[i][j2];
                field[i][j2] = temp;
            }
            j1 = J1;
            j2 = J2;
        }
    }

    //использование всех методов для перемешки поля в случайном порядке
    public void mixField(int count) {
        int max = 5; //количество методов перемешки
        int min = 1;

        for(int i=0; i<count; i++) {
            int rn = randomNumber(min, max); //выбор метода
            if(rn == 1) {
                transposeField();
            }
            if(rn == 2) {
                swapRowsInDistrict();
            }
            if(rn == 3) {
                swapColumnsInDistrict();
            }
            if(rn == 4) {
                swapHorizontalDistricts();
            }
            if(rn == 5) {
                swapVerticalDistricts();
            }
        }
    }

    //копирование матриц
    public void copyArray(int[][] copyArr, int[][]Arr) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                copyArr[i][j] = Arr[i][j];
            }
        }
    }

    //удаление клеток
    public int removeCells() {
        int min = 0;
        int max = 8;
        GameModes.chooseMode(MODE);
        int freeCells = new GameModes().getFreeCells();
        copyArray(copyField, field);

        for (int k = 0; k < freeCells; ) {
            int i = randomNumber(min, max); //выбор случайной клетки
            int j = randomNumber(min, max);
            while (field[i][j] == 0) {
                i = randomNumber(min, max);
                j = randomNumber(min, max);
            }

            int temp = field[i][j];
            field[i][j] = 0;

            if (solver.isOneSolution(field)) { //удаляем клетку, если без нее
                k++; //судоку будет иметь одно решение
                copyField[i][j] = 0;
            }
            if (!solver.isOneSolution(field)) {
                field[i][j] = temp;
            }
            if (System.currentTimeMillis() - m > 1000) {
                freeCells = new GameModes().getFreeCells();
            }
        }
        copyArray(field, copyField);
        return freeCells;
    }

    public void generateSudoku() {
        createBaseField();
        mixField(20);
        removeCells();
    }
}
