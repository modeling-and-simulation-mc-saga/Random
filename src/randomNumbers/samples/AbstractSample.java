package randomNumbers.samples;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;
import randomNumbers.AbstractRandom;

/**
 *
 * @author tadaki
 */
public class AbstractSample {

    final protected AbstractRandom aRandom;
    final protected double min;
    final protected double max;
    final protected int numBin;
    final protected int numSamples;

    public AbstractSample(AbstractRandom aRandom,
            double min, double max, int numBin, int numSamples) {
        this.aRandom = aRandom;
        this.min = min;
        this.max = max;
        this.numBin = numBin;
        this.numSamples = numSamples;
    }

    public void outputHistogram(String filename) throws IOException {
        Histogram histogram = new Histogram(min, max, numBin);
        for (int i = 0; i < numSamples; i++) {
            double x = aRandom.getNext();
            histogram.put(x);
        }

        List<Point2D.Double> plist = histogram.calculateFrequency();
        try (PrintStream out = new PrintStream(filename)) {
            plist.forEach(p -> out.println(p.x + " " + p.y));
        }

    }
}
