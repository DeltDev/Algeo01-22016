import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
import Misc.Misc;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1,m2;
		m = new Matrix(16,16);

		m  = Misc.GenerateBCubicSplineMat();
		m.printMatrix();
	}
}
