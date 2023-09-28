package Menu;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import LinearAlgebra.*;

import java.util.Scanner;
import SPLTuples.*;
public class EnhancedIO { //Class ini untuk input lewat keyboard dan output secara umum
	public static Matrix InputSquareMatrix() {
		int n;
		Matrix m;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan ukuran matriks persegi: ");
		n = in.nextInt();
		m = new Matrix(n,n);
		System.out.println("Input isi matriks:");
		m.inputMatrix(n,n);
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
		m.inputMatrix(baris,kolom);
		
		return m;
	}
	
	public static Matrix InputSPL() {
		int banyakPersamaan,banyakVariabel;
		Matrix m;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan banyak persamaan: ");
		banyakPersamaan = in.nextInt();
		System.out.print("Masukkan banyak variabel peubah (n): ");
		banyakVariabel = in.nextInt();
		m = new Matrix(banyakVariabel,banyakVariabel+1);
		System.out.println("Masukkan persamaan linear a[1]x[1] + a[2]x[2] + ... a[n]x[n] = b");
		System.out.println("dengan format : a[1] a[2] ... a[n] b");
		m.inputMatrix(banyakPersamaan,banyakVariabel+1);
		return m;
	}
	public static void OutputDoublePrecision4(double d) {
		System.out.print(BigDecimal.valueOf(d).setScale(4,RoundingMode.HALF_EVEN).toPlainString());
	}
	
	public static void OutputDet(double d) {
		System.out.print("Determinan dari matriks tersebut adalah: ");
		EnhancedIO.OutputDoublePrecision4(d);
		System.out.println("");
	}
	
	public static void OutputSPL(SPLTuples SPL) {
		if(SPL.isSolvable) {
			if(SPL.isParametric) {
				System.out.println("Solusinya parametrik");
			} else {
				System.out.println("Solusinya adalah: ");
				
				for(int i = 0; i<SPL.Solution.length; i++) {
					System.out.print("x["+ (i+1) +"]= ");
					EnhancedIO.OutputDoublePrecision4(SPL.Solution[i]);
					System.out.println("");
				}
			}
		} else {
			System.out.println("Solusinya tidak ada");
		}
	}
	
	public static void OutputFunction(SPLTuples SPL, boolean regressionfunction) {
		int i;
		for(i = 0; i<SPL.Solution.length; i++) {
			if(i != 0) {
				if(SPL.Solution[i] >=0) {
					System.out.print(" + ");
				} else {
					System.out.print(" ");
				}
			}
			EnhancedIO.OutputDoublePrecision4(SPL.Solution[i]);
			if(regressionfunction) {
				if(i !=0) {
					System.out.print("x");
					System.out.print("["+i+"]");
					if(i != 1) {
						System.out.print("^");
						System.out.print(i);
					}
				}
			} else {
				if(i !=0) {
					System.out.print("x");
					if(i != 1) {
						System.out.print("^");
						System.out.print(i);
					}
				}
			}

		}
		System.out.println("");
	}
}
