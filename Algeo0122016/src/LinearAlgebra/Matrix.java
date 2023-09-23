package LinearAlgebra;
import java.io.*;
import java.util.Scanner;

public class Matrix {
	//Atribut Matrix
	int row,col;
	double [][] Mat = new double[row][col];
	
	//Konstruktor
	public Matrix(int rowInput, int colInput) {
		this.col = colInput;
		this.row = rowInput;
		int i,j;
		Mat = new double [row][col];
		for(i = 0; i<rowInput; i++) {
			for(j = 0; j<colInput; j++) {
				this.Mat[i][j] = 0;
			}
		}
	}
	
	//prosedur untuk input elemen matrix di baris row dan kolom col
	public void inputELMT(double val, int row, int col) {
		this.Mat[row][col] = val;
	}
	
	//Selektor untuk mengakses Elemen di baris row dan col
	public double ELMT(int row, int col){
		
		return this.Mat[row][col];
	}
	
	//Prosedur untuk input matriks
	public void inputMatrix() {
		Scanner in = new Scanner(System.in);
		int i,j;
		for(i = 0; i<this.row; i++) {
			for(j = 0; j<this.col; j++) {
				this.Mat[i][j] = in.nextDouble();
			}
		}
	}
	
	//Prosedur untuk output matriks
	public void printMatrix() {
		int i,j;
		for(i = 0; i<this.row; i++) {
			System.out.print("[");
			for(j = 0; j<this.col; j++) {
				if(j == this.col - 1) {
					System.out.print(this.Mat[i][j]);
				} else {
					System.out.print(this.Mat[i][j] + ",");
				}
			}
			System.out.println("]");
		}
	}
	
	//prosedur untuk menukar baris pada suatu matriks
	public void swapRow(int row1, int row2) {
		int i;
		double swaphelp;
		for(i = 0; i<this.col; i++) {
			swaphelp = this.Mat[row1][i];
			this.Mat[row1][i] = this.Mat[row2][i];
			this.Mat[row2][i] = swaphelp;
		}
	}
	
	//prosedur untuk membuat matriks transpose
	public Matrix Transpose(){
		int i,j;
		Matrix ret;
		ret = new Matrix(this.col,this.row);
		for(i=0;i<this.row;i++){
			for (j=0;j<this.col;j++)
			{
				ret.Mat[j][i] = this.Mat[i][j];
			}
		}
		return ret;
	}
	//prosedur untuk menambahkan matriks
	public static Matrix Plus(Matrix m1, Matrix m2)
	{
		int i,j;
		Matrix ret;
		ret = new Matrix(m1.row,m1.col);
		for(i=0;i<m1.row;i++) {
			for(j=0;j<m1.col;j++)
			{
				ret.Mat[i][j] = m1.Mat[i][j] + m2.Mat[i][j]; 
			}
		}
		return ret;
	}
	
	//prosedur untuk mengurangi matriks
	public static Matrix Minus(Matrix m1, Matrix m2)
	{
		int i,j;
		Matrix ret;
		ret = new Matrix(m1.row,m1.col);
		for(i=0;i<m1.row;i++) {
			for(j=0;j<m1.col;j++)
			{
				ret.Mat[i][j] = m1.Mat[i][j] - m2.Mat[i][j]; 
			}
		}
		return ret;
	}
	
	//prosedur perkalian matriks
	public static Matrix Multiply(Matrix m1, Matrix m2) {
		Matrix ret;
		ret = new Matrix(m1.row,m2.col);
		int i,j,k;
		for (i = 0; i<m1.row; i++) {
			for(j = 0; j<m2.row; j++) {
				ret.Mat[i][j] = 0;
				for(k = 0; k<m1.col; k++) {
					ret.Mat[i][j] = ret.Mat[i][j] + m1.Mat[i][k] * m2.Mat[k][j];
				}
			}
		}
		return ret;
	}

	//fungsi mengubah matriks menjadi matriks segitiga ATAS
	public static double DeterminanReduksiBaris(Matrix m){
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

/*	//fungsi menghitung determinan dengan ekspansi kofaktor
	public static double DeterminanKofaktor(Matrix m){
		if (m.row == 1){return m.Mat[0][0];} else { // kasus matriks ukuran 1x1
			double Sum = 0;
			Matrix ReCursed;
			ReCursed = new Matrix((m.row - 1), (m.col - 1)); //matriks yang akan digunakan untuk rekursi jadi row dan col nya dikurangi 1
			int i, j;
			for (i = 1;i < m.row;i++){
				for (j = 1;j < m.col;j++){
					ReCursed.Mat[i-1][j-1] = m.Mat[i][j]; // copy tapi tanpa baris dan kolom pertama
				};
			};
			Sum += (DeterminanKofaktor(ReCursed) * m.Mat[0][0]); // first
			for (i = 1;i < m.row;i++){
				for (j = 1;j < m.col;j++){
					ReCursed.Mat[i-1][j-1] = m.Mat[i-1][j]; // untuk yang selanjutnya copy 1 baris
				};
				if (i % 2 == 0){Sum += (DeterminanKofaktor(ReCursed) * m.Mat[i][0]);} else {Sum -= (DeterminanKofaktor(ReCursed) * m.Mat[i][0]);}
			};
			return Sum;
		}
	}
*/
	/*public static void CramerSolve(Matrix m) {
		double D;
		D = Matrix.DeterminanKofaktor(m);
		
		if(D == 0) {
			System.out.println("Sistem Persamaan Linear berikut tidak memiliki solusi.");
			return;
		}
		Matrix clone,reset;
		clone = new Matrix(m.row,m.col);
		clone = m;
		reset = new Matrix(m.row,m.col);
		reset = m;
		double [] solution = new double[m.row];
		clone.printMatrix();
		for(int i = 0; i<m.col-1; i++) {
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = m.Mat[j][m.col-1];
			}
			clone.printMatrix();
			System.out.println("");
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = reset.Mat[j][i];
			}
		}
	}*/
}
