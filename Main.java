import java.util.*;

public class Main {
    static Board soduko = new Board();

    static Board mid = new Board();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Logic logic = new Logic();

        // DEFINE YOUR POINTS BELOW WITH X BEING POSITIVE DIRECTION TO LEFT AND Y BEING
        // POSITIVE DIRECTION DOWN
        Main.soduko.setVal(0, 0, 5);
        Main.soduko.setVal(0, 1, 6);
        Main.soduko.setVal(1, 0, 3);
        Main.soduko.setVal(1, 2, 9);
        Main.soduko.setVal(2, 2, 8);
        Main.soduko.setVal(3, 1, 1);
        Main.soduko.setVal(4, 1, 9);
        Main.soduko.setVal(5, 1, 5);
        Main.soduko.setVal(4, 0, 7);
        Main.soduko.setVal(7, 2, 6);
        Main.soduko.setVal(0, 3, 8);
        Main.soduko.setVal(0, 4, 4);
        Main.soduko.setVal(0, 5, 7);
        Main.soduko.setVal(3, 4, 8);
        Main.soduko.setVal(4, 3, 6);
        Main.soduko.setVal(5, 4, 3);
        Main.soduko.setVal(4, 5, 2);
        Main.soduko.setVal(8, 3, 3);
        Main.soduko.setVal(8, 4, 1);
        Main.soduko.setVal(8, 5, 6);
        Main.soduko.setVal(1, 6, 6);
        Main.soduko.setVal(3, 7, 4);
        Main.soduko.setVal(4, 7, 1);
        Main.soduko.setVal(5, 7, 9);
        Main.soduko.setVal(4, 8, 8);
        Main.soduko.setVal(6, 6, 2);
        Main.soduko.setVal(7, 6, 8);
        Main.soduko.setVal(8, 7, 5);
        Main.soduko.setVal(8, 8, 9);
        Main.soduko.setVal(7, 8, 7);

        Main.soduko.showBoard();

        System.out.println();
        System.out.println();
        System.out.println();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.soduko));
            }
        }

        /*
         * for (int y = 0; y < 9; y++) { for (int x = 0; x < 9; x++) {
         * Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.mid)); } }
         * 
         * for (int y = 0; y < 9; y++) { for (int x = 0; x < 9; x++) {
         * Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.mid)); } }
         * 
         * for (int y = 0; y < 9; y++) { for (int x = 0; x < 9; x++) {
         * Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.mid)); } }
         * 
         * for (int y = 0; y < 9; y++) { for (int x = 0; x < 9; x++) {
         * Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.mid)); } }
         * 
         * for (int y = 0; y < 9; y++) { for (int x = 0; x < 9; x++) {
         * Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.mid)); } }
         */

        while (isNotFull(mid)) {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    mid.setVal(x, y, logic.getPossibilities(x, y, Main.mid));
                }
            }
        }
        mid.showBoard();
    }

    public static boolean isNotFull(Board b) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (b.get(x, y).getT().equals("list")) {
                    return true;
                }
            }
        }
        return false;
    }
}