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
	public static Matrix ConvertSegitigaAtas(Matrix m){
		int i, j, k;
		Matrix mSegitiga;
		int swap = 0; //menghitung berapa kali operasi tukar baris dilakukan
		mSegitiga = new Matrix(m.row,m.col);

		//copy
		for(i=0;i<m.row;i++) {
			for(j=0;j<m.col;j++)
			{
				mSegitiga.Mat[i][j] = m.Mat[i][j]; 
			}
		};

		//ubah
		for (i = 0;i < (mSegitiga.row - 1);i++){ //mSegitiga.row "just in case"

			// mencari apakah nilainya 0 dan apakah ada baris lain yang nilainya bukan 0
			if (mSegitiga.Mat[i][i] == 0){
				for (j = i+1;j < mSegitiga.row;j++){
					if (mSegitiga.Mat[j][i] != 0){
						mSegitiga.swapRow(i, j);
						swap += 1;
						break;
					};
				};
			};

			// kalau masih 0, skip
			if (mSegitiga.Mat[i][i] != 0){
				for (j = i+1;j < mSegitiga.row;j++){
					double multiply = mSegitiga.Mat[j][i];
					double divide = mSegitiga.Mat[i][i];
					for (k = 0;k < mSegitiga.col;k++){
						mSegitiga.Mat[j][k] = mSegitiga.Mat[j][k] - (multiply*((mSegitiga.Mat[i][k])/divide)); // agak ribet but it works trust me bro
					};
				};
			};
		};

		// kalau jumlah operasi tukar baris ganjil, kalikan baris terakhir dengan -1 supaya determinannya sama "just in case"
		if (swap % 2 == 1){
			for(i = 0;i < mSegitiga.col;i++){
				mSegitiga.Mat[mSegitiga.row - 1][i] = (mSegitiga.Mat[mSegitiga.row - 1][i]) * (-1);
			};
		};

		return mSegitiga;
		// test case 1 2 3 4 2 9 11 15 3 11 22 28 31 112 223 294
		// hasilnya 1 2 3 4 0 5 5 7 0 0 8 9 0 0 0 10

		/* ternyata buat jadi matriks eselon bukan cuma membagi baris saja, misalnya matriks 
		1 2 3 4                   1 2 3 4          1 2 3 4
		0 0 0 5                   0 0 0 1          0 0 0 1
		0 0 0 6                   0 0 0 1          0 0 0 0
		0 0 0 7 itu nanti jadinya 0 0 0 1 harusnya 0 0 0 0 nanti kalau ada yang bisa pikirin caranya gimana*/
	}
}
