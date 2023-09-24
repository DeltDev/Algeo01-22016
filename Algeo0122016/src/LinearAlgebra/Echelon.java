package LinearAlgebra;
import LinearAlgebra.*;
public class Echelon {
	
	public static Matrix REF(Matrix m) {
		int r = m.row;
		int c = m.col;
		Matrix m1 = new Matrix(r,c);
		double ratio;

		for(int i = 0; i<r; i++) {//copy matrix m ke m1 lokal
			for(int j = 0; j<c; j++) {
				m1.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(int i = 0; i<r-1; i++) { //ubah matrix m jadi matriks eselon
			for(int j = r-1; j>i; j--) {
				if(m1.Mat[j][i] == 0) {
					continue;
				} else {
					if(m1.Mat[j-1][i] == 0) {
						m1.swapRow(j,j-1);
						continue;
					}
						
					ratio = m1.Mat[j][i] / m1.Mat[j-1][i];
					for(int l = 0; l<c; l++) {
						m1.Mat[j][l] = m1.Mat[j][l] - ratio * m1.Mat[j-1][l];
					}
				}
			}
		}
		
		double divider;
		for(int i = 0; i<r; i++) {
			divider = m1.Mat[i][i];
			for(int j = i; j<c; j++) {
				if(divider != 0) {
					m1.Mat[i][j] = m1.Mat[i][j] / divider;
				}
			}
		}
		return m1;
	}
	
	public static Matrix RREF(Matrix m) {
		Matrix REF1;
		REF1 = new Matrix(m.row,m.col);
		REF1 = Echelon.REF(m);
		double mul;
		for(int i = 1; i<m.row; i++) {
			for(int j = 0; j<i; j++) {
				mul = REF1.Mat[j][i];
				for(int k = 0; k<m.col; k++) {
					REF1.Mat[j][k] = REF1.Mat[j][k] - mul * REF1.Mat[i][k];
				}
			}
		}
		return REF1;
	}
}
