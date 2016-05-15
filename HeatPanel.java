import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HeatPanel extends JPanel implements ActionListener {

	private double TN,
				TE,
				TS,
				TW;
	private int nodes;
	
	private String img;
	
	private JButton imageMap;

	private Image heatMap;
	
	private JPanel gridHeat;
	
	private GridLayout grid;
	
	private Graphics g1;
	
	public HeatPanel() {
		super();
		this.setPreferredSize(new Dimension(710, 400));
		
		this.gridHeat = new JPanel();
		this.setLayout(null);
		
		
		
		JLabel title = new JLabel("Transferencia de Calor ");
		this.add(title);
		title.setFont(new Font("Calibri", Font.BOLD, 25));
		
		//heat = new JPanel[(nodes/2)+1][(nodes/2)+1];
		
		this.imageMap = new JButton();
		this.imageMap.addActionListener(this);
		//this.add(this.imageMap);
		
		this.imageMap.setBounds(391, 60, 300, 300);
		
		
	}

	public void setImgName(String img) {
		this.img = img;
		System.out.println(img + " Name");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont( new Font( "Calibri", Font.BOLD, 12 ) );
		g.drawString("Por Manlio Rivas & Marco Robles", 520, 390);
		//CONDICIONES DEL BORDE
		g.setFont( new Font( "Calibri", Font.BOLD, 16 ) );
		g.drawString("Condiciones del Borde", 120, 25);
		this.writeBorders(g);
		//AREA
		g.drawRect(50, 60, 300, 300);
		
		//DISTRIBUCION DE CALOR
		g.setFont( new Font( "Calibri", Font.BOLD, 16 ));
		g.drawString("Distribución del Calor", 460, 25);
		//AREA
		g.drawRect(390, 59, 301, 301);
		//GRID CONDICIONES DEL BORDE
		this.calculateGrid(g);
		//this.paintMap(g);
		
		this.drawImage(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.imageMap) {
			
			this.imageMap.setIcon(new ImageIcon(this.img));
		}
	}

	public void drawImage(Graphics g) {
		g.drawImage(this.heatMap, 391, 60, 300,300, this);
	}
	
	public void heatMap(Image heat) {
		this.heatMap = heat;
		System.out.println("IMAGEN");
	}
	
	public void paintMap(Graphics g) {
		int nodes = this.nodes;
		int height = (int) Math.sqrt(nodes) + 1;
		int width = (int) Math.sqrt(nodes) + 1;
		int spaceH = 300 / height ;
		int spaceW = 300 / width ;
		for(int i = 0; i < width; i++ ){
			for(int j=0; j < height; j++) {
				g.drawRect(390 + i * spaceW, 59 + j * spaceH, spaceW, spaceH);	
			}
		}
	}
	public void calculateGrid(Graphics g) {
		
		int nodes = this.nodes;
		int height = (int) Math.sqrt(nodes) + 1;
		int width = (int) Math.sqrt(nodes) + 1;
		int spaceH = 300 / height ;
		int spaceW = 300 / width ;
		
		
		for(int i = 0; i < width; i++ ){
			for(int j=0; j < height; j++) {
				g.drawRect(50 + i * spaceW, 60 + j * spaceH, spaceW, spaceH);	
			}
		}
		
		for(int i = 0; i < width-1; i++) {
			for(int j = 0; j < height-1; j++) {
				g.drawString("O", 45 + spaceW + i * spaceW, 65 + spaceH + j * spaceH);
			}
		}
		
	}
	
	public void setNodes(int nodes){
		this.nodes = nodes;
	}
	
	public void setTN(Double TN) {
		this.TN = TN;
	}
	
	public void setTE(Double TE) {
		this.TE = TE;
	}
	
	public void setTS(Double TS) {
		this.TS = TS;
	}
	
	public void setTW(Double TW) {
		this.TW = TW;
	}
	

	public void setPlate() {
		this.grid = new GridLayout((nodes/2)+1, (nodes/2)+1);
		this.gridHeat.setLayout(this.grid);
		
		
		this.gridHeat.setBounds(380, 60,300,300);
		this.gridHeat.setBackground(Color.white);
		this.add(this.gridHeat);
		
	}
	
	public void writeBorders(Graphics g) {
		
		g.drawString("TN", 165, 55);
		g.drawString("T", 355, 195);
		g.drawString("E", 355, 210);
		g.drawString("TS", 165, 375);
		g.drawString("T", 20, 195);
		g.drawString("W", 20, 210);
		
		if(TW != 0.0 || TN != 0.0 || TE != 0.0 || TS != 0.0 ){
			g.drawString(TE+"°", 355, 230);
			g.drawString(""+TS+"°", 200, 375);
			g.drawString(""+TN+"°", 200, 55);
			g.drawString(TW+"°", 15, 230);
		}
		System.out.println(TW);
		
	}
	
	
	
	

	
}
