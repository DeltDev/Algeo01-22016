package LinearAlgebra;
import LinearAlgebra.*;
public class Inverse {
	public static void GaussJordan(Matrix m) {
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

		
		if(Matrix.AllBottomZero(doubleInv)) {
			System.out.println("Matriks tersebut tidak punya invers.");
		} else {
			System.out.println("Invers matriks tersebut adalah: ");
			Matrix Inv;
			Inv = new Matrix (m.row,m.col);
			
			for(int i = 0; i<m.row; i++) {
				for(int j = 0; j<m.col; j++) {
					Inv.Mat[i][j] = doubleInv.Mat[i][j+m.row];
				}
			}
			Inv.printMatrix();
		}
	}
}
