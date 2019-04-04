package randomNumbers;

import java.util.function.Function;

/**
 * 変換法による乱数生成
 *
 * @author tadaki
 */
public class Transform extends AbstractRandom{

    private final Function<Double, Double> invProDist;//確率分布の逆関数

    /**
     * コンストラクタ
     *
     * @param invProDist 確率分布の逆関数
     */
    public Transform(Function<Double, Double> invProDist) {
        this.invProDist = invProDist;
    }

    /**
     * 乱数を一つ生成
     *
     * @return 生成された乱数
     */
    @Override
    public double getNext() {
        double x = Math.random();
        return invProDist.apply(x);
    }

}
