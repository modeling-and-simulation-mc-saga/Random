package randomNumbers;

import java.util.Random;

/**
 *
 * @author tadaki
 */
abstract public class AbstractRandom {

    protected final Random random;

    public AbstractRandom(Random random){
        this.random = random;
    }
    
    /**
     * Initializing with specifying the seed
     *
     * @param seed
     */
    public AbstractRandom(long seed) {
        random = new Random();
        if (seed > 0) {
            random.setSeed(seed);
        }
    }
    
    public AbstractRandom() {
        this(new Random(0));
    }


    abstract public double getNext();
}
