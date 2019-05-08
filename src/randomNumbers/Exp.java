package randomNumbers;

import histogram.Histogram;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.DoubleFunction;
import myLib.utils.FileIO;

/**
 * 指数分布に従う乱数
 *
 * @author tadaki
 */
public class Exp {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //指数分布に対応した分布関数の逆関数を定義
        // A * exp (-x)
        double A = Math.E / (Math.E - 1);
        DoubleFunction<Double> invProDist = (x) -> {
            return -Math.log(1 - x / A);
        };
        //変換法による乱数生成のインスタンス
        AbstractRandom aRandom = new Transform(invProDist);

        double min = 0.;//下限
        double max = 1.;//上限
        int numBin = 100;//binの数
        int numSamples = 100000;//乱数の総数
        //ヒストグラムを生成
        Histogram histogram = new Histogram(min, max, numBin);
        for (int i = 0; i < numSamples; i++) {
            double x = aRandom.getNext();
            histogram.put(x);
        }
        //ヒストグラムを出力
        List<Point2D.Double> plist = histogram.calculateFrequency();
        String filename = Exp.class.getSimpleName() + "-output.txt";
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            for (Point2D.Double p : plist) {
                FileIO.writeSSV(out, p.x, p.y);
            }
        }
    }

}
