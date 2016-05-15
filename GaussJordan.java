import java.util.Scanner;

public class GaussJordan {
	
	
	public float[][] matrix;
	
	public float[][] borders;
	
	public float[][] heatTransfer;
	
	public int nodes;
	
	public float TN,
				  TE,
				  TS,
				  TW;
	
	public GaussJordan(float TN, float TE, float TS, float TW, int nodes) {
		this.borders = new float[(int) ((Math.sqrt(nodes))+2)][(int) ((Math.sqrt(nodes))+2)];
		this.matrix = new float[nodes][nodes+5];
		this.TN = TN;
		this.TE = TE;
		this.TS = TS;
		this.TW = TW;
		this.nodes = nodes;
		this.setBorders();
		this.fillMatrixGaussJordan();
	}	
	
	//----------------------------------GAUSS-----------------------
	public float[][] methodGaussJordan(float[][] mat){

		int piv = 0;
		int var = mat.length;
		
		for (int i=0;i<var; i++) {
			mat = pivote(mat, piv, var);
			mat = ceros(mat, piv, var);
			piv++;
		}
		return mat;
	}

	public float[][] pivote(float mat[][], int piv, int var) {
		float temp = mat[piv][piv];
		for (int y=0;y<(var+1);y++) {
			mat[piv][y] = mat[piv][y] / temp;
		}
		return mat;
	}

	public float[][] ceros(float mat[][], int piv, int var){
		for (int x=0;x<var;x++){
			if (x != piv){
				float c = mat[x][piv];
				for (int z=0;z<(var+1);z++){
					mat[x][z] = ((-c)*mat[piv][z])+mat[x][z];
				}
			}
		}
		return mat;
	}
	
	public void performGaussJordan(float[][] matriz) {
		int piv = 0;
		int var = this.nodes;
		for (int a = 0; a < this.nodes; a++) {
            pivote(matriz, piv, var);

            System.out.println("\tRenglon " + (a + 1) + " entre el pivote");
            this.methodGaussJordan(matriz);

            System.out.println("");

            System.out.println("\tHaciendo ceros");
            this.ceros(matriz, piv, var);

            this.heatTransfer = this.methodGaussJordan(matriz);
            System.out.println("");
            piv++;
        }
        for (int x = 0; x < var; x++) {
            System.out.println("La variable X" + (x + 1) + " es: " + matriz[x][var]);
        }
	}
	
	//---------------------------------------------------------------
	
	public void setBorders() {
		for(int i = 0; i < borders.length; i++) {
			if( i == 0 ) {
				for(int j = 0; j < borders[i].length; j++) {
					borders[i][j] = this.TN;
				}
			} 
			else if (i == borders.length - 1) {
				for(int j = 0; j < borders[i].length; j++) {
					borders[i][j] = this.TS;
				}
			}
			else {
				borders[i][0] = this.TW;
				borders[i][borders.length - 1] = this.TE;
			}
		}
	}
	
	public void fillMatrixGaussJordan() {
		System.out.println(this.nodes + " nodes");
		int size = (this.borders.length-2);
		int nodeNo;
		
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
			    nodeNo = (i * size) + j;
				if (i == 0){
					this.matrix[nodeNo][0] = this.borders[0][1];
				}
				else
				{
					this.matrix[nodeNo][(int) nodeNo - size + 4] = (float) -1.0;
				}
				if (i == size - 1)
				{
					this.matrix[nodeNo][1] = this.borders[this.borders.length - 1][1];
				}
				else
				{
					this.matrix[nodeNo][(nodeNo + size + 4)] = (float) -1.0;
				}
				if (j == 0)
				{
					this.matrix[nodeNo][2] = this.borders[1][0];
				}
				else
				{
					this.matrix[nodeNo][nodeNo - 1 + 4] = (float) -1.0;
				}
				if (j == size - 1)
				{
					this.matrix[nodeNo][3] = this.borders[1][this.borders.length - 1];
				}
				else
				{
					this.matrix[nodeNo][nodeNo + 1 + 4] = (float) -1.0;
				}
				
				this.matrix[nodeNo][nodeNo + 4] = 4;
			}
		}
		for(int i = 0; i < this.matrix.length; i++) {
			int k = 0;
			for(int j = 0; j < 4; j++) {
				k += this.matrix[i][j];
			} this.matrix[i][this.matrix[i].length-1] = k;
		} 
		
		
	}
	
	public float[][] setGaussMatrx() {
		float[][] matriz2 = new float[this.nodes][this.nodes+1];
		
		for(int i = 0; i < this.matrix.length; i++){
			for(int j = 0; j < this.matrix[i].length; j++){
				if(j<4){
					continue;
				}
				else{
					matriz2[i][j-4] = this.matrix[i][j];
				}
			}
			
		}
		String plate = "";
		for(int i = 0; i < matriz2.length; i++) {
			for(int j = 0; j < matriz2[i].length; j++) {
				plate += " " + matriz2[i][j] + " ";
			}
			plate += "\n";
		}System.out.println(plate);
		return matriz2;
	}
	
	public String toStringMatrix() {
		String plate = "";
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				plate += " " + matrix[i][j] + " ";
			}
			plate += "\n";
		}
		return plate;
	}
	
	public void setResults() {
		
		float[] temp = new float[this.nodes];
		for (int x = 0; x < temp.length; x++) {
            temp[x] = this.heatTransfer[x][this.nodes];
        }
		int k = 0;
		for(int i = 1; i < this.borders.length-1; i++) {
			for(int j = 1; j < this.borders[i].length-1; j++) {
					if(this.borders[i+1][j] != 0 || this.borders[i][j+1] != 0 || this.borders[i-1][j] != 0 || this.borders[i][j-1] != 0 ) {
							this.borders[i][j] = temp[k];
					}
					k ++;
			} 
		}
	}
	
	public String toString(){
		String plate = "";
		for(int i = 0; i < this.heatTransfer.length; i++) {
			for(int j = 0; j < this.heatTransfer[i].length; j++) {
				plate += " " + this.heatTransfer[i][j] + " ";
			}
			plate += "\n";
		}
		return plate;
	}
	
	public double[][] getHeat() {
		double[][] temp = new double[this.borders.length][this.borders.length];
		for(int i = 0; i < this.borders.length; i++) {
			for(int j = 0; j < this.borders[i].length; j++) {
				temp[i][j] = this.borders[i][j];
			}
		}
		return temp;
	}
	
	
	public String toStringBorders() { //----------------------- BORDE DE LA PLACA----------------------------
		String plate = "";
		for(int i = 0; i < borders.length; i++) {
			for(int j = 0; j < borders[i].length; j++) {
				plate += " " + borders[i][j] + " ";
			}
			plate += "\n";
		}
		
		return plate;
	}//-------------------------------------------------------------------------------------------------------

	//---------------------------GAUSS-JORDAN----------------------------------------------------------------

    public static void main(String args[]) {
    	//PRUEBA
    	Scanner scan = new Scanner(System.in);
    	int nodes;
    	float TN, TE, TS, TW;
    	
    	System.out.println("Nodos: ");
    	nodes = scan.nextInt();
    	System.out.println("TN: ");
    	TN = scan.nextFloat();
    	System.out.println("TE: ");
    	TE = scan.nextFloat();
    	System.out.println("TS: ");
    	TS = scan.nextFloat();
    	System.out.println("TW: ");
    	TW = scan.nextFloat();
    	
    	
        GaussJordan gauss = new GaussJordan(TN, TE, TS, TW, nodes);
        System.out.println(gauss.toStringBorders());
        
        
        gauss.performGaussJordan(gauss.setGaussMatrx());
        gauss.setResults();
        System.out.println(gauss.toString());
        System.out.println(gauss.toStringBorders());
        
       
    
    }
}