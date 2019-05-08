package randomNumbers;

import java.util.function.DoubleFunction;

/**
 * 棄却法による乱数生成
 *
 * @author tadaki
 */
public class Rejection  extends AbstractRandom{

    private final DoubleFunction<Double> probDensity;//確率密度関数
    private final double min;//乱数生成の下限
    private final double max;//乱数生成の上限
    private final double maxOfFunction;//確率密度関数の最大値

    /**
     * コンストラクタ
     *
     * @param probDensity 確率密度関数
     * @param min 乱数生成の下限
     * @param max 乱数生成の上限
     * @param maxOfFunction 確率密度関数の最大値
     */
    public Rejection(DoubleFunction<Double> probDensity, 
            double min, double max, double maxOfFunction) {
        this.probDensity = probDensity;
        this.min = min;
        this.max = max;
        this.maxOfFunction = maxOfFunction;
    }

    /**
     * 乱数を一つ生成
     *
     * @return 生成された乱数
     */
    @Override
    public double getNext() {
        boolean done = false;//乱数が生成されるとtrueとなる
        double nextRandom = 0.;
        while (!done) {
            //二つの乱数
            double x = Math.random();
            double y = Math.random();
            nextRandom = (max - min) * x + min;
            done = (y < probDensity.apply(nextRandom) / maxOfFunction);
        }
        return nextRandom;
    }

}
