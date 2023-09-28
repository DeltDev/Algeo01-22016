import java.io.*;


import LinearAlgebra.*;

import java.util.Scanner;
import Menu.*;
import java.math.*;
import SPLTuples.*;
public class Main {

	public static void main(String[] args) {
		Matrix m;
		int baris;
		int kolom;
		int banyakPersamaan;
		int banyakVariabel;
		Scanner in = new Scanner(System.in);
		int inputMenu, inputSubmenu;
		SPLTuples SPL;

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
						m = EnhancedIO.InputSPL();
						switch (inputSubmenu){
							case 1:
								// fungsi SPL Gauss
								SPL = EquationSystem.Gauss(m);
								EnhancedIO.OutputSPL(SPL);
								break;
							case 2:
								// fungsi SPL Gauss-Jordan
								SPL = EquationSystem.GaussJordan(m);
								EnhancedIO.OutputSPL(SPL);
								break;
							case 3:
								// fungsi SPL matriks balikan
								// Karena matriks invers terdefinisi hanya pada matriks persegi,
								//maka banyak persamaan linear wajib sama dengan banyak variabel
								EquationSystem.Inverse(m);
								break;
							case 4:
								// fungsi SPL Cramer
								// Karena matriks invers terdefinisi hanya pada matriks persegi,
								//maka banyak persamaan linear wajib sama dengan banyak variabel
								EquationSystem.CramerRule(m);
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 4 -_-");
						};
						if ((inputSubmenu > 0) && (inputSubmenu <= 4)){
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
						if ((inputSubmenu > 0) && (inputSubmenu <= 2)){
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
								Inverse.Adjoin(m);
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputSubmenu > 0) && (inputSubmenu <= 2)){
							break;
						};
					};
					break;
				case 4:
					
					int banyakTitik;
					System.out.println("INTERPOLASI POLINOM");
					System.out.print("Masukkan banyak titik: ");
					banyakTitik = in.nextInt();
					double [] x = new double[banyakTitik];
					double [] y = new double[banyakTitik];
					System.out.println("Masukkan titik: ");
					for(int i = 0; i<banyakTitik; i++) {
						x[i] = in.nextDouble();
						y[i] = in.nextDouble();
					}
					
					Matrix aug;
					aug = new Matrix(banyakTitik,banyakTitik+1);
					
					for(int i = 0; i<banyakTitik; i++) {
						for(int j = 0; j<banyakTitik+1; j++) {
							if(j == banyakTitik) {
								aug.Mat[i][j] = y[i];
							} else {
								aug.Mat[i][j] = Math.pow(x[i], j);
							}
						}
					}

					SPL = EquationSystem.GaussJordan(aug);
					System.out.println("Polinomial interpolasi yang didapat adalah:");
					System.out.print("f(x) = ");

					
					for(int i = 0; i<SPL.Solution.length; i++) {
						if(i != 0) {
							if(SPL.Solution[i] >=0) {
								System.out.print(" + ");
							} else {
								System.out.print(" ");
							}
						}
						EnhancedIO.OutputDoublePrecision4(SPL.Solution[i]);
						if(i !=0) {
							if(i == 1) {
								System.out.print("x");
							} else {
								System.out.print("x^");
								System.out.print(i);
							}
						}
					}
					System.out.println("");
					
					double masukan,taksiran;
					System.out.print("Masukkan nilai yang ingin ditaksir pada fungsi polinomial di atas: ");
					masukan = in.nextDouble();
					taksiran = 0.0;
					for(int i = 0; i<SPL.Solution.length; i++) {
						if(i == 0) {
							taksiran = SPL.Solution[i];
						} else {
							taksiran = taksiran + Math.pow(masukan, i) * SPL.Solution[i];
						}
					}
					System.out.print("f(");
					System.out.print(masukan);
					System.out.print(") = ");
					EnhancedIO.OutputDoublePrecision4(taksiran);
					System.out.println("");
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

	public static  double Sigma(Matrix m, int kiri, int kanan){ //copy paste this function11!11!11
		double Sum = 0;
		int i;
		if (kiri == 0){
			if (kanan == 0){Sum = m.row;} else {
				for (i = 0;i < m.row;i++){Sum += m.Mat[i][kanan-1];};
			};
		} else {
			if (kanan == 0){
				for (i = 0;i < m.row;i++){Sum += m.Mat[i][kiri-1];};
			} else {
				for (i = 0;i < m.row;i++){Sum += (m.Mat[i][kiri-1] * m.Mat[i][kanan-1]);};
			};
		};
		return Sum;
	}
}
