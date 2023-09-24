import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1,m2;
		m = new Matrix(3,3);
		m1 = new Matrix(3,3);
		m2 = new Matrix(3,3);
		m.inputMatrix();

		Inverse.GaussJordan(m);
	}
}
