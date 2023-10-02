package Menu;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import LinearAlgebra.*;
import Misc.Misc;

import java.util.Scanner;
import SPLTuples.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import BicubicSITuple.BicubicSITuple;
import PITuple.PITuple;
import MulRegTuple.MulRegTuple;
public class EnhancedIO { //Class ini untuk input lewat keyboard dan output secara umum
	public static Matrix InputSquareMatrixKeyboard() {
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
	
	public static Matrix InputSPLKeyboard() {
		int banyakPersamaan,banyakVariabel;
		Matrix m;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan banyak persamaan: ");
		banyakPersamaan = in.nextInt();
		System.out.print("Masukkan banyak variabel peubah (n): ");
		banyakVariabel = in.nextInt();
		m = new Matrix(banyakVariabel,banyakVariabel+1);
		System.out.println("Masukkan persamaan linear");
		System.out.println("dalam bentuk matriks augmented");
		m.inputMatrix(banyakPersamaan,banyakVariabel+1);
		return m;
	}

	public static Matrix InputSPLFile() {
		Matrix m;
		m = new Matrix(0,0);
		String filedir = findFileDir();
		File file = new File(filedir);
		Scanner scanFile;
		//1. hitung banyak baris dan kolom dulu
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputSPLFile();
		}
		int col,row;
		col = 0;
		row = 0;

		
		col = scanFile.nextLine().split(" ").length;
		row++;
		
		//flush sisa text
		while(scanFile.hasNextLine()) {
			String flush = scanFile.nextLine();
			row++;
		}
		
		if(row<col) {
			m = new Matrix(col-1,col);
		} else {
			m = new Matrix(row,col);
		}

		scanFile.close();
		
		//2. Parsing
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputSPLFile();
		}
		
		
		for(int i = 0; i<row; i++) {
			String [] line = scanFile.nextLine().trim().split(" ");
			for(int j = 0; j<line.length; j++) {
				m.Mat[i][j] = Double.parseDouble(line[j]);
			}
		}
		
		scanFile.close();
		return m;
	}

	public static Matrix InputSquareMatrixFile() {
		Matrix m;
		m = new Matrix(0,0);
		String filedir = findFileDir();
		File file = new File(filedir);
		Scanner scanFile;
		//1. hitung banyak baris dan kolom dulu
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputSPLFile();
		}
		int n;
		n = 0;
		

		
		n = scanFile.nextLine().split(" ").length;
		
		//flush sisa text
		while(scanFile.hasNextLine()) {
			String flush = scanFile.nextLine();
		}
		
		m = new Matrix(n,n);
		scanFile.close();
		
		//2. Parsing
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputSPLFile();
		}
		
		
		for(int i = 0; i<n; i++) {
			String [] line = scanFile.nextLine().trim().split(" ");
			for(int j = 0; j<line.length; j++) {
				m.Mat[i][j] = Double.parseDouble(line[j]);
			}
		}
		
		scanFile.close();
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
					System.out.print("x["+ (i+1) +"] = ");
					EnhancedIO.OutputDoublePrecision4(SPL.Solution[i]);
					System.out.println("");
				}
			}
		} else {
			System.out.println("Solusinya tidak ada");
		}
	}

	public static void OutputParametric(Matrix m){
		int i, j;
		boolean notfirst;
		for (i = 0; i < m.row; i++){
			System.out.print("x["+ (i+1) +"] = ");
			notfirst = false;
			for (j = 0; j < m.col; j++){
				if (m.Mat[i][j] != 0){
					if (notfirst){
						if (m.Mat[i][j] > 0){System.out.print("+");};
					};
					notfirst = true;
					if (j == 0){
						EnhancedIO.OutputDoublePrecision4(m.Mat[i][j]);
					} else if (m.Mat[i][j] == -1){
						System.out.print("-");
					} else if (m.Mat[i][j] != 1){EnhancedIO.OutputDoublePrecision4(m.Mat[i][j]);};
					if (j != 0){
						System.out.print("t[" + (j) + "]");
					};
				};
			};
			System.out.println("");
		};
	}

	public static void OutputSPLInverse(SPLTuples SPL) {
		if(SPL.isSolvable) {
			System.out.println("Solusinya adalah: ");
				
			for(int i = 0; i<SPL.Solution.length; i++) {
				System.out.print("x["+ (i+1) +"] = ");
				EnhancedIO.OutputDoublePrecision4(SPL.Solution[i]);
				System.out.println("");
			}
			
		} else {
			System.out.println("Solusinya mungkin tidak ada atau mungkin parametrik.");
		}
	}

	public static void OutputFunction(SPLTuples SPL, boolean regressionfunction) {
		int i;
		for(i = 0; i<SPL.Solution.length; i++) {
			
			
			if(Math.abs(SPL.Solution[i]) >= 0.0001) { //cek koefisien yang dianggap sangat kecil sehingga dapat diabaikan
				if(i != 0) {
					if(SPL.Solution[i] > 0) {
						System.out.print(" + ");
					} else {
						System.out.print(" ");
					}
				}
				EnhancedIO.OutputDoublePrecision4(SPL.Solution[i]);
				if(regressionfunction) {
					if(i != 0) {
						System.out.print("x");
						System.out.print("["+i+"]");
						if(i != 1) {
							System.out.print("^");
							System.out.print(i);
						}
					}
				} else {
					if(i != 0) {
						System.out.print("x");
						if(i != 1) {
							System.out.print("^");
							System.out.print(i);
						}
					}
				}
			}
		}
		
		if(regressionfunction) {
			System.out.print("+ ei");
		}
		System.out.println("");
	}
	
	private static String findFileDir()
	{
		Path currentRelativePath = Paths.get("");
		String curdir = currentRelativePath.toAbsolutePath().toString();
		String filedir = curdir.replace("bin", "test");

		String fileInput;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan nama file: ");
		fileInput = in.next();
		filedir = filedir + '\\' + fileInput;
		return filedir;
	}
	
	public static BicubicSITuple InputBicubicKeyboard() {
		BicubicSITuple ret;
		System.out.println("Masukkan matriks 4x4 dengan format:");
		System.out.println("f(0,0) f(1,0) f(0,1) f(1,1)");
		System.out.println("fx(0,0) fx(1,0) fx(0,1) fx(1,1)");
		System.out.println("fy(0,0) fy(1,0) fy(0,1) fy(1,1)");
		System.out.println("fxy(0,0) fxy(1,0) fxy(0,1) fxy(1,1)");
		Matrix m = new Matrix(4,4);
		m.inputMatrix(4, 4);
		System.out.println("Masukkan nilai x dan y yang ingin ditaksir: ");
		double x,y;
		Scanner in;
		in = new Scanner(System.in);
		x = in.nextDouble();
		y = in.nextDouble();
		ret = new BicubicSITuple(m,x,y);
		return ret;
	}
	
	public static BicubicSITuple InputBicubicFile() {
		BicubicSITuple ret;
		ret = new BicubicSITuple(new Matrix(4,4),0.0,0.0);
		Matrix m;
		double xT,yT;
		m = new Matrix (4,4);
		String filedir = findFileDir();
		File file = new File(filedir);
		Scanner scanFile;
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputBicubicFile();
		}
		
		
		xT = 0.0;
		yT = 0.0;
		//parsing

		for(int i = 0; i<4; i++) {
			String [] line = scanFile.nextLine().trim().split(" ");
			for(int j = 0; j<line.length; j++) {
				m.Mat[i][j] = Double.parseDouble(line[j]);
			}
		}
			
				
		String [] line = scanFile.nextLine().trim().split(" ");
		for(int j = 0; j<line.length; j++) {
			if(j == 0) {
				xT = Double.parseDouble(line[j]);
			} else {
				yT = Double.parseDouble(line[j]);
			}
		}
			
		scanFile.close();
		ret = new BicubicSITuple(m,xT,yT);
		return ret;
	}
	
	public static PITuple InputPIKeyboard() {
		PITuple ret;
		int banyakTitik;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan banyak titik: ");
		banyakTitik = in.nextInt();
		
		double [] x = new double[banyakTitik];
		double [] y = new double[banyakTitik];
		System.out.println("Masukkan titik: ");
		for(int i = 0; i<banyakTitik; i++) {
			x[i] = in.nextDouble();
			y[i] = in.nextDouble();
		}
		System.out.print("Masukkan nilai yang ingin ditaksir pada fungsi polinomial yang akan dicari: ");
		double masukan;
		masukan = in.nextDouble();
		ret = new PITuple(masukan,x,y);
		return ret;
	}
	
	public static PITuple InputPIFile() {
		PITuple ret;
		ret = new PITuple(0.0,new double[0],new double[0]);
		String filedir = findFileDir();
		File file = new File(filedir);
		Scanner scanFile;
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputPIFile();
		}
		//1. hitung banyak titik
		int rowcnt;
		rowcnt = 0;
		while(scanFile.hasNextLine()) {
			if(scanFile.nextLine().split(" ").length == 2) {
				rowcnt++;
			}
		}
		scanFile.close();
		double [] xRet = new double[rowcnt];
		double [] yRet = new double[rowcnt];
		double taks;
		taks = 0.0;
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputPIFile();
		}
		
		for(int i = 0; i<rowcnt; i++) {
			String [] line = scanFile.nextLine().trim().split(" ");
			for(int j = 0; j<2; j++) {
				if(j == 0) {
					xRet[i] = Double.parseDouble(line[j]);
				} else {
					yRet[i] = Double.parseDouble(line[j]);
				}
			}
		}
			
				
		String [] line = scanFile.nextLine().trim().split(" ");
		taks = Double.parseDouble(line[0]);
		scanFile.close();
		ret = new PITuple(taks,xRet,yRet);
		return ret;
	}
	
	public static MulRegTuple InputRegKeyboard() {
		MulRegTuple ret;
		ret = new MulRegTuple(0,0,new Matrix(0,0), new Matrix(0,0), new double[0]);
		int n, k;
		Scanner in = new Scanner(System.in);
		System.out.print("Masukkan banyak variabel peubah: ");
		k = in.nextInt();
		System.out.print("Masukkan banyak sampel: ");
		n = in.nextInt();
		Matrix inputNK = new Matrix (n, (k+1));
		Matrix NEE = new Matrix ((k+1), (k+2));
		System.out.println("Masukkan dengan format");
		System.out.println("x[1][1] x[2][1] ... x[k][1] y[1]");
		System.out.println("x[1][2] x[2][2] ... x[k][2] y[2]");
		System.out.println("   :       :     :     :     :  ");
		System.out.println("x[1][n] x[2][n] ... x[k][n] y[n]");
		inputNK.inputMatrix(n, (k+1));
		for (int i = 0;i < (k+1);i++){ // i loop row
			for (int j = 0;j < (k+2);j++){ // j loop col
				NEE.Mat[i][j] = Misc.Sigma(inputNK, i, j);
			};
		};
		//input keyboard MulRegTuple: k,n,inputNK,NEE,xTest
		
		double [] xTest = new double[k];
		System.out.println("Masukkan sampel baru yang ingin ditaksir: ");
		for(int i = 0; i<k; i++) {
			xTest[i] = in.nextDouble();
		}
		ret = new MulRegTuple(k,n,inputNK,NEE,xTest);
		return ret;
	}
	
	public static MulRegTuple InputRegFile() {
		MulRegTuple ret;
		ret = new MulRegTuple(0,0,new Matrix(0,0), new Matrix(0,0), new double[0]);
		String filedir = findFileDir();
		File file = new File(filedir);
		Scanner scanFile;
		//1. hitung banyak variabel dan banyak sampel
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputRegFile();
		}
		
		int k,n;
		n = 0;
		k = scanFile.nextLine().split(" ").length-1;
		n++;
		while(scanFile.hasNextLine()) {
			if(scanFile.nextLine().split(" ").length == (k+1)) {
				n++;
			}
		}
		
		scanFile.close();
		Matrix NK = new Matrix(n,(k+1));
		Matrix NEE = new Matrix((k+1),(k+2));
		double [] xTest = new double[k];
		//2. parsing
		try {
			scanFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan!");
			return InputRegFile();
		}
		for(int i = 0; i<n; i++) {
			String [] line = scanFile.nextLine().trim().split(" ");
			for(int j = 0; j<line.length; j++) {
				NK.Mat[i][j] = Double.parseDouble(line[j]);
			}
		}
		String [] line = scanFile.nextLine().trim().split(" ");
		for(int i = 0; i<k; i++) {
			xTest[i] = Double.parseDouble(line[i]);
		}
		for (int i = 0;i < (k+1);i++){ // i loop row
			for (int j = 0;j < (k+2);j++){ // j loop col
				NEE.Mat[i][j] = Misc.Sigma(NK, i, j);
			};
		};
		ret = new MulRegTuple(k,n,NK,NEE,xTest);
		scanFile.close();
		return ret;
	}
}
