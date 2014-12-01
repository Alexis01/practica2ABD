xquery version "3.0";


for $b in distinct-values( doc("bd-agregador.xml")/listaNot/noticia/listaEtiq/etiqueta)order by $b
return $b
    