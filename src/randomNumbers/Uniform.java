package randomNumbers;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

/**
 * Uniform disturibution
 *
 * @author tadaki
 */
public class Uniform extends AbstractRandom {

    private final double min;
    private final double max;

    public Uniform(double min, double max) {
        this(min,max,0);
    }

    public Uniform(double min, double max, long seed) {
        super(seed);
        this.min = min;
        this.max = max;
    }

    @Override
    public double getNext() {
        return (max - min) * random.nextDouble() + min;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        double min = -1.;
        double max = 1.;
        int numBin = 100;//the number of bins
        int numSamples = 100000;//the number of random numbers
        //Generating histogram
        Histogram histogram = new Histogram(min, max, numBin);

        Uniform uniform = new Uniform(min, max);
        for (int i = 0; i < numSamples; i++) {
            double x = uniform.getNext();
            histogram.put(x);
        }

        List<Point2D.Double> plist = histogram.calculateFrequency();
        String filename = Uniform.class.getSimpleName() + "-output.txt";
        try (PrintStream out = new PrintStream(filename)) {
            plist.forEach(p -> out.println(p.x+" "+p.y));
        }
        System.out.println(histogram.checkNormalization(plist));
    }

}
