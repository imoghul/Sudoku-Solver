import java.util.*;

public class Main {
    static Board soduko = new Board();

    static Board mid = new Board();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Logic logic = new Logic();

        // DEFINE YOUR POINTS BELOW WITH X BEING POSITIVE DIRECTION TO LEFT AND Y BEING
        // POSITIVE DIRECTION DOWN

        /*
         * Scanner s = new Scanner(System.in); int isDone = 0; while (isDone != 1) { int
         * confirm = 0; System.out.println("x: "); int x = s.nextInt();
         * 
         * System.out.println("y: "); int y = s.nextInt();
         * 
         * System.out.println("val: "); int val = s.nextInt(); while (confirm != 1) {
         * System.out.println("confirm with a 1: "); confirm = s.nextInt(); }
         * System.out.println(val + " at: " + x + ", " + y); Main.soduko.setVal(x, y,
         * val);
         * 
         * System.out.println("If you are done type 1: "); isDone = s.nextInt(); }(/)
         */

        Main.soduko.setVal(1, 1, 8);
        Main.soduko.setVal(0, 2, 6);
        Main.soduko.setVal(2, 2, 2);

        Main.soduko.setVal(5, 1, 7);

        Main.soduko.setVal(6, 0, 2);
        Main.soduko.setVal(7, 1, 9);
        Main.soduko.setVal(6, 2, 5);

        Main.soduko.setVal(1, 3, 7);

        Main.soduko.setVal(3, 4, 9);
        Main.soduko.setVal(4, 3, 6);
        Main.soduko.setVal(5, 4, 1);
        Main.soduko.setVal(4, 5, 2);

        Main.soduko.setVal(7, 5, 4);

        Main.soduko.setVal(2, 6, 5);
        Main.soduko.setVal(1, 7, 9);
        Main.soduko.setVal(2, 8, 6);

        Main.soduko.setVal(3, 7, 4);

        Main.soduko.setVal(6, 6, 6);
        Main.soduko.setVal(7, 7, 7);
        Main.soduko.setVal(8, 6, 3);

        // Main.soduko.showBoard();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Main.mid.setVal(x, y, logic.getPossibilities(x, y, Main.soduko));
            }
        }

        int[][] newBoard = new int[9][9];

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                newBoard[x][y] = Main.soduko.getNumber(y, x);
            }
        }

        Main.soduko.showBoard();
        System.out.println();
        Main.solveSudoku(newBoard, 9);
        new Board(newBoard).showBoard();
        // Main.solve(Main.soduko, new SodukoNumber(Main.soduko.getNextOpen().x,
        // Main.mid.getNextOpen().y,
        // Main.mid.getNumbers(Main.soduko.getNextOpen().x,
        // Main.soduko.getNextOpen().y).get(0)));
        // Main.solve(Main.soduko, new SodukoNumber(0, 0, 1));
        // Main.solve(Main.mid).showBoard();

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

    public static Board solveBad(Board b) {
        Logic l = new Logic();
        while (isNotFull(b)) {
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    Main.mid.setVal(x, y, l.getPossibilities(x, y, Main.mid)); //
                    // mid.showBoard(); // System.out.println(); // System.out.println(); } } }
                }
            }
        }
        return b;
    }

    public static boolean solve(Board b, SodukoNumber s) {
        Logic l = new Logic();
        if (isNotFull(b)) {
            b.showBoard();
            return true;
        }
        int nextX = b.getNextOpen().x;
        int nextY = b.getNextOpen().y;
        b.showBoard();
        for (int i = 0; i < l.getPossibilities(s.getX(), s.getY(), b).getVals().size(); i++) {
            int nextPossible = l.getPossibilities(s.getX(), s.getY(), b).getVals().get(i);
            // System.out.println(nextX + ", " + nextY);
            // System.out.println(l.getPossibilities(s.getX(), s.getY(), b).getVals());
            boolean works = (!b.getSubBoard(nextX, nextY).getIt().contains(nextPossible)
                    && !b.getRow(nextX, nextY).getIt().contains(nextPossible)
                    && !b.getColumn(nextX, nextY).getIt().contains(nextPossible));
            if (works) {

                b.setVal(nextX, nextY, nextPossible);
                if (solve(b, new SodukoNumber(b.getNextOpen().x, b.getNextOpen().y, nextPossible))) {
                    return true;
                }

            }

        }
        // System.out.println(nextX);
        return false;

    }

    public static void print(int[][] board, int N) {
        // we got the answer, just print it
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int) Math.sqrt(N) == 0) {
                System.out.print("");
            }
        }
    }

    public static boolean solveSudoku(int[][] board, int n) {
        Logic l = new Logic();
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;

                    // we still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // no empty space left
        if (isEmpty) {
            return true;
        }

        // else for each-row backtrack
        for (int num = 1; num <= n; num++) {

            if (l.attempt(new SodukoNumber(row, col, num), new Board(board))) {
                board[row][col] = num;
                Main.print(board, n);
                System.out.println();
                if (solveSudoku(board, n)) {

                    return true;
                } else {
                    // replace it
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

}