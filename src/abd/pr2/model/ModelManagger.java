package abd.pr2.model;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;

import net.xqj.exist.ExistXQDataSource;
import abd.pr2.bd.ConsultaEtiquetas;
import abd.pr2.bd.ConsultaNoticiasPorCategoria;
import abd.pr2.bd.ConsultaPublicacion;

public class ModelManagger {
	private ConsultaEtiquetas managerEtiquetas;
	private ConsultaNoticiasPorCategoria managerNewsFromEtiquetas; 
	private ConsultaPublicacion managerBodyFromNew;
	XQDataSource xqds;
	public ModelManagger(){
		this.managerEtiquetas=new ConsultaEtiquetas();
		this.xqds=new ExistXQDataSource();
	}
	public List<String> getEtiquetas() throws FileNotFoundException, XQException{
		return this.managerEtiquetas.ejecutar();
	}
	public List<Noticia> getNewsFromCateg(String key) throws FileNotFoundException, XQException{
		this.managerNewsFromEtiquetas=new ConsultaNoticiasPorCategoria(this.xqds, key);
		return this.managerNewsFromEtiquetas.ejecutar();
	}
	public List<String> getBodyNewFromKey(String key) throws FileNotFoundException, XQException{
		this.managerBodyFromNew=new ConsultaPublicacion(this.xqds, key);
		return this.managerBodyFromNew.ejecutar();
	}
}

 