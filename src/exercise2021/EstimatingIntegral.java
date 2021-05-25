package exercise2021;

import estimatingPi.Pi;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.DoubleFunction;
import myLib.utils.FileIO;
import myLib.utils.Utils;

/**
 *
 * @author tadaki
 */
public class EstimatingIntegral {

    private final DoubleFunction<Double> func;//integrand
    private final double ymax;
    private final double xmin;
    private final double xmax;
    private int count = 0;
    private int allCount = 0;
    private final Random myRandom;

    public EstimatingIntegral(DoubleFunction<Double> func, double ymax,
            double xmin, double xmax, Random myRandom) {
        this.func = func;
        this.ymax = ymax;
        this.xmin = xmin;
        this.xmax = xmax;
        this.myRandom = myRandom;
    }

    /**
     * add one random point and return estimated value
     *
     * @return
     */
    public double addOne() {
        allCount++;






    }

    public Map<String, List<Point2D.Double>> generateRandomSamples(int n) {
        Map<String, List<Point2D.Double>> map = Utils.createMap();
        List<Point2D.Double> accept = Utils.createList();
        List<Point2D.Double> reject = Utils.createList();
        for (int i = 0; i < n; i++) {
            double x = myRandom.nextDouble() * (xmax - xmin) + xmin;
            double y = myRandom.nextDouble() * ymax;
            if (y < func.apply(x)) {
                accept.add(new Point2D.Double(x, y));
            } else {
                reject.add(new Point2D.Double(x, y));
            }
        }
        map.put("accept", accept);
        map.put("reject", reject);
        return map;
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
        
        Random myRandom = new Random(32943L);
        double xmin = 0.;
        double xmax = Math.PI / 2.;
        double ymax = 1.;
        double expected = Math.PI / 4.;//expected value of integral
        //intenrand
        DoubleFunction<Double> func = (x) -> Math.pow(Math.cos(x), 2.);
        EstimatingIntegral sys = new EstimatingIntegral(func,
                ymax, xmin, xmax, myRandom);
        int k = 0;
        int t = 0;
        String filename = EstimatingIntegral.class.getSimpleName() + "-output.txt";
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            while (k < n) {
                while (true) {
                    t++;
                    double x = sys.addOne();
                    if (t == numData[k]) {
                        //推計した値と厳密な値の差
                        double r = Math.abs(x - expected);
                        FileIO.writeSSV(out, t, x, r);
                        break;
                    }
                }
                k++;
            }
        }
        int nSample = 100;
        Map<String, List<Point2D.Double>> map = sys.generateRandomSamples(nSample);
        for (String s : map.keySet()) {
            filename = EstimatingIntegral.class.getSimpleName()
                    + "-" + s + "-output.txt";
            List<Point2D.Double> pList = map.get(s);
            try (BufferedWriter out = FileIO.openWriter(filename)) {
                for (Point2D.Double p : pList) {
                    FileIO.writeSSV(out, p.x, p.y);
                }
            }
        }
    }

}
