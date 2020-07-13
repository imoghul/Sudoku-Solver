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
}