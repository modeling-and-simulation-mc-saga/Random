package simpleMonteCarlo;

import java.util.Random;

/**
 *
 * @author tadaki
 */
abstract public class SpinSystem {
    
    protected final int n; //スピン数
    protected final int[] s; //スピン
    protected final double[][] J; //相互作用
    protected double energy = 0.; //energy
    protected Random random;

    public SpinSystem(int n, Random random) {
        this.n = n;
        s = new int[n];
        J = new double[n][n];        
        this.random = random;
        initializeJ();
    }

    /**
     * スピン配置をランダムに初期化
     */
    public void initialize() {
        for (int i = 0; i < n; i++) {
            s[i] = 2 * (int) (2 * random.nextDouble()) - 1;
        }
        evalEnergy();
    }
    
    abstract protected void initializeJ();

    /**
     * 一ステップ
     *
     * @return
     */
    public double oneStep() {
        int k = random.nextInt(n); //ランダムにスピンを生成
        int ds = -2 * s[k]; //スピンを反転する大きさ
        //エネルギー変化を計算
        double de = 0.;
        for (int j = 0; j < n; j++) {
            de += -2 * J[k][j] * s[j] * ds;
        }
        //エネルギーが下がる場合に、スピンを本当に反転する
        if (de < 0) {
            energy += de;
            s[k] += ds;
        }
        return de;
    }

    /**
     * エネルギーを計算
     *
     * @return
     */
    public double evalEnergy() {
        energy = 0.;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                energy += J[i][j] * s[i] * s[j];
            }
        }
        return energy;
    }
    
    public double getEnergy() {
        return energy;
    }
    
}
