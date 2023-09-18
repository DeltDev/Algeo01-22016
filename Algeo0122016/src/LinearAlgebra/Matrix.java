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
}
