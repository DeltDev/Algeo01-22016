package LinearAlgebra;
import LinearAlgebra.*;
import Menu.EnhancedIO;
public class EquationSystem {
	public static void CramerRule(Matrix m) {
		double D;
		D = Determinant.ReduksiBaris(m);
		
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
		double [] Det = new double[m.row];
		double [] solution = new double[m.row];

		boolean allDetZero;
		allDetZero = true;
		System.out.println("Solusi:");
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = konstan.Mat[j][0];
			}
			Det[i] = Determinant.ReduksiBaris(clone);
			if(Det[i] != 0) {
				allDetZero = false;
			}
			for(int j = 0; j<m.row; j++){
				clone.Mat[j][i] = reset.Mat[j][i];
			}
		}			
		
		if(D == 0) {
			if(allDetZero) {
				System.out.println("Sistem persamaan linear berikut memiliki solusi banyak/parametrik.");
				return;
			} else {
				System.out.println("Sistem persamaan linear berikut tidak memiliki solusi.");
				return;
			}
		} else {
			System.out.println("Solusi persamaan linear berikut adalah:");
			for(int i = 0; i<m.row; i++) {
				solution[i] = Det[i]/D;
				System.out.print("x["+ (i+1) + "] =");
				EnhancedIO.OutputDoublePrecision4(solution[i]);
				System.out.println("");
			}
			return;
		}


	}
}
