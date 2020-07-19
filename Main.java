import java.util.*;

public class Main {
    static Board soduko = new Board();

    static Board mid = new Board();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Logic logic = new Logic();

        // DEFINE YOUR POINTS BELOW WITH X BEING POSITIVE DIRECTION TO LEFT AND Y BEING
        // POSITIVE DIRECTION DOWN

        Scanner s = new Scanner(System.in);
        String list = s.nextLine();
        System.out.println(list.length() % 3 == 0);
        for (int i = 0; i < (list.length()) / 3; i++) {

            Main.soduko.setVal(Integer.parseInt(String.valueOf(list.charAt(i * 3))),
                    Integer.parseInt(String.valueOf(list.charAt(i * 3 + 1))),
                    Integer.parseInt(String.valueOf(list.charAt(i * 3 + 2))));
        }

        // 001115212028126224309504316418322708802613629721131044149248056257434539343451638836647841759853168266075279183463565572384489587662869671773888
        /*
         * while (isDone != 1) { int confirm = 0; System.out.println("x: "); int x =
         * s.nextInt();
         * 
         * System.out.println("y: "); int y = s.nextInt();
         * 
         * System.out.println("val: "); int val = s.nextInt(); while (confirm != 1) {
         * System.out.println("confirm with a 1: "); confirm = s.nextInt(); }
         * System.out.println(val + " at: " + x + ", " + y); Main.soduko.setVal(x, y,
         * val);
         * 
         * System.out.println("If you are done type 1: "); isDone = s.nextInt(); }
         */

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

        Main.solve(Main.soduko, new SodukoNumber(0, 0));

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
        // Logic l = new Logic();
        if (isNotFull(b)) {
            // b.showBoard();
            return true;
        }
        int nextX = b.getNextOpen().x;
        int nextY = b.getNextOpen().y;
        // b.showBoard();
        for (int i = 1; i < 9/* l.getPossibilities(s.getX(), s.getY(), b).getVals().size() */; i++) {
            int nextPossible = i;// l.getPossibilities(s.getX(), s.getY(), b).getVals().get(i);
            // System.out.println(nextX + ", " + nextY);
            // System.out.println(l.getPossibilities(s.getX(), s.getY(), b).getVals());
            // System.out.println(nextPossible);
            boolean works = (!b.getSubBoard(nextX, nextY).getIt().contains(nextPossible)
                    && !b.getRow(nextX, nextY).getIt().contains(nextPossible)
                    && !b.getColumn(nextX, nextY).getIt().contains(nextPossible));
            if (works) {

                b.setVal(nextX, nextY, nextPossible);
                // b.showBoard();
                if (solve(b, new SodukoNumber(b.getNextOpen().x, b.getNextOpen().y, nextPossible))) {
                    return true;
                } else {
                    b.setVal(nextX, nextY, 0);
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
                // Main.print(board, n);
                // System.out.println(l.progress(new Board(board)) + "%");
                // System.out.println();
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