package abd.pr2.bd;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;
import abd.pr2.model.Noticia;

public class ConsultaNoticiasPorCategoria extends AbstractMapper<Noticia> {
	
	private static final String ARCHIVO_CONSULTA_XQUERY = "consulta1.xquery";
	private String categoria;
	
	public ConsultaNoticiasPorCategoria(XQDataSource xqds, String categoria) {
		this.xqds = xqds;
		this.categoria = categoria;
		this.nombreArchivo = ARCHIVO_CONSULTA_XQUERY;
	}
	public ConsultaNoticiasPorCategoria( String categoria) {
		this.categoria = categoria;
		this.nombreArchivo = ARCHIVO_CONSULTA_XQUERY;
	}
	@Override
	protected void fillExVar(XQPreparedExpression exp) throws XQException {
		exp.bindString(new QName("etiqueta"), this.categoria, null);
	
	}
	@Override
	protected Noticia buidObject(XQResultSequence rs)throws XQException {
		Noticia result = new Noticia();
		org.w3c.dom.Element n;
		n = (org.w3c.dom.Element)rs.getObject();

		result.setId(n.getAttribute("id"));
		result.setTitular(n.getAttribute("titular"));
			
		return result;
	}
	public static void main(String args[]) throws XQException, FileNotFoundException {
		XQDataSource xqds = new ExistXQDataSource();
			
		String categ= "Internacional";
		int i = 0;
	
			ConsultaNoticiasPorCategoria not = new ConsultaNoticiasPorCategoria(xqds,categ);
				
			List<Noticia> notas;
			notas = not.ejecutar();
			Noticia noticia = new Noticia();
			while(!notas.isEmpty() && i <notas.size()){
				noticia = notas.get(i);
				System.out.println(noticia.getId());
				System.out.println(noticia.getTitular());
				i++;
			}
    }
	

}
