xquery version "3.0";

declare variable $id_Noticia as xs:string external;

 
for $b in  doc("bd-agregador.xml")/listaNot/noticia 
where $b/@idNoticia =$id_Noticia
return <body>
        <div class="noticia">
        <div class="cabecera"> <a href="{$b/URL}">{$b/titular}</a></div>
        
        <div class="fecha"> {$b/fechaHora}</div>
            
        <div class="etiquetas"> {
            string-join ( for $p in $b/listaEtiq/etiqueta  
                return $p  , ",") 
             }
         </div>
        <div class="entradilla">{$b/entradilla}</div>
        </div>
         
         {for $p in $b/listaCom/comentario order by  $p//fechaHora/anio,$p//fechaHora/mes,$p //fechaHora/dia,$p//fechaHora/hora,$p//fechaHora/min,$p//fechaHora/seg  return   
            <div class="comentario">
                <div class="autor"> {$p//usr} </div>
                <div class="fecha_com"> {$p//fechaHora} </div>
                <div class="texto"> {$p//txtCom} </div>
             </div>
             
         } 
       
    </body>