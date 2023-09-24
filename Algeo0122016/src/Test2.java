import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1,m2;
		m = new Matrix(3,6);
		m1 = new Matrix(3,6);
		m2 = new Matrix(3,6);
		m.inputMatrix();

		double det1,det2;
		
		det1 = Determinant.ReduksiBaris(m);
		det2 = Determinant.EkspansiKofaktor(m);
		m1 = Echelon.REF(m);
		m2 = Echelon.RREF(m);
		m1.printMatrix();
		m2.printMatrix();
	}
}
