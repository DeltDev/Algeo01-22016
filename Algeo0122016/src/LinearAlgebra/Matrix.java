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
	
	public void InputELMT(double val, int row, int col) {
		this.Mat[row][col] = val;
	}
	
	public double ELMT(int row, int col){
		
		return this.Mat[row][col];
	}
	
	
}
