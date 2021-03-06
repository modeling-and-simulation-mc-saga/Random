set terminal pdfcairo enhanced size 29cm,21cm font "Times-New-Roman" fontscale .5
set output "Pi.pdf"
set title "Estimating {/:Symbol p}"
set format x "10^{%L}"
set lmargin 5
set multiplot layout 2,1
set log x
set yrange [0.7:0.9]
set tmargin 2
set bmargin 1.5
unset xtics
plot "Pi-output.txt" ps 2 pt 7 title "simulation",pi/4 lw 3 title "{/Symbol p}/4"
set tmargin .5
set bmargin 3
set xtics
set xlabel "{/:Italic N}"
unset title
set yrange[*:*]
set log xy
set format xy "10^{%L}"
f(x) = sqrt((1-pi/4)*(pi/4))/sqrt(x)
plot "Pi-output.txt" u 1:3 ps 2 pt 7 title "{/:Roman simulation}",\
f(x) with line lw 3 title "((1-{/Symbol p}/4)({/Symbol p}/4))^{1/2}{/:Italic N}^{-1/2}"

