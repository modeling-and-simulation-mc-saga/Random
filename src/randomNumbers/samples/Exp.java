package randomNumbers.samples;

import java.io.IOException;
import java.util.function.DoubleFunction;
import randomNumbers.AbstractRandom;
import randomNumbers.Transform;

/**
 * Exponential
 *
 * @author tadaki
 */
public class Exp extends AbstractSample {

    public Exp(AbstractRandom aRandom, double min,
            double max, int numBin, int numSamples) {
        super(aRandom, min, max, numBin, numSamples);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //Define inverse of distribution
        // A * exp (-x)
        double A = Math.E / (Math.E - 1);
        DoubleFunction<Double> invProDist = (x) -> {
            return -Math.log(1 - x / A);
        };
        //Transform method
        AbstractRandom aRandom = new Transform(invProDist);

        double min = 0.;
        double max = 1.;
        int numBin = 100;
        int numSamples = 100000;

        Exp exp = new Exp(aRandom, min, max, numBin, numSamples);
        String filename = Exp.class.getSimpleName() + "-output.txt";
        exp.outputHistogram(filename);
    }

}
