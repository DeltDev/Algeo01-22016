package LinearAlgebra;
import java.io.*;
import java.util.Scanner;
import java.math.*;
import Menu.EnhancedIO;
public class Matrix {
	//Atribut Matrix
	public int row,col;
	public double [][] Mat = new double[row][col];
	
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
	
	//Prosedur untuk input matriks
	public void inputMatrix(int r, int c) {
		Scanner in = new Scanner(System.in);
		int i,j;
		for(i = 0; i<r; i++) {
			for(j = 0; j<c; j++) {
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
					EnhancedIO.OutputDoublePrecision4(this.Mat[i][j]);
				} else {
					EnhancedIO.OutputDoublePrecision4(this.Mat[i][j]);
					System.out.print(",");
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
	public static Matrix PlusMinus(Matrix m1, Matrix m2,boolean minus)
	{
		int i,j,sign;
		sign = 1;
		if(minus) {
			sign *= -1;
		}
		Matrix ret;
		ret = new Matrix(m1.row,m1.col);
		for(i=0;i<m1.row;i++) {
			for(j=0;j<m1.col;j++)
			{
				ret.Mat[i][j] = m1.Mat[i][j] + m2.Mat[i][j] * sign; 
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
			for(j = 0; j<m2.col; j++) {
				ret.Mat[i][j] = 0;
				for(k = 0; k<m1.col; k++) {
					ret.Mat[i][j] = ret.Mat[i][j] + m1.Mat[i][k] * m2.Mat[k][j];
				}
			}
		}
		return ret;
	}
	
	public static boolean AllRowZero(Matrix m, int row) {
		for(int i = 0; i<m.col; i++) {
			if(m.Mat[row][i] != 0) {
				return false;
			}
		}
		return true;
	}
	public static Matrix Augment(Matrix m1, Matrix m2) {
		Matrix aug = new Matrix(m1.row,m1.col+1);
		for(int i = 0; i<m1.row; i++) {
			for(int j = 0; j<m1.col; j++) {
				aug.Mat[i][j] = m1.Mat[i][j];			
			}
		}
		
		for(int i = 0; i<m1.row; i++) {
			aug.Mat[i][m1.col] = m2.Mat[i][0];
		}
		return aug;
	}
	
	public static Matrix toColMatrix(Matrix m) {
		Matrix ret = new Matrix(m.row*m.row,1);
		int cnt = 0;
		
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.col; j++) {
				ret.Mat[cnt][0] =m.Mat[i][j];
				cnt++;
			}
		}
		return ret;
	}
}
