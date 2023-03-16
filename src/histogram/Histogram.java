package histogram;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Histogram
 *
 * @author tadaki
 */
public class Histogram {

    private final double min;
    private final double max;
    private final double binWidth;
    private final int hist[];

    /**
     * Constructor with specifyinng the number of bins
     *
     * @param min
     * @param max
     * @param numBin
     */
    public Histogram(double min, double max, int numBin) {
        this.min = min;
        this.max = max;
        binWidth = (max - min) / numBin;
        hist = new int[numBin];
    }

    /**
     * Constructor with specifying the width of bins
     *
     * @param min
     * @param max
     * @param binWidth
     */
    public Histogram(double min, double max, double binWidth) {
        this.min = min;
        this.binWidth = binWidth;
        int numBin = (int) ((max - min) / binWidth);
        if (min + numBin * binWidth < max) {
            numBin++;
        }
        this.max = min + numBin * binWidth;
        hist = new int[numBin];
    }

    /**
     * register one value
     *
     * @param x
     * @return
     */
    public int put(double x) {
        if (x < min || x >= max) {//if x is out of the area
            return -1;
        }
        // find the index of the bin
        int binIndex = (int) ((x - min) / binWidth);
        // increate the count of the bin
        hist[binIndex]++;
        return binIndex;
    }

    public double getLowerBound() {
        return min;
    }

    public double getUpperBound() {
        return max;
    }

    public int[] getHist() {
        return hist;
    }

    public int getCount(int index){
        if(index<0||index>=hist.length)return -1;
        return hist[index];
    }
    
    /**
     * returning the result
     *
     * the values are normalized as the probability density
     *
     * @return
     */
    public List<Point2D.Double> calculateFrequency() {
        List<Point2D.Double> pointList = 
                Collections.synchronizedList(new ArrayList<>());
        //the total number of data
        int sum = 0;
        for (int i = 0; i < hist.length; i++) {
            sum += hist[i];
        }

        for (int i = 0; i < hist.length; i++) {
            double x = min + i * binWidth + binWidth / 2.;//the half value of a bin
            double y = (double) hist[i] / sum / binWidth;//relative frequency
            pointList.add(new Point2D.Double(x, y));
        }
        return pointList;
    }

    /**
     * validate normalized
     *
     * @param plist
     * @return
     */
    public double checkNormalization(List<Point2D.Double> plist) {
        double frequency = 0.;
        for (Point2D.Double p : plist) {
            frequency += p.y * binWidth;
        }
        return frequency;
    }
}
