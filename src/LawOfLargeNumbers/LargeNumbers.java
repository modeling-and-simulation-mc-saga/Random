package LawOfLargeNumbers;

import randomNumbers.AbstractRandom;
import randomNumbers.Uniform;
import java.io.PrintStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Law of large numbers
 *
 * @author tadaki
 */
public class LargeNumbers {

    private final AbstractRandom myRandom;

    public LargeNumbers(AbstractRandom myRandom) {
        this.myRandom = myRandom;
    }

    /**
     * sample mean
*
     * @param numSample
     * @return
     */
    public double generateSampleMean(int numSample) {
        double sum = 0.;
        for (int i = 0; i < numSample; i++) {
            double x = myRandom.getNext();
            sum += x;
        }
        return sum / numSample;
    }

    /**
     * observe the law of large number by changing sample size
*
     * @param from initial sample size
     * @param max maximum sample size
     * @param nSamples the number of samples 
     * @return
     */
    public List<Result> observeSizeDependence(int from, int max, int nSamples) {
        int num = from;
        List<Result> plist = Collections.synchronizedList(new ArrayList<>());
        while (num <= max) {//incleasins sample size
            double sum = 0.;//sum
            double sum2 = 0.;//square sum
            for (int i = 0; i < nSamples; i++) {
                double x = generateSampleMean(num);
                sum += x;
                sum2 += x * x;
            }
            sum /= nSamples;//mean
            sum2 /= nSamples;
            sum2 -= sum * sum;//divergence
            plist.add(new Result(num, sum, sum2));
            num *= 2;
        }
        return plist;
    }

    public static void main(String args[]) throws IOException {
        int nSamples = 1000;//the number of samples
        int num = 16;//initial sample size
        int numMax = 16384;//maximum sample size
        //uniform random number generator in [-.5,.5)
        AbstractRandom myRandom = new Uniform(-0.5, 0.5);

        LargeNumbers ln = new LargeNumbers(myRandom);
        List<Result> plist = ln.observeSizeDependence(num, numMax, nSamples);

        String filename = LargeNumbers.class.getSimpleName() + ".txt";
        try (PrintStream out = new PrintStream(filename)) {
            plist.forEach(p -> out.println(p.toString()));
        }
    }
}
