package randomNumbers;

import java.util.function.DoubleFunction;

/**
 * Rejection method
 *
 * @author tadaki
 */
public class Rejection extends AbstractRandom {

    private final DoubleFunction<Double> probDensity;//probability density function
    private final double min;//minimum of random numbers
    private final double max;//maximum of random numbers
    private final double maxOfFunction;//upper limit of probDensity

    /**
     *
     * @param probDensity probability density function
     * @param min minimum of random numbers
     * @param max maximum of random numbers
     * @param maxOfFunction upper limit of probDensity
     */
    public Rejection(DoubleFunction<Double> probDensity,
            double min, double max, double maxOfFunction) {
        this(probDensity, min, max, maxOfFunction, 0);
    }

    public Rejection(DoubleFunction<Double> probDensity,
            double min, double max, double maxOfFunction, long seed) {
        super(seed);
        this.probDensity = probDensity;
        this.min = min;
        this.max = max;
        this.maxOfFunction = maxOfFunction;
    }

    @Override
    public double getNext() {
        //becomes true if succeding to generate random number
        boolean done = false;
        double nextRandom = 0.;
        while (!done) {
            //generating two uniform random numbers
            double x = random.nextDouble();
            double y = random.nextDouble();
            nextRandom = (max - min) * x + min;
            done = (y < probDensity.apply(nextRandom) / maxOfFunction);
        }
        return nextRandom;
    }

}
