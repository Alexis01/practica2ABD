package abd.pr2.controller;



import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.xquery.XQException;

import abd.pr2.model.ModelManagger;
import abd.pr2.model.Noticia;

public class Controller {
	private ModelManagger manager;
	public Controller(){
		this.manager=new ModelManagger();
	}
	public List<String> getEtiquetasFromModel() throws FileNotFoundException, XQException{
		return this.manager.getEtiquetas();
	}
	public List<Noticia> getNewsFromModel(String key) throws FileNotFoundException, XQException{
		return this.manager.getNewsFromCateg(key);
	}
	public List<String> getBodyNew(String key) throws FileNotFoundException, XQException{
		return this.manager.getBodyNewFromKey(key);
	}
}
