import java.io.*;

import LinearAlgebra.*;

import java.util.Scanner;
import Menu.*;
import java.math.*;
public class Main {

	public static void main(String[] args) {	
		Matrix m;
		int baris;
		int kolom;
		int banyakPersamaan;
		Scanner in = new Scanner(System.in);
		int inputMenu, inputSubmenu;
		while (true){
			System.out.println("MENU");
			System.out.println("1. Sistem Persamaan Linier");
			System.out.println("2. Determinan");
			System.out.println("3. Matriks balikan");
			System.out.println("4. Interpolasi Polinom");
			System.out.println("5. Interpolasi Bicubic Spline");
			System.out.println("6. Regresi linier berganda");
			System.out.println("7. Keluar");
			inputMenu = in.nextInt();
			switch (inputMenu){
				case 1:
					while (true){
						System.out.println("SISTEM PERSAMAAN LINIER");
						System.out.println("1. Metode eliminasi Gauss");
						System.out.println("2. Metode eliminasi Gauss-Jordan");
						System.out.println("3. Metode matriks balikan");
						System.out.println("4. Kaidah Cramer");
						inputSubmenu = in.nextInt();
						switch (inputSubmenu){
							case 1:
								// fungsi SPL Gauss
								break;
							case 2:
								// fungsi SPL Gauss-Jordan
								break;
							case 3:
								// fungsi SPL matriks balikan
								
								break;
							case 4:
								// fungsi SPL Cramer
								System.out.print("Masukkan banyak variabel persamaan (n): ");
								banyakPersamaan = in.nextInt();
								m = new Matrix(banyakPersamaan,banyakPersamaan+1);
								System.out.println("Masukkan persamaan linear a[1]x[1] + a[2]x[2] + ... a[n]x[n] = b");
								System.out.print("dengan format : a[1] a[2] ... a[n] b sebanyak n kali: ");
								m.inputMatrix();
								EquationSystem.CramerRule(m);
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 4 -_-");
						};
						if ((inputSubmenu > 0) && (inputSubmenu < 5)){
							break;
						};
					};
					break;
				case 2:
					while (true) {
						System.out.println("DETERMINAN");
						System.out.println("1. Metode Reduksi Baris");
						System.out.println("2. Metode Ekspansi Kofaktor");
						inputSubmenu = in.nextInt();
						m = EnhancedIO.InputSquareMatrix();
						switch(inputSubmenu) {
							case 1:
								EnhancedIO.OutputDet(Determinant.ReduksiBaris(m));
								break;
							case 2:
								EnhancedIO.OutputDet(Determinant.EkspansiKofaktor(m));
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputSubmenu > 0) && (inputSubmenu < 2)){
							break;
						};
					};
					break;
				case 3:
					while (true){
						System.out.println("MATRIKS BALIKAN");
						System.out.println("1. Metode eliminasi Gauss-Jordan");
						System.out.println("2. Metode matriks adjoin");

						inputSubmenu = in.nextInt();
						m = EnhancedIO.InputSquareMatrix();
						switch (inputSubmenu){
							case 1:
								Matrix m1;
								m1 = Inverse.GaussJordan(m);
								if(Matrix.AllBottomZero(m1)) {
									System.out.println("Matriks tersebut tidak memiliki invers.");
								} else {
									Matrix m2;
									m2 = Inverse.GetInverseGJ(m1);
									System.out.println("Invers matriks tersebut adalah:");
									m2.printMatrix();
								}
								break;
							case 2:
								// invers matriks adjoin
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputSubmenu > 0) && (inputSubmenu < 2)){
							break;
						};
					};
					break;
				case 4:
					System.out.println("INTERPOLASI POLINOM"); // nanti
					break;
				case 5:
					System.out.println("INTERPOLASI BICUBIC SPLINE"); // nanti
					break;
				case 6:
					System.out.println("REGRESI LINIER BERGANDA"); // nanti
					break;
				case 7:
					System.out.println("KELUAR"); // nanti
					break;
				default:
					System.out.println("Masukkan angka diantara 1 sampai 7 -_-");
			};
			if (inputMenu == 7){ // keluar
				break;
			};
		};
	}
}
