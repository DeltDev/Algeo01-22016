package Menu;
import java.io.*;

import LinearAlgebra.*;

import java.util.Scanner;
public class Input {
	public static Matrix SquareMatrix() {
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
	
	public static Matrix RectMatrix() {
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
}
