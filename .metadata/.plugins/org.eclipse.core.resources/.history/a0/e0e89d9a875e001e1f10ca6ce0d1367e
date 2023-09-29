package Misc;

import LinearAlgebra.Matrix;

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
}
