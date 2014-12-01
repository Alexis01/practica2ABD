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

public class ConsultaPublicacion extends AbstractMapper<String> {
	
	private static final String ARCHIVO_CONSULTA_XQUERY = "consulta4.xquery";
	private String idNot;
	
	
	public ConsultaPublicacion(XQDataSource xqds, String idNot){
		this.xqds = xqds;
		this.idNot= idNot;
		this.nombreArchivo= ARCHIVO_CONSULTA_XQUERY;
		
	}
	@Override
	protected void fillExVar(XQPreparedExpression exp) throws XQException {
		exp.bindString(new QName("id_Noticia"), this.idNot, null);	
	}
	@Override
	protected String buidObject(XQResultSequence rs) throws XQException {
		
		String result= null;
		result = rs.getItemAsString(null);	
		return result;
	}

	public static void main(String[] args) throws FileNotFoundException, XQException {
		XQDataSource xqds = new ExistXQDataSource();
		String idNot="a1";
		List<String> ess = new  ArrayList<String>();
		ConsultaPublicacion publi = new ConsultaPublicacion(xqds,idNot);
		ess = publi.ejecutar();
		System.out.println(ess);	
	}

}
