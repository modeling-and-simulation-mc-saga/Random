package linearCongruential;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import myLib.utils.FileIO;

/**
 * Schrageの方法を用いた疑似乱数生成
 * @author tadaki
 */
public class Schrage {

    private final int a;
    private final int m;
    private final int q;
    private final int r;
    private int x;

    public Schrage(int a, int m, int seed) {
        this.a = a;
        this.m = m;
        q = (int) ((double) m / a);
        x = seed;
        r = m % a;
    }

    public Schrage(int a, int m) {
        Date d = new Date();
        x = (int) (d.getTime() % m);
        this.a = a;
        this.m = m;
        q = (int) ((double) m / a);
        r = m % a;
    }

    public int getNext() {
        int y = (int) ((double) x / q);
        x = a * (x % q) - y * r;
        if (x < 0) {
            x += m;
        }
        return x;
    }

    public double getNextDouble() {
        int y = getNext();
        return (double) y / m;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        double min = 0.;//下限
        double max = 1.;//上限
        int numBin = 100;//binの数
        int numSamples = 100000;//乱数の総数
        //ヒストグラムを生成
        Histogram histogram = new Histogram(min, max, numBin);
        //乱数をn個生成し、ヒストグラムへ登録
        Schrage uniform = new Schrage(17807, Integer.MAX_VALUE);
        for (int i = 0; i < numSamples; i++) {
            double x = uniform.getNextDouble();
            histogram.put(x);
        }
        //ヒストグラムを出力
        List<Point2D.Double> plist = histogram.calculateFrequency();
        String filename = Schrage.class.getSimpleName() + "-output.txt";
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
        System.out.println(histogram.checkNormalization(plist));
    }
}
