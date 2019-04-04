set terminal pdfcairo enhanced color size 29cm,21cm font "Times-New-Roman" fontscale 1.2
set output "LargeNumberMean.pdf"
set title "Law of Large Numbers (mean)"
set log x
set format x "10^{%L}"
plot "LargeNumbers.txt" ps 2 pt 7 title "simulation",\
0 lw 3 title "theory"

set title "Law of Large Numbers (variance)"
set output "LargeNumberVariance.pdf"
set log xy
set format y "10^{%L}"
plot "LargeNumbers.txt" u 1:3 ps 2 pt 7 title "simulation",\
1./12/x ls 3 title "theory"