package LinearAlgebra;
import LinearAlgebra.*;
public class Inverse {
	public static Matrix GaussJordan(Matrix m) {
		//asumsikan m punya invers
		Matrix doubleM,doubleInv;
		doubleM = new Matrix(m.row,2*m.row);
		doubleInv = new Matrix(m.row,2*m.row);
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

	public static Matrix Adjoin(Matrix m){
		double det = Determinant.EkspansiKofaktor(m);
		int i, j, i2, j2;
		//asumsi matriks persegi dan ada invers (determinan != 0)
		Matrix Inv, nyariDet;
		Inv = new Matrix (m.row, m.row);
		if (m.row == 1){Inv.Mat[0][0] = 1 / m.Mat[0][0]; return Inv;}else{//Kasus matriks ukuran 1x1
			nyariDet = new Matrix ((m.row - 1), (m.row - 1));
			//matriks kofaktor yg sdh dibagi determinan
			for (i = 0;i < m.row;i++){
				for (j = 0;j < m.row;j++){
					for (i2 = 0;i2 < m.row;i2++){
						for (j2 = 0;j2 < m.row;j2++){
							if (i2 < i){
								if (j2 < j){nyariDet.Mat[i2][j2] = m.Mat[i2][j2];} else if (j2 > j){nyariDet.Mat[i2][j2 - 1] = m.Mat[i2][j2];};
							} else if (i2 > i){
								if (j2 < j){nyariDet.Mat[i2 - 1][j2] = m.Mat[i2][j2];} else if (j2 > j){nyariDet.Mat[i2 - 1][j2 - 1] = m.Mat[i2][j2];};
							};
						};
					};
					if ((i + j) % 2 == 0){
						Inv.Mat[i][j] = (Determinant.EkspansiKofaktor(nyariDet)) / det;
					} else {
						Inv.Mat[i][j] = ((Determinant.EkspansiKofaktor(nyariDet)) * (-1)) / det;
					};
				};
			};
		//transpose
		Inv = Inv.Transpose();
		return Inv;
		}
	}
}
