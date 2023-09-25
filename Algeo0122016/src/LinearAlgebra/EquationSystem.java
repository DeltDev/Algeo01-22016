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
	
	public static void Gauss(Matrix m) {
		Matrix m1;
		m1 = new Matrix(m.row,m.col);
		m1 = Echelon.REF(m);
		
		if(Matrix.AllBottomZero(m1)) {
			if(m1.Mat[m.row-1][m.col-1] != 0) {
				System.out.println("Sistem persamaan berikut tidak memiliki solusi.");
				return;
			} else {
				System.out.println("Sistem persamaan berikut memiliki solusi parametrik");
				return;
			}
		} else {
			double [] Solution = new double [m.row];
			Solution[m.row-1] = m1.Mat[m.row-1][m.col-1];
			
			for(int i = m.row-2; i>=0; i--) {
				Solution[i] = m1.Mat[i][m.col-1];
				for(int j = i+1; j<m.col-1; j++) {
					Solution[i] = Solution[i] - m1.Mat[i][j]*Solution[j];
				}
			}
			System.out.println("Solusi persamaan linear berikut adalah:");
			
			for(int i = 0; i<m.row; i++) {
				System.out.print("x["+ (i+1) + "] =");
				EnhancedIO.OutputDoublePrecision4(Solution[i]);
				System.out.println("");
			}
		}
	}
	
	public static void GaussJordan(Matrix m) {
		Matrix m1;
		m1 = new Matrix(m.row,m.col);
		m1 = Echelon.RREF(m);
		
		if(Matrix.AllBottomZero(m1)) {
			if(m1.Mat[m.row-1][m.col-1] != 0) {
				System.out.println("Sistem persamaan berikut tidak memiliki solusi.");
				return;
			} else {
				System.out.println("Sistem persamaan berikut memiliki solusi parametrik");
				return;
			}
		} else {
			System.out.println("Solusi persamaan linear berikut adalah:");
			
			for(int i = 0; i<m.row; i++) {
				System.out.print("x["+ (i+1) + "] =");
				EnhancedIO.OutputDoublePrecision4(m1.Mat[i][m.col-1]);
				System.out.println("");
			}
		}
	}
	public static void Inverse(Matrix m) {
		Matrix m1,m2,konstan,solution,variabel;
		m1 = new Matrix(m.row,2*m.row);
		variabel = new Matrix(m.row,m.row);
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.row; j++) {
				variabel.Mat[i][j] = m.Mat[i][j];
			}
		}
		m1 = Inverse.GaussJordan(variabel);

		if(Matrix.AllBottomZero(m1)) {
			System.out.println("Sistem persamaan linear tersebut mungkin memiliki solusi parametrik atau tidak memiliki solusi");
			return;
		} 
		m2 = Inverse.GetInverseGJ(m1);

		konstan = new Matrix(m.row,1);
		
		for(int i = 0; i<m.row; i++) {
			konstan.Mat[i][0] = m.Mat[i][m.col-1];
		}

		solution = new Matrix(m.row,1);
		solution = Matrix.Multiply(m2, konstan);
		System.out.println("Solusi persamaan linear berikut adalah:");
		
		for(int i = 0; i<m.row; i++) {
			System.out.print("x["+ (i+1) + "]= ");
			EnhancedIO.OutputDoublePrecision4(solution.Mat[i][0]);
			System.out.println("");
		}
	}
}
