set terminal pdfcairo enhanced color size 29cm,21cm font "Times-New-Roman" fontscale 1.2
set output "Schrage.pdf"
set title "Schrage Method (#bin 100, #sample 100,000)"
set yrange [0:1.2]
p = 1./100
N = 100000
sigma = sqrt((1-p)/p/N)
set style fill solid border lc rgb "black" #ヒストグラムのスタイル
set label 1 at first 0.9,1.05+sigma "{/Symbol s}"
plot "Schrage-output.txt" with boxes notitle, 1+sigma lt -1 notitle, 1-sigma lt -1 notitle
