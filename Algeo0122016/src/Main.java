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
		Matrix mat3 = new Matrix(row1,col1);
		mat3 = Matrix.Minus(mat1,mat1);
		mat3.printMatrix();
	}
}
