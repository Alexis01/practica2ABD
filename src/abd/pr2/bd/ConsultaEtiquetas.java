package abd.pr2.bd;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

public class ConsultaEtiquetas extends AbstractMapper<String> {

	private static final String ARCHIVO_CONSULTA_XQUERY = "consulta3.xquery";

	public ConsultaEtiquetas(){
		this.nombreArchivo= ARCHIVO_CONSULTA_XQUERY;
	}
	@Override
	protected void fillExVar(XQPreparedExpression exp)throws XQException{
		//No la implementamos porque no tenemos var externa.
	}
	@Override
	protected String buidObject(XQResultSequence rs) throws XQException {
		String result = null;
		result = rs.getItemAsString(null);
		return result;
	}
//*Creaamos una String para cada nuevo resultado de la BBDD, porque para cada consulta susutuye el id del
//result y se cambian todos al mismo en cada vuelta. por eso en la prac1ABD existe el buildObject().
	public static void main(String[] args) throws FileNotFoundException, XQException {
		
		List<String> ess =new ArrayList<String>();
		ConsultaEtiquetas eti = new ConsultaEtiquetas();
		ess=eti.ejecutar();
		String sa= new String();
		int i = 0;
		while(!ess.isEmpty() && i <ess.size()){
			sa = ess.get(i);
			System.out.println(sa);
			i++;
		}

	}
	
	
	
}
