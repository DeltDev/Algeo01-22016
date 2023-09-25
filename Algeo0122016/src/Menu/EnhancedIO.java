package Menu;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import LinearAlgebra.*;

import java.util.Scanner;
public class EnhancedIO {
	public static Matrix InputSquareMatrix() {
		int n;
		Matrix m;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan ukuran matriks persegi: ");
		n = in.nextInt();
		m = new Matrix(n,n);
		System.out.println("Input isi matriks:");
		m.inputMatrix();
		return m;
	}
	
	public static Matrix InputRectMatrix() {
		int baris,kolom;
		Matrix m;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan banyak baris matriks: ");
		baris = in.nextInt();
		System.out.print("Masukkan banyak kolom matriks: ");
		kolom = in.nextInt();
		m = new Matrix(baris,kolom);
		System.out.println("Input isi matriks:");
		m.inputMatrix();
		
		return m;
	}
	
	public static void OutputDoublePrecision4(double d) {
		System.out.print(BigDecimal.valueOf(d).setScale(4,RoundingMode.HALF_EVEN).toPlainString());
	}
}
