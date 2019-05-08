package randomNumbers;

import java.util.Random;

/**
 *
 * @author tadaki
 */
abstract public class AbstractRandom {

    protected final Random random;

    public AbstractRandom() {
        this(0);
    }

    /**
     * seedを指定してRandomを初期化
     *
     * @param seed
     */
    public AbstractRandom(long seed) {
        random = new Random();
        if (seed > 0) {
            random.setSeed(seed);
        }
    }

    abstract public double getNext();
}
