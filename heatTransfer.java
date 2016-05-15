import java.util.Scanner;


public class heatTransfer {
	
	
	public double TN,
			   TE,
			   TS,
			   TW;
	public int nodes;
	
	public double[][] heat;
	
	public heatTransfer(int TN, int TE, int TS, int TW, int nodes) {
		this.TN = TN;
		this.TE = TE;
		this.TS = TS;
		this.TW = TW;
		this.nodes = nodes;
		
	}
	
	public heatTransfer() {
		this.setPlate();
		this.boundersPlate();
	}
	
	public void setPlate() {
		this.heat = new double[(int) ((Math.sqrt(nodes))+2)][(int) ((Math.sqrt(nodes))+2)];
	}
	
	public double[][] getHeat() {
		return this.heat;
	}
	
	public boolean validatePlate(int target){
		
		// handle 0 and 1
	    if (target <= 1)
	        return true;

	    long currentSquare = 4;
	    long currentNumber = 2;

	    // loop through till the target is more than
	    // the square of the current number
	    while (currentSquare <= target)
	    {
	        // if we have a match, return true
	        if (currentSquare == target)
	            return true;
	        // increment current number
	        currentNumber++;
	        // find the next square
	        currentSquare = currentNumber * currentNumber;
	    }

	    // no matching number could be squared
	    return false;
	}
	
	public void setTN(double TN) {
		this.TN = TN;
	}
	
	public void setTE(double TE) {
		this.TE = TE;	
	}
	
	public void setTS(double TS) {
		this.TS = TS;
	}
	
	public void setTW(double TW) {
		this.TW = TW;
	}
	
	public void setNodes(int nodes) {
		if(this.validatePlate(nodes)){
			this.nodes = nodes;
		}
	}
	
	public double formula(double tn, double te, double tw, double ts) {
		double formula = 0;
		formula = (tn + te + tw + ts) / 4;
		return formula;
	}

	public void boundersPlate() {
		for(int i = 0; i < heat.length; i++) {
			if( i == 0 ) {
				for(int j = 0; j < heat[i].length; j++) {
					heat[i][j] = TN;
				}
			} 
			else if (i == heat.length - 1) {
				for(int j = 0; j < heat[i].length; j++) {
					heat[i][j] = TS;
				}
			}
			else {
				heat[i][0] = TW;
				heat[i][heat.length - 1] = TE;
			}
		}
	}
	
	public void calculateHeat() {
		for(int i = 1; i < heat.length-1; i++) {
			//if( i != 0 || i != heat.length - 1){
				for(int j = 1; j < heat[i].length-1; j++) {
					//if(j != 0 || j != heat.length - 1) {
						//System.out.println("I = " + i + " J = " + j);
						//System.out.println(this.formula(heat[i][j-1], heat[i+1][j], heat[i][j+1], heat[i-1][j]));
						
						if(heat[i+1][j] != 0 || heat[i][j+1] != 0 || heat[i-1][j] != 0 || heat[i][j-1] != 0 ) {
							//for(int k = 0; k < 100; k++) {
								heat[i][j] = this.formula(heat[i][j-1], heat[i+1][j], heat[i][j+1], heat[i-1][j]);
							//}
							
						} 
				}
			//}
			
		}
	}
	
	public String toString() {
		String plate = "";
		for(int i = 0; i < heat.length; i++) {
			for(int j = 0; j < heat[i].length; j++) {
				plate += " " + heat[i][j] + " ";
			}
			plate += "\n";
		}
		
		return plate;
	}

	
	
	
	public static void main(String[] args) {
		

		Scanner scanner = new Scanner(System.in);
		
		
		int nodes = 0;
		boolean ans = true;
		while(ans){
			System.out.println("Nodos: ");
			int xnodes = scanner.nextInt();
			if( xnodes % 2 == 0) {
				nodes =  xnodes;
				ans = false;
				break;
			} else {
				System.out.println("Debe ser numero par");
				ans = true;
			}
		}
		
		
		System.out.println("TN: ");
		int TN = scanner.nextInt();
		
		System.out.println("TE: ");
		int TE = scanner.nextInt();

		System.out.println("TS: ");
		int TS = scanner.nextInt();
		
		System.out.println("TW: ");
		int TW = scanner.nextInt();
		
		heatTransfer plate = new heatTransfer(TN, TE, TS, TW, nodes);
		
		
		plate.setPlate();
		plate.boundersPlate();
		System.out.println(plate);
		
		for(int i = 0; i < 100; i++) {
			plate.calculateHeat();
		}
		
		System.out.println(plate);
		
		
		//scanner.close();
	}

}
