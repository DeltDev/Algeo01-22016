package LinearAlgebra;
import LinearAlgebra.*;
public class Inverse {
	public static Matrix GaussJordan(Matrix m) {
		//asumsikan m punya invers
		Matrix doubleM,doubleInv;
		doubleM = new Matrix(m.row,2*m.col);
		doubleInv = new Matrix(m.row,2*m.col);
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.col; j++) {
				doubleM.Mat[i][j] = m.Mat[i][j];
			}
		}
		
		for(int i = 0; i<m.row; i++) {
			doubleM.Mat[i][i+m.row] = 1;
		}
		

		doubleInv = Echelon.RREF(doubleM);
		return doubleInv;
	}
	
	public static Matrix GetInverseGJ(Matrix m) {

			Matrix Inv;
			Inv = new Matrix (m.row,m.row);
			
			for(int i = 0; i<m.row; i++) {
				for(int j = 0; j<m.row; j++) {
					Inv.Mat[i][j] = m.Mat[i][j+m.row];
				}
			}
			return Inv;
	}
}
