import java.util.ArrayList;
import java.awt.Point;

public class Board {
    public ArrayList<ArrayList<SodukoNumber>> board = new ArrayList();

    public Board() {
        for (int i = 0; i < 9; i++) {
            board.add(new ArrayList());
            for (int j = 0; j < 9; j++) {
                board.get(i).add(new SodukoNumber(i, j));
            }
        }
    }

    public Board(int[][] b) {
        for (int i = 0; i < 9; i++) {
            board.add(new ArrayList());
            for (int j = 0; j < 9; j++) {
                board.get(i).add(new SodukoNumber(i, j, b[i][j]));
            }
        }
    }

    public Board(ArrayList<ArrayList<SodukoNumber>> n) {
        board = n;
    }

    public ArrayList getBoard() {
        return board;
    }

    public int getNumber(int x, int y) {
        return board.get(y).get(x).getVal();
    }

    public ArrayList getNumbers(int x, int y) {
        return board.get(y).get(x).getVals();
    }

    public SodukoNumber get(int x, int y) {
        return board.get(y).get(x);
    }

    public void setVal(int x, int y, int val) {
        board.get(y).set(x, new SodukoNumber(x, y, val));
    }

    public void setVal(int x, int y, ArrayList vals) {
        board.get(y).set(x, new SodukoNumber(x, y, vals));
    }

    public void setVal(int x, int y, SodukoNumber s) {
        board.get(y).set(x, s);
    }

    public subBoard getSubBoard(int x, int y) {
        return new subBoard(x / 3, y / 3, board);
    }

    public Row getRow(int x, int y) {
        return new Row(x, y, board);
    }

    public Column getColumn(int x, int y) {
        return new Column(x, y, board);
    }

    public SodukoNumber getNextOpenRaw() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(j).get(i).getT().equals("list")) {
                    return board.get(j).get(i);
                }
            }
        }
        return new SodukoNumber(-1, -1, -1);

    }

    public Point getNextOpen() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board.get(j).get(i).isFilled()) {
                    return new Point(board.get(j).get(i).getX(), board.get(j).get(i).getY());
                }
            }
        }
        return new Point(0, 0);
    }

    public void showBoard() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (get(j, i).getT().equals("number")) {
                    if (getNumber(j, i) == 0) {
                        System.out.print("     ");
                    } else {
                        System.out.print(getNumber(j, i) + "    ");
                    }
                } else {
                    /* System.out.print("?    "); */System.out.print(getNumbers(j, i) + " ");
                }
                if ((j + 1) % 3 == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("________________________________________________");
            }
        }
        System.out.println();
        System.out.println();
    }

}

class subBoard {
    private ArrayList<Integer> sub = new ArrayList();
    ArrayList<ArrayList<SodukoNumber>> board;

    int X;
    int Y;

    public subBoard(int x, int y, ArrayList<ArrayList<SodukoNumber>> b) {
        X = x;
        Y = y;
        board = b;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((b.get(i + 3 * y).get(j + 3 * x).getVal()) != 0) {
                    sub.add(b.get(i + 3 * y).get(j + 3 * x).getVal());
                }
            }
        }

    }

    public ArrayList getIt() {
        return sub;
    }

    public Board getItRaw() {
        ArrayList<ArrayList<SodukoNumber>> b = board;
        ArrayList<ArrayList<SodukoNumber>> subB = new ArrayList();
        for (int i = 0; i < 3; i++) {
            subB.add(new ArrayList(3));
            for (int j = 0; j < 3; j++) {
                subB.get(i).add(b.get(i + 3 * Y).get(j + 3 * X));
            }
        }
        return new Board(subB);
    }
}

class Row {
    private ArrayList<Integer> sub = new ArrayList();

    public Row(int x, int y, ArrayList<ArrayList<SodukoNumber>> b) {
        for (int i = 0; i < 9; i++) {
            if (b.get(y).get(i).getVal() != 0) {
                sub.add(b.get(y).get(i).getVal());
            }
        }
    }

    public ArrayList getIt() {
        return sub;
    }
}

class Column {
    private ArrayList<Integer> sub = new ArrayList();

    public Column(int x, int y, ArrayList<ArrayList<SodukoNumber>> b) {
        for (int i = 0; i < 9; i++) {
            if (b.get(i).get(x).getVal() != 0) {
                sub.add(b.get(i).get(x).getVal());
            }
        }
    }

    public ArrayList getIt() {
        return sub;
    }
}