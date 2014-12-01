xquery version "3.0";

subsequence( (
for $b in  doc("bd-agregador2.xml")/listaNot/listaUsuario/usuario 
    let $t :=count(for $c in doc("bd-agregador2.xml")/listaNot/noticia/listaCom/comentario/usuario where $c/@nick=$b/@nick return $b)
         where some $x in doc("bd-agregador2.xml")/listaNot/noticia/listaCom/comentario/usuario satisfies 
            $x/@nick = $b/@nick order by $t descending 
        return 
            <resultado-usr nombre="{$b/nombre}" 
                numero_comentarios="{ $t }"/>),1,10)