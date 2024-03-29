package LinearAlgebra;
import LinearAlgebra.Matrix;
public class Determinant {
	//fungsi mengubah matriks menjadi matriks segitiga ATAS
	public static double ReduksiBaris(Matrix m){
		int n = m.row;
		Matrix m1 = new Matrix(n,n);
		double ratio,temp;
		int swapcount;
		swapcount = 0;
		for(int i = 0; i<n; i++) {//copy matrix m ke m1 lokal
			for(int j = 0; j<n; j++) {
				m1.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(int i = 0; i<n-1; i++) { //ubah matrix m jadi matrix segitiga Atas
			for(int j = n-1; j>i; j--) {
				if(m1.Mat[j][i] == 0) {
					continue;
				} else {
					if(m1.Mat[j-1][i] == 0) {
						m1.swapRow(j,j-1);
						swapcount++;
						continue;
					}
						
					ratio = m1.Mat[j][i] / m1.Mat[j-1][i];
					for(int l = 0; l<n; l++) {
						m1.Mat[j][l] = m1.Mat[j][l] - ratio * m1.Mat[j-1][l];
					}
				}
			}
		}
		
		double det;
		det = 1;
		for(int i = 0; i<n; i++) {
			det *= m1.Mat[i][i];
		}
		
		if(swapcount % 2 == 0) {
			return det;
		} else {
			return det * (-1);
		}
	}

	//fungsi menghitung determinan dengan ekspansi kofaktor
	public static double EkspansiKofaktor(Matrix m){
		if (m.row == 1)
			{return m.Mat[0][0];} 
		else { // kasus matriks ukuran 1x1
			double Sum = 0;
			Matrix ReCursed;
			ReCursed = new Matrix((m.row - 1), (m.col - 1)); //matriks yang akan digunakan untuk rekursi jadi row dan col nya dikurangi 1
			int i, j;
			for (i = 1;i < m.row;i++){
				for (j = 1;j < m.col;j++){
					ReCursed.Mat[i-1][j-1] = m.Mat[i][j]; // copy tapi tanpa baris dan kolom pertama
				};
			};
			Sum += (EkspansiKofaktor(ReCursed) * m.Mat[0][0]); // first
			for (i = 1;i < m.row;i++){
				for (j = 1;j < m.col;j++){
					ReCursed.Mat[i-1][j-1] = m.Mat[i-1][j]; // untuk yang selanjutnya copy 1 baris
				};
				if (i % 2 == 0){Sum += (EkspansiKofaktor(ReCursed) * m.Mat[i][0]);} else {Sum -= (EkspansiKofaktor(ReCursed) * m.Mat[i][0]);}
			};
			return Sum;
		}
	}
	
}
