package LinearAlgebra;
import LinearAlgebra.*;
import SPLTuples.*;
import Menu.EnhancedIO;
public class EquationSystem {
	public static void CramerRule(Matrix m) { //tidak feasible untuk di daur ulang untuk menyelesaikan solusi parametrik
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

		boolean allDetNotZero;
		allDetNotZero = true;
		System.out.println("Solusi:");
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = konstan.Mat[j][0];
			}
			Det[i] = Determinant.ReduksiBaris(clone);
			if(Det[i] == 0) {
				allDetNotZero = false;
			}
			for(int j = 0; j<m.row; j++){
				clone.Mat[j][i] = reset.Mat[j][i];
			}
		}			
		
		if(D == 0) {
			if(allDetNotZero) {
				System.out.println("Sistem persamaan linear berikut tidak memiliki solusi.");
				return;
			} else {
				System.out.println("Sistem persamaan linear berikut memiliki solusi banyak/parametrik.");
				return;
			}
		} else {
			System.out.println("Solusinya adalah:");
			for(int i = 0; i<m.row; i++) {
				solution[i] = Det[i]/D;
				System.out.print("x["+ (i+1) + "]= ");
				EnhancedIO.OutputDoublePrecision4(solution[i]);
				System.out.println("");
			}
			return;
		}
	}
	
	public static SPLTuples Gauss(Matrix m) {
		Matrix m1;
		m1 = new Matrix(m.row,m.col);
		m1 = Echelon.REF(m);
		
		boolean isSol;
		boolean isPara;
		double [] Solution = new double [m.row];
		for(int i = 0; i<m.row; i++) {
			Solution[i] = Double.NaN;
		}
		if(Matrix.AllBottomZero(m1)) {
			if(m1.Mat[m.row-1][m.col-1] != 0) {
				isSol = false;
				isPara = false;
			} else {
				isSol = true;
				isPara = true;
			}
		} else {
			isSol = true;
			isPara = false;
			
			Solution[m.row-1] = m1.Mat[m.row-1][m.col-1];
			
			for(int i = m.row-2; i>=0; i--) {
				Solution[i] = m1.Mat[i][m.col-1];
				for(int j = i+1; j<m.col-1; j++) {
					Solution[i] = Solution[i] - m1.Mat[i][j]*Solution[j];
				}
			}
		}
		SPLTuples ret = new SPLTuples(m1, Solution, isSol, isPara);
		return ret;
	}
	
	public static SPLTuples GaussJordan(Matrix m) {
		Matrix m1;
		m1 = new Matrix(m.row,m.col);
		m1 = Echelon.RREF(m);
		boolean isSol;
		boolean isPara;
		double [] Solution = new double [m.row];
		for(int i = 0; i<m.row; i++) {
			Solution[i] = Double.NaN;
		}
		if(Matrix.AllBottomZero(m1)) {
			if(m1.Mat[m.row-1][m.col-1] != 0) {
				isSol = false;
				isPara = false;
			} else {
				isSol = true;
				isPara = true;
			}
		} else {
			isSol = true;
			isPara = false;
			
			for(int i = 0; i<m.row; i++) {

				Solution[i] = m1.Mat[i][m.col-1];
			}
		}
		
		SPLTuples ret = new SPLTuples(m1, Solution, isSol, isPara);
		return ret;
	}
	public static void Inverse(Matrix m) { //tidak feasible untuk di daur ulang karena tidak bisa menentukan solusi parametrik
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
		System.out.println("Solusinya adalah:");
		
		for(int i = 0; i<m.row; i++) {
			System.out.print("x["+ (i+1) + "]= ");
			EnhancedIO.OutputDoublePrecision4(solution.Mat[i][0]);
			System.out.println("");
		}
	}
}
