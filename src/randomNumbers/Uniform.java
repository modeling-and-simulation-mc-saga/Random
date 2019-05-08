package randomNumbers;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;

/**
 * 一様乱数の例
 *
 * @author tadaki
 */
public class Uniform extends AbstractRandom {

    private final double min;
    private final double max;

    /**
     * コンストラクタ
     *
     * @param min 下限
     * @param max 上限
     */
    public Uniform(double min, double max) {
        this(min,max,0);
    }

    public Uniform(double min, double max, long seed) {
        super(seed);
        this.min = min;
        this.max = max;
    }

    /**
     * 乱数を一つ生成
     *
     * @return
     */
    @Override
    public double getNext() {
        return (max - min) * random.nextDouble() + min;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        double min = -1.;//下限
        double max = 1.;//上限
        int numBin = 100;//binの数
        int numSamples = 100000;//乱数の総数
        //ヒストグラムを生成
        Histogram histogram = new Histogram(min, max, numBin);
        //乱数をn個生成し、ヒストグラムへ登録
        Uniform uniform = new Uniform(min, max);
        for (int i = 0; i < numSamples; i++) {
            double x = uniform.getNext();
            histogram.put(x);
        }
        //ヒストグラムを出力
        List<Point2D.Double> plist = histogram.calculateFrequency();
        String filename = Uniform.class.getSimpleName() + "-output.txt";
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
        System.out.println(histogram.checkNormalization(plist));
    }

}
