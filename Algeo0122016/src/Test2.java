import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1,m2;
		m = new Matrix(3,4);

		m.inputMatrix();
		EquationSystem.Gauss(m);
		EquationSystem.GaussJordan(m);
		Inverse.GaussJordan(m);
	}
}
