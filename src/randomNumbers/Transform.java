package randomNumbers;

import java.util.function.DoubleFunction;

/**
 * 変換法による乱数生成
 *
 * @author tadaki
 */
public class Transform extends AbstractRandom {

    private final DoubleFunction<Double> invProDist;//確率分布の逆関数

    /**
     * コンストラクタ
     *
     * @param invProDist 確率分布の逆関数
     */
    public Transform(DoubleFunction<Double> invProDist) {
        this(invProDist,0);
    }

    public Transform(DoubleFunction<Double> invProDist, long seed) {
        super(seed);
        this.invProDist = invProDist;
    }

    /**
     * 乱数を一つ生成
     *
     * @return 生成された乱数
     */
    @Override
    public double getNext() {
        double x = random.nextDouble();
        return invProDist.apply(x);
    }

}
