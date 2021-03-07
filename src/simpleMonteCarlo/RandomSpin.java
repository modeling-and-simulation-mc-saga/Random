package simpleMonteCarlo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 * simple random spin system
 * @author tadaki
 */
public class RandomSpin extends SpinSystem {


    /**
     * コンストラクタ
     * @param n スピン数
     */
    public RandomSpin(int n, Random random) {
        super(n,random);
    }

    /**
     * ランダムな相互作用を作成
     */
    @Override
    protected void initializeJ() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double r = random.nextDouble() - 0.5;
                J[i][j] = r;
                J[j][i] = r;
            }
        }

    }


    /**
     * ランダムスピンのMonte Carlo
     * @param args
     * @throws IOException 
     */
    public static void main(String args[]) throws IOException {
        int n = 256;//スピン数
        int tmax = 100;//Monte Carlo stepの上限
        int numSample = 5;//異なる初期配置の数
        long seed = 32124L;
        RandomSpin sys = new RandomSpin(n,new Random(seed));
        
        for (int k = 0; k < numSample; k++) {
            String filename = RandomSpin.class.getSimpleName() +"-"
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
