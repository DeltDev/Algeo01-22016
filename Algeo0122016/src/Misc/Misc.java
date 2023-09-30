package Misc;

import LinearAlgebra.Matrix;
import java.math.*;
public class Misc {
	public static double Sigma(Matrix m, int kiri, int kanan){
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
	
	public static Matrix GenerateBCubicSplineMat() {
		
		Matrix ret;
		ret = new Matrix(16,16);
		
		int curRow;
		curRow = 0;
		
		for(int i = 0; i<=1; i++) {
			for(int j = 0; j<=1; j++) {
				f(i,j,ret,curRow);
				curRow++;
			}
		}
		for(int i = 0; i<=1; i++) {
			for(int j = 0; j<=1; j++) {
				fx(j,i,ret,curRow);
				curRow++;
			}
		}
		for(int i = 0; i<=1; i++) {
			for(int j = 0; j<=1; j++) {
				fy(j,i,ret,curRow);
				curRow++;
			}
		}
		for(int i = 0; i<=1; i++) {
			for(int j = 0; j<=1; j++) {
				fxy(j,i,ret,curRow);
				curRow++;
			}
		}
		return ret;
	}
	
	public static void f(double x, double y, Matrix m, int row) {
		double xNew;
		double yNew;
		int curCol;
		curCol = 0;
		for(int i = 0; i<=3; i++) {
			for(int j = 0; j<=3; j++) {
				try {
					xNew = Math.pow(x, i);
				} catch (ArithmeticException e) {
					xNew = 1;
				}
				
				try {
					yNew = Math.pow(y, j);
				} catch (ArithmeticException e) {
					yNew = 1;
				}
				m.Mat[row][curCol] = xNew * yNew;
				curCol++;
			}
		}
		return;
	}
	
	public static void fx(double x, double y, Matrix m, int row) {
		double xNew;
		double yNew;
		int curCol;
		curCol = 0;
		for(int j = 0; j<=3; j++) {
			for(int i = 0; i<=3; i++) {
				if(i == 0) {
					curCol++;
					continue;
				}
				try {
					xNew = Math.pow(x, i-1);
				} catch (ArithmeticException e) {
					xNew = 1;
				}
				
				try {
					yNew = Math.pow(y, j);
				} catch (ArithmeticException e) {
					yNew = 1;
				}
				m.Mat[row][curCol] = i * xNew * yNew;
				curCol++;
			}
		}
		return;
	}
	
	public static void fy(double x, double y, Matrix m, int row) {
		double xNew;
		double yNew;
		int curCol;
		curCol = 0;
		for(int j = 0; j<=3; j++) {				
			if(j == 0) {
				curCol+=4;
				continue;
			}
			for(int i = 0; i<=3; i++) {

				try {
					xNew = Math.pow(x, i);
				} catch (ArithmeticException e) {
					xNew = 1;
				}
				
				try {
					yNew = Math.pow(y, j-1);
				} catch (ArithmeticException e) {
					yNew = 1;
				}
				m.Mat[row][curCol] = j * xNew * yNew;
				curCol++;
			}
		}
		return;
	}
	
	public static void fxy(double x, double y, Matrix m, int row) {
		double xNew;
		double yNew;
		int curCol;
		curCol = 0;
		for(int j = 0; j<=3; j++) {
			for(int i = 0; i<=3; i++) {
				if((x == 0) && (i-1<=0)) {
					xNew = 1;
				} else {
					xNew = Math.pow(x,i-1);
				}
				
				if((y == 0) && (j-1<=0)) {
					yNew = 1;
				} else {
					yNew = Math.pow(y,j-1);
				}
				m.Mat[row][curCol] = i*j*xNew * yNew;
				curCol++;
			}
		}
		return;
	}
}
