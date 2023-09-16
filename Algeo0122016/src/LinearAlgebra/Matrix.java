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
}
