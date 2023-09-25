package LinearAlgebra;
import LinearAlgebra.*;
public class EquationSystem {
	public static void CramerRule(Matrix m) {
		double D;
		D = Determinant.EkspansiKofaktor(m);
		
		if(D == 0) {
			System.out.println("Sistem Persamaan Linear berikut tidak memiliki solusi.");
			return;
		}
		Matrix clone,reset;
		clone = new Matrix(m.row,m.col);
		clone = m;
		reset = new Matrix(m.row,m.col);
		reset = m;
		double [] solution = new double[m.row];
		clone.printMatrix();
		for(int i = 0; i<m.col-1; i++) {
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = m.Mat[j][m.col-1];
			}
			clone.printMatrix();
			System.out.println("");
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = reset.Mat[j][i];
			}
		}
	}
}