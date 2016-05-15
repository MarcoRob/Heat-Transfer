import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class HeatWindow extends JFrame {
	
	private heatTransfer plate;
	private HeatPanel heatPanel;
	private OptionsPanel options;

	public HeatWindow () {
		super("Transferencia de calor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.plate= new heatTransfer();
		this.heatPanel = new HeatPanel();
		this.options = new OptionsPanel(this.heatPanel, this.plate);
		this.add(this.heatPanel);
		this.add(this.options, BorderLayout.WEST);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		HeatWindow frame = new HeatWindow();
	}




}
