import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
import Misc.Misc;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1,m2,m3;
		m = new Matrix(4,4);
		m.inputMatrix(4, 4);
		m2 = new Matrix(16,16);
		m3 = new Matrix(16,17);
		m1  = Matrix.toColMatrix(m);
		m1.printMatrix();
		m2 = Misc.GenerateBCubicSplineMat();
		m3 = Matrix.Augment(m2, m1);
		m3.printMatrix();
		EquationSystem.Inverse(m3);
	}
}
