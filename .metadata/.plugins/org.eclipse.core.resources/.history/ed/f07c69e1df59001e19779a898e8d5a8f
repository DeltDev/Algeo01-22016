import java.io.*;

import LinearAlgebra.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
		Matrix m;
		int baris;
		int kolom;
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
						System.out.println("1. Metode Matriks Segitiga Atas");
						System.out.println("2. Metode Ekspansi Kofaktor");
						inputSubmenu = in.nextInt();
						switch(inputSubmenu) {
							case 1:
								System.out.print("Masukkan banyak baris matriks: ");
								baris = in.nextInt();
								System.out.print("Masukkan banyak kolom matriks: ");
								kolom = in.nextInt();
								m = new Matrix(baris,kolom);
								System.out.println("Input isi matriks:");
								m.inputMatrix();
								System.out.print("Determinan dari matriks tersebut adalah: ");
								System.out.println(Matrix.DeterminanSegitigaAtas(m));
								break;
							case 2:
								System.out.print("Masukkan banyak baris matriks: ");
								baris = in.nextInt();
								System.out.print("Masukkan banyak kolom matriks: ");
								kolom = in.nextInt();
								m = new Matrix(baris,kolom);
								System.out.println("Input isi matriks:");
								m.inputMatrix();
								System.out.print("Determinan dari matriks tersebut adalah: ");
								System.out.println(Matrix.DeterminanKofaktor(m));
								break;
							default:
								System.out.println("Masukkan angka diantara 1 sampai 4 -_-");
						};
						if ((inputSubmenu > 0) && (inputSubmenu < 2)){
							break;
						};
					};
					break;
				case 3:
					System.out.println("MATRIKS BALIKAN"); // nanti
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
