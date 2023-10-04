package LinearAlgebra;
import LinearAlgebra.*;
public class Echelon {
	
	public static Matrix REF(Matrix m) {
		int r = m.row;
		int c = m.col;
		Matrix m1 = new Matrix(r,c);
		double mul, div;
		int i, j;
		int a = 0;

		for(i = 0; i<r; i++) {//copy matrix m ke m1 lokal
			for(j = 0; j<c; j++) {
				m1.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(i = 0; i<c; i++) { //ubah matrix m jadi matriks eselon
			for(j = r-1; j>a; j--) {
				if(m1.Mat[j][i] == 0) {
					continue;
				} else {
					if(m1.Mat[j-1][i] == 0) {
						m1.swapRow(j,j-1);
						continue;
					}
					
					mul = m1.Mat[j][i];
					div = m1.Mat[j-1][i];
					for(int l = 0; l<c; l++) {
						m1.Mat[j][l] = m1.Mat[j][l] - mul * (m1.Mat[j-1][l] / div);
					}
				}
			}
			if (m1.Mat[j][i] != 0){a++;};
		}
		
		double divider;
		for(i = 0; i<r; i++) {
			//cari index bukan 0 pertama
			for (j = 0;j < c;j++){
				if (m1.Mat[i][j] != 0){a = j; break;};
			};
			if (j == c){a = 0;};
			divider = m1.Mat[i][a];
			if (j != c){
				for(j = i; j<c; j++) {
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
		int a, b;
		a = -1;
		for(int i = 1; i<m.row; i++) {
			//cari index bukan 0 pertama
			for (b = 0;b < m.col;b++){
				if (REF1.Mat[i][b] != 0){a = b; break;};
			};
			if (b != m.col){
				for(int j = 0; j<i; j++) {
					mul = REF1.Mat[j][a];
					for(int k = 0; k<m.col; k++) {
						REF1.Mat[j][k] = REF1.Mat[j][k] - mul * REF1.Mat[i][k];
					}
				}
			};
		}
		return REF1;
	}
}
