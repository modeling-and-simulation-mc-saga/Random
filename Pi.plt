set terminal pdfcairo enhanced color size 29cm,21cm  fontscale 1.2
set output "Pi.pdf"
set title "Estimating {/Symbol p}"
set format x "10^{%L}"
set log x
set yrange [0.7:0.9]
plot "Pi-output.txt" ps 2 pt 7 title "simulation",pi/4 lw 3 title "{/Symbol p}/4"
set output "Pi2.pdf"
set yrange[*:*]
set log xy
set format xy "10^{%L}"
f(x) = sqrt((1-pi/4)*(pi/4))/sqrt(x)
plot "Pi-output.txt" u 1:3 ps 2 pt 7 title "simulation",\
f(x) with line lw 3 title "((1-{/Symbol p}/)({/Symbol p}/4))^{1/2}{/:Italic N}^{-1/2}"

