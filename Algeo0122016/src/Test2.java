import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1;
		m = new Matrix(3,4);
		m.inputMatrix();

		double det1,det2;
		
		det1 = Determinant.ReduksiBaris(m);
		det2 = Determinant.EkspansiKofaktor(m);
		m1 = Echelon.REF(m);
		
		m1.printMatrix();
	}
}
