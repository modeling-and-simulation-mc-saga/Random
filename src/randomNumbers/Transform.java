package randomNumbers;

import java.util.function.DoubleFunction;

/**
 * Transform method
 *
 * @author tadaki
 */
public class Transform extends AbstractRandom {

    private final DoubleFunction<Double> invProDist;//inverse of distribution
    
    /**
    *
     * @param invProDist inverse of distribution
     */
    public Transform(DoubleFunction<Double> invProDist) {
        this(invProDist,0);
    }

    public Transform(DoubleFunction<Double> invProDist, long seed) {
        super(seed);
        this.invProDist = invProDist;
    }

    @Override
    public double getNext() {
        double x = random.nextDouble();
        return invProDist.apply(x);
    }

}
