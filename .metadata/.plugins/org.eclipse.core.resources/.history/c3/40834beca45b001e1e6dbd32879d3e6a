package LinearAlgebra;
import LinearAlgebra.*;
public class EquationSystem {
	public static void CramerRule(Matrix m) {
		double D;
		D = Determinant.EkspansiKofaktor(m);
		
		if(D == 0) {
			System.out.println("Sistem Persamaan Linear berikut tidak memiliki solusi atau memiliki solusi parametrik.");
			return;
		}
		Matrix clone,konstan,reset;
		clone = new Matrix(m.row,m.row);
		reset = new Matrix(m.row,m.row);
		konstan = new Matrix(m.row,1);
		for(int i = 0; i<m.row; i++){
			for(int j = 0; j<m.row; j++){
				clone.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(int i = 0; i<m.row; i++){
			for(int j = 0; j<m.row; j++){
				reset.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(int i = 0; i<m.row; i++){
			konstan.Mat[i][0] = m.Mat[i][m.col-1];
		}
		
		double [] solution = new double[m.row];
		double [] Det = new double[m.row];
		
		System.out.println("Solusi:");
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = konstan.Mat[j][0];
			}
			
			Det[i] = Determinant.ReduksiBaris(clone);
			solution[i] = (Determinant.EkspansiKofaktor(clone))/D;
			
			
			System.out.println("x[" + (i+1) + "] = " + solution[i]);
			
			for(int j = 0; j<m.row; j++){
				clone.Mat[j][i] = reset.Mat[j][i];
			}

		}
		
	}
}
