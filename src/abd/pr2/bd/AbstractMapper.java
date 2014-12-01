package abd.pr2.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import abd.pr2.model.Noticia;
import net.xqj.exist.ExistXQDataSource;

public abstract class AbstractMapper<T> {
	
	protected String nombreArchivo;
	protected XQDataSource xqds=new ExistXQDataSource();
	
		
	/***
	 * Método génerico que sirve para ejecutar las consultas
	 * @return
	 * @throws FileNotFoundException
	 * @throws XQException
	 */
	public List<T> ejecutar() throws FileNotFoundException, XQException {
		List<T> objList = new ArrayList<T>();
		
		InputStream is = new FileInputStream(nombreArchivo);
		
		xqds.setProperty("serverName", "localhost");
		xqds.setProperty("port", "8080");
		XQConnection con = xqds.getConnection("admin","123");
		XQPreparedExpression exp = con.prepareExpression(is);
		
		fillExVar(exp);
		
		XQResultSequence rs = exp.executeQuery();
		
		objList= getAResult(rs);
		
		rs.close();
		exp.close();
		con.close();
		
		return objList;
	}
	/***
	 * Método génerico que nos devuelve una lista de objetos.
	 * @param rs
	 * @return
	 * @throws XQException
	 */
	protected List<T> getAResult(XQResultSequence rs)throws XQException {
		T result= null;
		List<T> objList = new ArrayList<T>();
		while(rs.next()){
			result = buidObject(rs);					
			objList.add(result);
		}
		return objList;
	}
	/***
	 * Rellena la variable exterior que necesita la consulta xquery para llevarse a cabo.
	 * No todos los mapper de las consultas la implementan porque no todos tienen variables exteriores.
	 * @param exp 
	 * @throws XQException
	 */
	protected abstract void fillExVar(XQPreparedExpression exp)throws XQException;
	/***
	 * Implementada en todos las consultas, crea objetos a partir de las consultas, a partir de estos interpretamos datos en al aplicación Java
	 * @param rs
	 * @return
	 * @throws XQException
	 */
	protected abstract T buidObject(XQResultSequence rs)throws XQException;

}
