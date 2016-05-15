import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


public class OptionsPanel extends JPanel implements ActionListener {

	public JButton ok,
					reset;
	
	public File file;
	
	public int imgName;
	
	private JTextField TN,
					   TE,
					   TS,
					   TW,
					   node;
	
	private HeatPanel heatPanel;
	
	private heatTransfer plate;
	
	public OptionsPanel(HeatPanel heatPanel, heatTransfer plate) {
		super();
		this.imgName = 0;
		this.heatPanel = heatPanel;
		this.plate = plate;
		this.setPreferredSize(new Dimension(100, 400));
		
		JLabel title = new JLabel("Borde");
		this.add(title);
		title.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JLabel title1 = new JLabel("de la ");
		this.add(title1);
		title1.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JLabel title2 = new JLabel("Placa");
		this.add(title2);
		title2.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JLabel tn = new JLabel("TN: ");
		this.add(tn);
		Font fuente = new Font("Calibri", Font.BOLD, 14);
		tn.setFont(fuente);
		this.TN= new JTextField(5);
		this.add(this.TN, BorderLayout.WEST);
		
		JLabel te = new JLabel("TE: ");
		this.add(te);
		te.setFont(fuente);
		this.TE= new JTextField(5);
		this.add(this.TE);
		
		JLabel ts = new JLabel("TS: ");
		this.add(ts);
		ts.setFont(fuente);
		this.TS= new JTextField(5);
		this.add(this.TS);
		
		JLabel tw = new JLabel("TW");
		this.add(tw);
		tw.setFont(fuente);
		this.TW= new JTextField(5);
		this.add(this.TW);
		
		JLabel nodes = new JLabel("Nodos: ");
		this.add(nodes);
		nodes.setFont(fuente);
		this.node= new JTextField(5);
		this.add(this.node);
		
		this.ok = new JButton("Calculate");
		this.ok.addActionListener(this);
		this.add(this.ok);
		
		this.reset = new JButton("Reset");
		this.reset.addActionListener(this);
		//this.add(this.reset);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
	
		if(evt.getSource() == this.ok) { 
			if(this.plate.validatePlate(Integer.parseInt(this.node.getText()))){
				heatPanel.setTN(Double.parseDouble(this.TN.getText()));
				heatPanel.setTE(Double.parseDouble(this.TE.getText()));
				heatPanel.setTS(Double.parseDouble(this.TS.getText()));
				heatPanel.setTW(Double.parseDouble(this.TW.getText()));
				heatPanel.setNodes(Integer.parseInt(this.node.getText()));
				
				this.plate.setTN(Double.parseDouble(this.TN.getText()));
				this.plate.setTE(Double.parseDouble(this.TE.getText()));
				this.plate.setTS(Double.parseDouble(this.TS.getText()));
				this.plate.setTW(Double.parseDouble(this.TW.getText()));
				this.plate.setNodes(Integer.parseInt(this.node.getText()));
				
				plate.setPlate();
				plate.boundersPlate();
				System.out.println(plate);
				
				for(int i = 0; i < 100; i++) {
					plate.calculateHeat();
				}
				
//				GaussJordan gauss = new GaussJordan(Float.parseFloat(this.TN.getText()), Float.parseFloat(this.TE.getText()), Float.parseFloat(this.TS.getText()), Float.parseFloat(this.TW.getText()), Integer.parseInt(this.node.getText()));
//		        System.out.println(gauss.toStringBorders());
//		        gauss.performGaussJordan(gauss.setGaussMatrx());
//		        gauss.setResults();
//		        System.out.println(gauss.toString());
//		        System.out.println(gauss.toStringBorders());
		        
		        
				HeatChart map = new HeatChart(gauss.getHeat()); //--
				map.setTitle("HEAT MAP");
				map.setLowValueColour(new Color(243,251,128));
				map.setHighValueColour(new Color(248,91,6));
				
				this.imgName ++;
				int x = this.imgName;
				System.out.println("imgName --> " + this.imgName);
				System.out.println(x +" X");
				String image = x + ".png";
				this.file = new File(image);
				try {
					map.saveToFile(file);
					System.out.println("TRY");
				} catch (IOException e) {
					e.printStackTrace();
				}
				ImageIcon temp = new ImageIcon(image);
				heatPanel.setImgName(image);
				
				System.out.println(plate);
				heatPanel.heatMap(temp.getImage());
				heatPanel.repaint();
				
		} else {
				JOptionPane.showMessageDialog(null, "Los NODOS tienen que estar al CUADRADO PERFECTO");
			}
		}
		
		if (evt.getSource() == this.reset) {
			System.out.println(this.imgName + " <-- IMG");
			String name = this.imgName + ".png";
			try{
	    		
	    		File file = new File(name);
	        	
	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	    	   
	    	}catch(Exception e){
	    		
	    		e.printStackTrace();
	    		
	    	}
		} 
	}
}
