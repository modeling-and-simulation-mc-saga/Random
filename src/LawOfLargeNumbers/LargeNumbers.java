package LawOfLargeNumbers;

import randomNumbers.AbstractRandom;
import randomNumbers.Uniform;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;
import myLib.utils.Utils;

/**
 * 大数の法則を確認する。
 * @author tadaki
 */
public class LargeNumbers {
    private final AbstractRandom myRandom;//乱数生成器

    public LargeNumbers(AbstractRandom myRandom) {
        this.myRandom = myRandom;
    }
 
    /**
     * サンプル平均の生成
     * @param numSample サンプルサイズ
     * @return 
     */
    double generateSampleMean(int numSample){
        double sum=0.;
        for(int i=0;i<numSample;i++){
            double x = myRandom.getNext();
            sum += x;
        }
        return sum/numSample;
    }
    
    public static void main(String args[]) throws IOException{
        int nSamples=1000;//各サイズのサンプルを生成する数
        int num = 16;//サンプルサイズ初期値
        List<Result> plist=Utils.createList();
        //一様分布の乱数を定義
        AbstractRandom myRandom = new Uniform(-0.5,0.5);
        
        LargeNumbers ln=new LargeNumbers(myRandom);
        while(num <=16384){
            double sum=0.;//和
            double sum2=0.;//二乗和
            for(int i=0;i<nSamples;i++){
                double x=ln.generateSampleMean(num);
                sum += x;
                sum2 += x*x;
            }
            sum /= nSamples;//平均
            sum2 /= nSamples;
            sum2 -= sum*sum;//分散
            plist.add(new Result(num,sum,sum2));
            num *=2;
        }
        //結果出力
        String filename = LargeNumbers.class.getSimpleName()+".txt";
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            for(Result p:plist){
                out.write(p.toString());
                out.newLine();
            }
        }
    }
}
