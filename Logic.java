import java.util.*;

public class Logic {

    private final ArrayList<Integer> allNums = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    public SodukoNumber getPossibilities(int x, int y, Board b) {
        if (b.get(x, y).isFilled()) {
            return b.get(x, y);
        }

        ArrayList sub = b.getSubBoard(x, y).getIt();
        ArrayList row = b.getRow(x, y).getIt();
        ArrayList column = b.getColumn(x, y).getIt();

        ArrayList newNums = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        newNums.removeAll(sub);
        newNums.removeAll(row);
        newNums.removeAll(column);

        if (newNums.size() == 1) {
            return new SodukoNumber(x, y, (int) (newNums.get(0)));
            // return new SodukoNumber(x, y, newNums.get(0));
        }

        return (new SodukoNumber(x, y, newNums));
    }

    public ArrayList getUniqueSub(int x, int y, Board b) {
        ArrayList n = b.get(x, y).getVals();
        ArrayList p = removeALL(n, b.getSubBoard(x, y).getItRaw().get(0, 0).getVals());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                p = removeALL(n, b.getSubBoard(x, y).getItRaw().get(j, i).getVals());
            }
        }
        return p;
    }

    public ArrayList removeALL(ArrayList main, ArrayList sub) {
        main.removeAll(sub);
        return main;
    }

    public boolean attempt(SodukoNumber s, Board b) {
        // System.out.println(b.getSubBoard(s.getX(), s.getY()).getIt());
        // System.out.println(s.getVal() + ", " + !b.getSubBoard(s.getX(),
        // s.getY()).getIt().contains(s.getVal()));
        return (!b.getSubBoard(s.getX(), s.getY()).getIt().contains(s.getVal())
                && !b.getRow(s.getX(), s.getY()).getIt().contains(s.getVal())
                && !b.getColumn(s.getX(), s.getY()).getIt().contains(s.getVal()));
    }

    public int progress(Board b) {
        int total = b.getBoard().size() * b.getBoard().get(0).size();
        int counter = 0;
        for (int i = 0; i < b.getBoard().size(); i++) {
            for (int j = 0; j < b.getBoard().get(i).size(); j++) {
                if (b.get(j, i).isFilled()) {
                    counter++;
                }
            }
        }
        return counter * 100 / total;
    }
}