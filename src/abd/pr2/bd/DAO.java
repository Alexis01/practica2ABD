package abd.pr2.bd;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;

import abd.pr2.model.*;
import net.xqj.exist.ExistXQDataSource;

public class DAO {
	
	private XQDataSource xqds;

	public DAO(XQDataSource xqds) {
		super();
		this.xqds = xqds;
	}
	
	public List<Noticia> consultarNoticiasPorEtiqueta(String etiqueta) throws FileNotFoundException, XQException {
	
		ConsultaNoticiasPorCategoria not = new ConsultaNoticiasPorCategoria(this.xqds,etiqueta);
		List<Noticia> noticias = null;
		noticias = not.ejecutar();
		
		return noticias;
	}
	public List<Usuario> consultarUsuariosMasActivos() throws FileNotFoundException, XQException{
		List<Usuario> ess= new ArrayList<Usuario>();
		ConsultaUsuario usr = new ConsultaUsuario(this.xqds);
		ess = usr.ejecutar();
		
		return ess;
	}
	public String consultarInformacionNoticia(String id) throws FileNotFoundException, XQException{
		
		List<String> ess = new  ArrayList<String>();
		ConsultaPublicacion publi = new ConsultaPublicacion(this.xqds,id);
		ess = publi.ejecutar();
		String noticia = ess.toString();
		
		return noticia;
	}
	public List<String> consultarEtiquetas() throws FileNotFoundException, XQException{
	
		List<String> etiquetas =new ArrayList<String>();
		ConsultaEtiquetas eti = new ConsultaEtiquetas();
		etiquetas=eti.ejecutar();
		
		return etiquetas;
	}
	
	
	public static void main(String args[]) throws XQException, FileNotFoundException {
		
		XQDataSource xqds = new ExistXQDataSource();
		
		DAO dao =new DAO(xqds);
		List<String> res ;
		List<Noticia> res2 ;
		List<Usuario> res3 ;
		String res4 =null;
		
		//consulta noticia Etiquetas
		String etiqueta="Internacional";
		res2 = dao.consultarNoticiasPorEtiqueta(etiqueta);
		
		//consulta Usuarios+Activos
		Usuario usr = null; 
		res3 = dao.consultarUsuariosMasActivos();
		
		//Consulta InfoNoticia
		String id = "a2";
		res4 = dao.consultarInformacionNoticia(id);
		
		//Consuta Etiquetas
		res = dao.consultarEtiquetas();
		
		
		//MUESTRA POR PANTALLA
		int i=0,j=0,k=0;
		
		Noticia noticia=null;
		System.out.println("Consulta noticias por etiquetas");
		while(!res2.isEmpty() && i <res2.size()){
			noticia = res2.get(i);
			System.out.println(noticia.getId());
			System.out.println(noticia.getTitular());
			i++;
		}
		System.out.println("Consulta Usuarios + activos");
		  while(!res3.isEmpty() && k <res3.size()){
			usr = res3.get(k);
			System.out.println(usr.getNombresUsr());
			System.out.println(usr.getnComent());
			k++;
		}
		System.out.println("Consulta Info Noticia");
		System.out.println(res4);
		System.out.println("Consulta Etiquetas");
		String etis = null;
		while(!res.isEmpty() && j <res.size()){
			etis = res.get(j);
			System.out.println(etis);
			j++;
		}
	}
	
}
