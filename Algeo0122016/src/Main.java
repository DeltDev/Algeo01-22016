import java.io.*;
import LinearAlgebra.Matrix;
import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int row,col;
		row = in.nextInt();
		col = in.nextInt();
		Matrix mat = new Matrix(row,col);
		mat.InputELMT(5, 1, 1);
		System.out.println(mat.ELMT(1, 1));
	}
}
