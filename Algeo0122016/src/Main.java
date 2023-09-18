import java.io.*;

import LinearAlgebra.*;

import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int row1,col1;
		row1 = in.nextInt();
		col1 = in.nextInt();
		Matrix mat1 = new Matrix(row1,col1);
		mat1.inputMatrix();
		Matrix mat2 = new Matrix(col1,row1);
		mat2 = mat1.Transpose();
		mat2.printMatrix();
	}
}
