package LinearAlgebra;

class Matrix {
	//Atribut Matrix
	double [][] Mat = new double[100000000][100000000];
	
	//Konstruktor
	Matrix() {
		int i,j;
		
		for(i = 0; i<100000000; i++) {
			for(j = 0; j<100000000; j++) {
				this.Mat[i][j] = 0;
			}
		}
	}
	
	
}