import LinearAlgebra.*;
import java.io.*;
import java.util.Scanner;
public class Test2 {
	public static void main(String[] args) {
		Matrix m,m1;
		m = new Matrix(4,4);
		m.inputMatrix();
		m.printMatrix();
		double det1,det2;
		
		det1 = Determinant.ReduksiBaris(m);
		det2 = Determinant.EkspansiKofaktor(m);
		
		System.out.println(det1);
		System.out.println(det2);
	}
}
