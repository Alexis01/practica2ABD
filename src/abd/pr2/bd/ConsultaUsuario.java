package abd.pr2.bd;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;
import abd.pr2.model.Usuario;

public class ConsultaUsuario extends AbstractMapper<Usuario>{

	private static final String ARCHIVO_CONSULTA_XQUERY = "consulta2.xquery";
	
	public ConsultaUsuario(XQDataSource xqds){
		this.xqds =xqds;
		this.nombreArchivo=ARCHIVO_CONSULTA_XQUERY;
	}
	
	@Override
	protected void fillExVar(XQPreparedExpression exp) throws XQException {
		//No la implementamos porque no tenemos var externa.
	}
	@Override
	protected Usuario buidObject(XQResultSequence rs) throws XQException {
		Usuario result = new Usuario();
		org.w3c.dom.Element n;
		n = (org.w3c.dom.Element)rs.getObject();
		
		result.setNombresUsr(n.getAttribute("nombre"));
		result.setnComent(n.getAttribute("numero_comentarios"));
		
		return result;
	}
	public static void main(String args[]) throws XQException, FileNotFoundException {
		XQDataSource xqds = new ExistXQDataSource();
		
		List<Usuario> ess= new ArrayList<Usuario>();
		ConsultaUsuario usrs = new ConsultaUsuario(xqds);
		ess = usrs.ejecutar();
		Usuario usr= new Usuario();
		int i =0;
		
		while(!ess.isEmpty() && i <ess.size()){
			usr = ess.get(i);
			System.out.println(usr.getNombresUsr());
			System.out.println(usr.getnComent());
			i++;
		}
		
		
	}
}
