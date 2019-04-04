package LawOfLargeNumbers;

import java.util.StringJoiner;

/**
 * 大数の法則に関するシミュレーション結果を保持するクラス
 * @author tadaki
 */
public class Result {
    public final int n;//サンプルサイズ
    public final double m;//平均
    public final double s;//分散

    public Result(int n, double m, double s) {
        this.n = n;
        this.m = m;
        this.s = s;
    }
    
    @Override
    public String toString(){
        StringJoiner sj = new StringJoiner(" ","","");
        sj.add(String.valueOf(n)).add(String.valueOf(m)).add(String.valueOf(s));
        return sj.toString();
    }
}
