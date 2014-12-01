xquery version "3.0";
declare variable $etiqueta as xs:string external;

   for $p in doc("bd-agregador.xml")/listaNot/noticia
        where $p/listaEtiq/etiqueta=$etiqueta 
            return <resultado-noticia  id="{$p/@idNoticia}" titular="{$p/titular}" />
