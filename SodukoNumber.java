import java.util.*;

public class SodukoNumber {

    private int x;
    private int y;
    private int val;
    private ArrayList<Integer> vals = new ArrayList(Arrays.asList(0));

    String t;

    public SodukoNumber(int ex, int why) {
        x = why;
        y = ex;
        val = 0;
        vals = new ArrayList(Arrays.asList(0));
        t = "number";
    }

    public SodukoNumber(int ex, int why, int v) {
        x = why;
        y = ex;
        val = v;
        vals.set(0, v);
        vals = new ArrayList(Arrays.asList(v));
        t = "number";
    }

    public SodukoNumber(int ex, int why, Integer v) {
        x = why;
        y = ex;
        val = v;
        vals = new ArrayList(Arrays.asList(v));
        t = "number";
    }

    public SodukoNumber(int ex, int why, ArrayList v) {
        x = why;
        y = ex;
        vals = v;
        t = "list";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVal() {
        return val;
    }

    public ArrayList<Integer> getVals() {

        return vals;
    }

    public String getT() {
        return t;
    }

    public boolean isFilled() {
        return !(val == 0);
    }
}