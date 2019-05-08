set terminal pdfcairo enhanced color size 29cm,21cm  fontscale 1.2
set output "Exp.pdf"
set xlabel "{/:Italic x}"
set ylabel "{/:Italic p}({/:Italic x})"
set title "exponential distribution" 
set log y
#set style fill solid border lc rgb "black" #ヒストグラムのスタイル
A = exp(1)/(exp(1)-1)
f(x) = A*exp(-x)
plot "Exp-output.txt" with boxes notitle, f(x) lw 3 notitle