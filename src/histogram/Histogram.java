package histogram;

import java.awt.geom.Point2D;
import java.util.List;
import myLib.utils.Utils;

/**
 * ヒストグラム
 *
 * @author tadaki
 */
public class Histogram {

    private final double min;//範囲の下限
    private final double max;//範囲の上限
    private final double binWidth;//bin の幅
    private final int hist[];//ヒストグラム

    /**
     * コンストラクタ：binの数を指定
     *
     * @param min 下限
     * @param max 上限
     * @param numBin binの数
     */
    public Histogram(double min, double max, int numBin) {
        this.min = min;
        this.max = max;
        binWidth = (max - min) / numBin;
        hist = new int[numBin];
    }

    /**
     * コンストラクタ：binの幅を指定
     *
     * @param min 下限
     * @param max 上限
     * @param binWidth binの幅
     */
    public Histogram(double min, double max, double binWidth) {
        this.min = min;
        this.binWidth = binWidth;
        int numBin = (int) ((max - min) / binWidth);
        if (min + numBin * binWidth < max) {
            numBin++;
        }
        this.max = min + numBin * binWidth;
        hist = new int[numBin];
    }

    /**
     * 値を一つ登録する
     *
     * @param x
     * @return
     */
    public int put(double x) {
        if (x < min || x >= max) {//範囲外の場合
            return -1;
        }
        //xが入るべきbinの番号を調べる
        int binIndex = (int) ((x - min) / binWidth);
        //bin のカウントを一つ増やす
        hist[binIndex]++;
        return binIndex;
    }

    public double getLowerBound() {
        return min;
    }

    public double getUpperBound() {
        return max;
    }

    public int[] getHist() {
        return hist;
    }

    public int getCount(int index){
        if(index<0||index>=hist.length)return -1;
        return hist[index];
    }
    /**
     * 結果をリストとして取得する
     *
     * 値は確率になるように規格化する
     *
     * @return
     */
    public List<Point2D.Double> calculateFrequency() {
        List<Point2D.Double> pointList = Utils.createList();
        //カウントの総和を求める
        int sum = 0;
        for (int i = 0; i < hist.length; i++) {
            sum += hist[i];
        }

        for (int i = 0; i < hist.length; i++) {
            double x = min + i * binWidth + binWidth / 2.;//binの中央値
            double y = (double) hist[i] / sum / binWidth;//binに入る割合
            pointList.add(new Point2D.Double(x, y));
        }
        return pointList;
    }

    /**
     * plistが規格化されていることを確かめる
     *
     * @param plist
     * @return
     */
    public double checkNormalization(List<Point2D.Double> plist) {
        double frequency = 0.;
        for (Point2D.Double p : plist) {
            frequency += p.y * binWidth;
        }
        return frequency;
    }
}
