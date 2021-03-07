set terminal pdfcairo enhanced size 29cm,21cm  fontscale .5
set output "RandomSpin.pdf"
set title "Random Spin Monte Carlo {/:Italic N}=256"
set xlabel "{/:Italic t}"
set ylabel "energy"
#set ytic 500
plot for [i=0:4] sprintf("RandomSpin-%d.txt",i) with line lw 3 notitle

