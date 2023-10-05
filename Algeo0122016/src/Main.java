import java.io.*;


import LinearAlgebra.*;

import java.util.Scanner;
import Menu.*;
import java.math.*;
import SPLTuples.*;
import Misc.*;
import BicubicSITuple.BicubicSITuple;
import PITuple.PITuple;
import MulRegTuple.MulRegTuple;
public class Main {

	public static void main(String[] args) {
		Matrix m, Para;
		double det;
		m = new Matrix(1000, 1000); Para = new Matrix(1000, 1000);
		String sout,filename;
		int baris;
		int kolom;
		int banyakPersamaan;
		int banyakVariabel;
		Scanner in = new Scanner(System.in);
		int inputMenu, inputSubmenu, inputMethod,inputSave;
		SPLTuples SPL;
		double taksiran;
		BicubicSITuple Bicubic;
		PITuple PInterpol;
		MulRegTuple Regresi;
		Bicubic = new BicubicSITuple(new Matrix(4,4),0.0,0.0);
		PInterpol = new PITuple(0.0,new double[0], new double[0]);
		Regresi = new MulRegTuple(0,0,new Matrix(0,0), new Matrix(0,0), new double[0]);
		while (true){
			sout = "";
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
					while(true) {
						System.out.println("SISTEM PERSAMAAN LINEAR");
						System.out.println("Pilih metode untuk menginput SPL:");
						System.out.println("1. Input Keyboard");
						System.out.println("2. Input File.txt (File harus mengandung HANYA matriks augmented)");
						inputMethod = in.nextInt();
						switch(inputMethod) {
							case 1:
								m = EnhancedIO.InputSPLKeyboard();
								break;
							case 2:
								m = EnhancedIO.InputSPLFile();
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputMethod > 0) && (inputMethod <= 2)){
							break;
						};
					};
					
					while (true){
						System.out.println("Pilih metode untuk menyelesaikan SPL:");
						System.out.println("1. Metode eliminasi Gauss");
						System.out.println("2. Metode eliminasi Gauss-Jordan");
						System.out.println("3. Metode matriks balikan");
						System.out.println("4. Kaidah Cramer");
						inputSubmenu = in.nextInt();
						switch (inputSubmenu){
							case 1:
								// fungsi SPL Gauss
								SPL = EquationSystem.Gauss(m);
								EnhancedIO.OutputSPL(SPL);
								
								if (SPL.isParametric){
									Para = EquationSystem.ParametricGauss(m);
									EnhancedIO.OutputParametric(Para);
								};
								EnhancedIO.OutputSPLFile(SPL, Para, false);
								break;
							case 2:
								// fungsi SPL Gauss-Jordan
								SPL = EquationSystem.GaussJordan(m);
								EnhancedIO.OutputSPL(SPL);
								
								if (SPL.isParametric){
									Para = EquationSystem.ParametricGaussJordan(m);
									EnhancedIO.OutputParametric(Para);
								};
								EnhancedIO.OutputSPLFile(SPL, Para, false);
								break;
							case 3:
								// fungsi SPL matriks balikan
								
								SPL = EquationSystem.Inverse(m);
								EnhancedIO.OutputSPLInverse(SPL);
								EnhancedIO.OutputSPLFile(SPL, Para, true);
								break;
							case 4:
								// fungsi SPL Cramer
								//Sebenarnya fungsi cramer hanya bisa menentukan apakah solusinya unik, parametrik, atau tidak ada solusi saja, namun tidak dapat mengoutputkan solusi parametrik secara langsung
								//Diasumsikan masih diminta solusi parametrik jika cramer's rule menentukan apakah SPL parametrik atau tidak, ulangi langkah gauss-jordan saja
								SPL = EquationSystem.CramerRule(m);
								EnhancedIO.OutputSPL(SPL);
								
								if(SPL.isParametric) {
									SPL = EquationSystem.GaussJordan(m);
									Para = EquationSystem.ParametricGaussJordan(m);
									EnhancedIO.OutputParametric(Para);
								}
								EnhancedIO.OutputSPLFile(SPL, Para, false);
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
					while(true) {
						System.out.println("DETERMINAN");
						System.out.println("Pilih metode untuk menginput matriks yang akan dicari determinannya:");
						System.out.println("1. Input Keyboard");
						System.out.println("2. Input File.txt (File harus mengandung HANYA matriks persegi)");
						inputMethod = in.nextInt();
						switch(inputMethod) {
							case 1:
								m = EnhancedIO.InputSquareMatrixKeyboard();
								break;
							case 2:
								m = EnhancedIO.InputSquareMatrixFile();
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputMethod > 0) && (inputMethod <= 2)){
							break;
						};
					};
					while (true) {
						System.out.println("Pilih metode untuk menghitung determinan matriks:");
						System.out.println("1. Metode Reduksi Baris");
						System.out.println("2. Metode Ekspansi Kofaktor");
						inputSubmenu = in.nextInt();
						switch(inputSubmenu) {
							case 1:
								det = Determinant.ReduksiBaris(m);
								EnhancedIO.OutputDet(det);
								EnhancedIO.OutputDetFile(det);
								break;
							case 2:
								det = Determinant.EkspansiKofaktor(m);
								EnhancedIO.OutputDet(det);
								EnhancedIO.OutputDetFile(det);
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
					while(true) {
						System.out.println("MATRIKS BALIKAN");
						System.out.println("Pilih metode untuk menginput matriks yang akan dicari inversnya:");
						System.out.println("1. Input Keyboard");
						System.out.println("2. Input File.txt (File harus mengandung HANYA matriks persegi)");
						inputMethod = in.nextInt();
						switch(inputMethod) {
							case 1:
								m = EnhancedIO.InputSquareMatrixKeyboard();
								break;
							case 2:
								m = EnhancedIO.InputSquareMatrixFile();
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputMethod > 0) && (inputMethod <= 2)){
							break;
						};
					};
					while (true){
						System.out.println("Pilih metode untuk mencari matriks balikan:");
						System.out.println("1. Metode eliminasi Gauss-Jordan");
						System.out.println("2. Metode matriks adjoin");

						inputSubmenu = in.nextInt();

						switch (inputSubmenu){
							case 1:
								Matrix m1;
								m1 = Inverse.GaussJordan(m);
								if(Matrix.AllRowZero(m1,m1.row-1)) {
									System.out.println("Matriks tersebut tidak memiliki invers.");
									EnhancedIO.OutputNoInverseFile();
								} else {
									Matrix m2;
									m2 = Inverse.GetInverseGJ(m1);
									System.out.println("Invers matriks tersebut adalah:");
									m2.printMatrix();
									EnhancedIO.OutputInverseMatrixFile(m2);
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
					System.out.println("INTERPOLASI POLINOM");
					while(true) {
						System.out.println("Pilih metode untuk menginput titik dan nilai yang akan ditaksir:");
						System.out.println("1. Input Keyboard");
						System.out.println("2. Input File.txt (File harus mengandung n buah titik (nilai x dan y) dan 1 angka di bawah n buah titik)");
						inputMethod = in.nextInt();
						switch(inputMethod) {
							case 1:
								PInterpol = EnhancedIO.InputPIKeyboard();
								break;
							case 2:
								PInterpol = EnhancedIO.InputPIFile();
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputMethod > 0) && (inputMethod <= 2)){
							break;
						};
					};
					Matrix aug;
					int banyakTitik = PInterpol.xValues.length;
					aug = new Matrix(banyakTitik,banyakTitik+1);
					
					for(int i = 0; i<banyakTitik; i++) {
						for(int j = 0; j<banyakTitik+1; j++) {
							if(j == banyakTitik) {
								aug.Mat[i][j] = PInterpol.yValues[i];
							} else {
								aug.Mat[i][j] = Math.pow(PInterpol.xValues[i], j);
							}
						}
					}

					
					SPL = EquationSystem.GaussJordan(aug);
					System.out.println("Polinomial interpolasi yang didapat adalah:");
					System.out.print("f(x) = ");

					EnhancedIO.OutputFunction(SPL,false);
					System.out.println("");
					taksiran = 0.0;
					for(int i = 0; i<SPL.Solution.length; i++) {
						if(i == 0) {
							taksiran = SPL.Solution[i];
						} else {
							taksiran = taksiran + Math.pow(PInterpol.taksir, i) * SPL.Solution[i];
						}
					}
					System.out.println("Taksiran nilai f(x) yang diinput adalah:");
					System.out.print("f(");
					EnhancedIO.OutputDoublePrecision4(PInterpol.taksir);
					System.out.print(") = ");
					EnhancedIO.OutputDoublePrecision4(taksiran);
					System.out.println("");
					EnhancedIO.OutputPIFile(SPL, PInterpol.taksir, taksiran);
					break;
				case 5:
					while(true) {
						System.out.println("INTERPOLASI BICUBIC SPLINE");
						System.out.println("Pilih metode untuk menginput nilai Bicubic Spline Interpolation:");
						System.out.println("1. Input Keyboard");
						System.out.println("2. Input File.txt (File harus mengandung matriks 4x4 dan 2 angka di bawah matriks tersebut)");
						inputMethod = in.nextInt();
						switch(inputMethod) {
							case 1:
								Bicubic = EnhancedIO.InputBicubicKeyboard();
								break;
							case 2:
								Bicubic = EnhancedIO.InputBicubicFile();
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputMethod > 0) && (inputMethod <= 2)){
							break;
						};
					};
					
					Matrix MatF = new Matrix(4,4);
					Matrix X = new Matrix(16,16);
					Matrix MatFCol = new Matrix(16,1);
					Matrix augment = new Matrix(16,17);
					Matrix a = new Matrix(4,4);
					X = Misc.GenerateBCubicSplineMat();
					double xTaksir,yTaksir;
					double outTaksir;
					
					
					MatF = Bicubic.Mat;
					
					MatFCol = Matrix.toColMatrix(MatF);
					augment = Matrix.Augment(X, MatFCol);
					SPL = EquationSystem.Inverse(augment);
					
					xTaksir = Bicubic.xTaksir;
					yTaksir = Bicubic.yTaksir;
					int idxsol = 0;
					Matrix A = new Matrix(4,4);
					for(int j = 0; j<4; j++) {
						for(int i = 0; i<4; i++) {
							A.Mat[i][j] = SPL.Solution[idxsol];
							idxsol++;
						}
					}
					outTaksir = 0;
					for(int i = 0; i<4; i++) {
						for(int j = 0; j<4; j++) {
							outTaksir = outTaksir + A.Mat[i][j] * Math.pow(xTaksir, i) * Math.pow(yTaksir, j);
						}
					}
					System.out.println("Taksiran nilai f(x,y) yang diinput adalah:");
					System.out.print("f(");
					EnhancedIO.OutputDoublePrecision4(xTaksir);
					System.out.print(", ");
					EnhancedIO.OutputDoublePrecision4(yTaksir);
					System.out.print(") = ");
					EnhancedIO.OutputDoublePrecision4(outTaksir);
					System.out.println("");
					EnhancedIO.OutputBSIFile(xTaksir, yTaksir, outTaksir);
					break;
				case 6:
					int i, j;
					
					
					while(true) {
						System.out.println("REGRESI LINIER BERGANDA"); 
						System.out.println("Pilih metode untuk menginput sampel untuk diregresi:");
						System.out.println("1. Input Keyboard");
						System.out.println("2. Input File.txt (File harus mengandung matriks augmented berukuran (k+1) x n dan k angka di barisan terbawah)");
						inputMethod = in.nextInt();
						switch(inputMethod) {
							case 1:
								Regresi = EnhancedIO.InputRegKeyboard();
								break;
							case 2:
								Regresi = EnhancedIO.InputRegFile();
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 2 -_-");
						};
						if ((inputMethod > 0) && (inputMethod <= 2)){
							break;
						};
					};
					SPL = EquationSystem.GaussJordan(Regresi.NEE);
					taksiran = 0.0;
					for(i = 0; i<SPL.Solution.length; i++) {
						if(i == 0) {
							taksiran = SPL.Solution[i];
						} else {
							taksiran = taksiran + Regresi.xtest[i-1] * SPL.Solution[i];
						}
					}
					System.out.println("Persamaan regresi linear yang didapat adalah:");
					System.out.print("y = ");
					EnhancedIO.OutputFunction(SPL,true);
					System.out.println("Nilai taksiran f(xk) yang didapat adalah:");
					System.out.print("f(");
					for(i = 0; i<Regresi.variables; i++) {
						if(i == Regresi.variables-1) {
							EnhancedIO.OutputDoublePrecision4(Regresi.xtest[i]);
							System.out.print(") = ");
						} else {
							EnhancedIO.OutputDoublePrecision4(Regresi.xtest[i]);
							System.out.print(", ");
						}
					}
					EnhancedIO.OutputDoublePrecision4(taksiran);
					System.out.println("");
					
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
