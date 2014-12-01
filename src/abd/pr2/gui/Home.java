package abd.pr2.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.xquery.XQException;

import abd.pr2.controller.Controller;
import abd.pr2.model.Noticia;

public class Home extends JFrame{
	private static final long serialVersionUID = 1L;
	Controller controller=new Controller();
	JPanel panel = new JPanel();
	//Lista
    DefaultListModel<String> listModel=new DefaultListModel<String>();
	@SuppressWarnings("rawtypes")
	JList lista= new JList();
	/*Combobox*/
	String[] defaultStrings = { "---"};
	JComboBox<String> combo = new JComboBox<String>(defaultStrings);
	/*Label y demas*/
	private VisorNoticias newsView;
	JLabel etiqueta = new JLabel("Categoría:");
	List<Noticia> newsGlobal=new LinkedList<Noticia>();
	
	public Home() throws FileNotFoundException, XQException{
		super("News");
		this.newsView=new VisorNoticias();
		setSize(600,600);
		setLocation(200,50);
		panel.setLayout (null);
		this.etiqueta.setBounds(5, 5, 100, 30);
		this.combo.setBounds(105, 5, 475, 30);
		this.combo.setBackground(Color.cyan);
		this.lista.setBounds(5, 35, 575, 250);
		lista.setBackground(Color.cyan);
		this.newsView=new VisorNoticias();
		this.newsView.setBounds(5, 290, 575, 250);
		this.newsView.setBackground(Color.lightGray);
		panel.add(newsView);
		panel.add(lista);
		panel.add(etiqueta);
		panel.add(combo);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setData();
		listeners();
	}
	public void setData() throws FileNotFoundException, XQException{
		List<String> result=this.controller.getEtiquetasFromModel();
		for (int i = 0; i < result.size(); i++) {
			combo.addItem(result.get(i).toString());
		}
	}
	public void listeners(){
		combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String key=(String) combo.getSelectedItem();
				if(!key.equals("---")){
					List<Noticia> news = null;
					try {
						news=controller.getNewsFromModel(key);
					} catch (FileNotFoundException | XQException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					listModel.clear();
					Noticia not=new Noticia();
					for(int i=0;i<news.size();i++){
						listModel.add(i, news.get(i).getTitular());
						not=news.get(i);
						newsGlobal.add(not);
					}
					lista.setModel(listModel);
				}
//				else{
//					JOptionPane.showMessageDialog(null,"Modificación datos personales");
//				}
				
				
			}
		});
		
		lista.addListSelectionListener(new ListSelectionListener() {
			  public void valueChanged(ListSelectionEvent evt) {
				    if (!evt.getValueIsAdjusting()) {
				    	String x=(String) lista.getSelectedValue();
						if(x!=null){
							Noticia not=new Noticia();
							for (int i = 0; i < newsGlobal.size(); i++) {
								if(x.equals(newsGlobal.get(i).getTitular())){
									not=(Noticia)newsGlobal.get(i);
								}
							}
							List<String> text = null;
							try {
								text = controller.getBodyNew(not.getId());
							} catch (FileNotFoundException | XQException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							for (int i = 0; i < text.size(); i++) {
								newsView.setText(text.get(i).toString());
							}
							
						}
//						else{
//							JOptionPane.showMessageDialog(null,"Selecciona una noticia para ver su contenido");
//						}
				    }
			  }
		});
	}
	public static void main(String[] args) throws FileNotFoundException, XQException{
		Home home=new Home();
	}
	
}
