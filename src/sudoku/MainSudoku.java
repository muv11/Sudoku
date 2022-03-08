package sudoku;

public class MainSudoku {

    public static void main(String[] args) {

        Generator object = new Generator();

        object.createBaseField();
        object.showField();

        System.out.print("\n");

        object.mixField(10);
        object.showField();

    }
}
