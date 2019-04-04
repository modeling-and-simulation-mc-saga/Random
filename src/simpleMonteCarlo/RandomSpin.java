package simpleMonteCarlo;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * simple random spin system
 * @author tadaki
 */
public class RandomSpin {

    private final int n;//スピン数
    private final int[] s;//スピン
    private final double J[][];//相互作用
    private double energy = 0.;//energy

    /**
     * コンストラクタ
     * @param n スピン数
     */
    public RandomSpin(int n) {
        this.n = n;
        s = new int[n];
        J = new double[n][n];
        initializeJ();
    }

    /**
     * ランダムな相互作用を作成
     */
    private void initializeJ() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double r = Math.random() - 0.5;
                J[i][j] = r;
                J[j][i] = r;
            }
        }

    }

    /**
     * スピン配置をランダムに初期化
     */
    public void initialize() {
        for (int i = 0; i < n; i++) {
            s[i] = 2 * (int) (2 * Math.random()) - 1;
        }
        evalEnergy();
    }

    /**
     * 一ステップ
     * @return 
     */
    public double oneStep() {
        int k = (int) (n * Math.random());//ランダムにスピンを生成
        int ds = -2 * s[k];//スピンを反転する大きさ
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

    /**
     * ランダムスピンのMonte Carlo
     * @param args
     * @throws IOException 
     */
    public static void main(String args[]) throws IOException {
        int n = 256;//スピン数
        int tmax = 20;//Monte Carlo stepの上限
        int numSample = 5;//異なる初期配置の数
        RandomSpin sys = new RandomSpin(n);
        
        for (int k = 0; k < numSample; k++) {
            String filename = RandomSpin.class.getSimpleName() 
                    + String.valueOf(k) + ".txt";
            try (BufferedWriter out = myLib.utils.FileIO.openWriter(filename)) {
                sys.initialize();
                out.write(String.valueOf(sys.getEnergy()));
                out.newLine();
                for (int t = 0; t < tmax; t++) {
                    for (int i = 0; i < n; i++) {
                        sys.oneStep();
                    }
                    out.write(String.valueOf(sys.getEnergy()));
                    out.newLine();
                }
                out.newLine();
                out.newLine();
            }
        }
    }
}
