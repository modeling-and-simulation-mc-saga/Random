package estimatingPi;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * estimating pi by simulation
 *
 * @author tadaki
 */
public class Pi {

    private int in;
    private int all;

    public Pi() {
        in = 0;
        all = 0;
    }

    public double addOne() {
        all++;
        double x = Math.random();
        double y = Math.random();
        double r = Math.sqrt(x * x + y * y);
        if (r < 1.) {
            in++;
        }
        return (double) in / all;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int n = 25;
        //データの個数を保存する配列：2^n
        int numData[] = new int[n];
        for (int i = 0; i < n; i++) {
            numData[i] = (int) Math.pow(2., (double) i);
        }

        Pi pi = new Pi();
        int k = 1;
        int t = 0;
        String filename = Pi.class.getSimpleName() + "-output.txt";
        try (BufferedWriter out = myLib.utils.FileIO.openWriter(filename)) {
            while (k < n) {
                double x = pi.addOne();
                if (t == numData[k]) {
                    //推計したpiの値と厳密な値の差
                    double r = Math.abs(x - Math.PI/4);
                    myLib.utils.FileIO.writeSSV(out, t, x, r);
                    k++;
                }
                t++;
            }
        }
    }

}
