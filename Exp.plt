set terminal pdfcairo enhanced size 29cm,21cm font "Times-New-Roman" fontscale .8
set output "Exp.pdf"
set title "{/:Roman exponential distribution}" 
set xlabel "{/:Italic x}"
set ylabel "{/:Italic p}({/:Italic x})"
set yrange [0:2]
set style fill solid border lc rgb "black" #ヒストグラムのスタイル
A = exp(1)/(exp(1)-1)
f(x) = A*exp(-x)
plot "Exp-output.txt" lc rgb "cyan" with boxes notitle, f(x) lw 5 lc rgb "red" notitle