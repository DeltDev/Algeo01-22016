package LinearAlgebra;
import LinearAlgebra.*;
import SPLTuples.*;
import Menu.EnhancedIO;
public class EquationSystem {
	public static void CramerRule(Matrix m) { //tidak feasible untuk di daur ulang untuk menyelesaikan solusi parametrik
		double D;
		D = Determinant.ReduksiBaris(m);
		
		Matrix clone,konstan,reset;
		clone = new Matrix(m.row,m.row);
		reset = new Matrix(m.row,m.row);
		konstan = new Matrix(m.row,1);
		for(int i = 0; i<m.row; i++){
			for(int j = 0; j<m.row; j++){
				clone.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(int i = 0; i<m.row; i++){
			for(int j = 0; j<m.row; j++){
				reset.Mat[i][j] = m.Mat[i][j];
			}
		}
		for(int i = 0; i<m.row; i++){
			konstan.Mat[i][0] = m.Mat[i][m.col-1];
		}
		double [] Det = new double[m.row];
		double [] solution = new double[m.row];

		boolean allDetNotZero;
		allDetNotZero = true;
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.row; j++) {
				clone.Mat[j][i] = konstan.Mat[j][0];
			}
			Det[i] = Determinant.ReduksiBaris(clone);
			if(Det[i] == 0) {
				allDetNotZero = false;
			}
			for(int j = 0; j<m.row; j++){
				clone.Mat[j][i] = reset.Mat[j][i];
			}
		}			
		
		if(D == 0) {
			if(allDetNotZero) {
				System.out.println("Sistem persamaan linear berikut tidak memiliki solusi.");
				return;
			} else {
				System.out.println("Sistem persamaan linear berikut memiliki solusi banyak/parametrik.");
				return;
			}
		} else {
			System.out.println("Solusinya adalah:");
			for(int i = 0; i<m.row; i++) {
				solution[i] = Det[i]/D;
				System.out.print("x["+ (i+1) + "]= ");
				EnhancedIO.OutputDoublePrecision4(solution[i]);
				System.out.println("");
			}
			return;
		}
	}
	
	public static SPLTuples Gauss(Matrix m) {
		Matrix m1;
		m1 = new Matrix(m.row,m.col);
		m1 = Echelon.REF(m);
		
		boolean isSol;
		boolean isPara;
		double [] Solution = new double [m.row];
		for(int i = 0; i<m.row; i++) {
			Solution[i] = Double.NaN;
		}
		isSol = true;
		isPara = true;
		for(int i = m.row-1; i>=0; i--) {
			if(i == m.row-1) { //lagi di baris paling bawah
				if(!Matrix.AllRowZero(m1, i)) { //baris paling bawah tidak 0 semua
					 isPara = false; //solusinya cuma 1
					 break;
				} else { //baris paling bawah 0 semua
					if(m1.Mat[i][m.col-1] != 0) { //pojok kanan bawah bukan nol 
						isSol = false; //solusinya gak ada
						isPara = false;
						break;
					}
				} //cek terus sampai barisnya gak 0 semua
			} else { // bukan di baris paling bawah
				if(Matrix.AllRowZero(m1, i)) { //kalo baris ke-i 0 semua
					if(m1.Mat[i][m.col-1] != 0) { //kalo konstantanya bukan 0, solusinya gak ada
						isSol = false;
						isPara = false;
						break;
					}
				} else { //kalo ada yang gak nol, berhentiin iterasinya biar ketahuan parametrik apa bukan
					break;
				}
			}
		}
		
		if(isSol && !isPara) {
			Solution[m.row-1] = m1.Mat[m.row-1][m.col-1];

			for(int i = m.row-2; i>=0; i--) {
				Solution[i] = m1.Mat[i][m.col-1];
				for(int j = i+1; j<m.col-1; j++) {
					Solution[i] = Solution[i] - m1.Mat[i][j]*Solution[j];
				}
			}
		}
		SPLTuples ret = new SPLTuples(m1, Solution, isSol, isPara);
		return ret;
	}

	// prekondisi Gauss(m).isSolvable = true, Gauss(m).isParametric = true
	public static Matrix ParametricGauss(Matrix m){
		Matrix m1, Mirror;
		m1 = new Matrix(m.row, m.col);
		m1 = Echelon.REF(m);
		Mirror = new Matrix(m1.col-1, m1.col);
		boolean [] isVariable = new boolean [m1.col - 1]; // true jika variabel dijadikan parameter
		int i, j, k, l;

		//searching variabel mana yang menjadi parameter
		isVariable[0] = false;
		for (i = (m1.col - 2);i >= 1;i--){
			for (j = (m1.row-1);j >= 0;j--){
				isVariable[i] = false;
				if (m1.Mat[j][i] != 0){
					for(k = (i - 1);k >= 0;k--){
						if (m1.Mat[j][k] != 0){
							isVariable[i] = true;
							break;
						};
					};
					break;
				};
			};
		};

		// cari solusi parameter
		for (i = (m1.col - 2);i >= 0;i--){ // i loop col minus
			if (isVariable[i]){
				Mirror.Mat[i][m1.col - 1 - i] = 1;
			} else {
				// cari j
				for (j = (m1.row - 1);j >= 0;j--){ // j loop row minus
					if (m1.Mat[j][i] != 0){break;};
				};

				//cari solusi
				for (k = (m1.col - 1);k > i;k--){ // k loop col minus
					if (k == (m1.col - 1)){
						Mirror.Mat[i][0] += (m1.Mat[j][k]/m1.Mat[j][i]);
					} else {
						for (l = 0;l < Mirror.col;l++){ // l loop Mirror col plus
							Mirror.Mat[i][l] -= ((m1.Mat[j][k] * Mirror.Mat[k][l])/m1.Mat[j][i]); // very confusing
						};
					};
				};
			};
		};

		// merapikan solusi untuk siap siap diprint (kalau bisa jangan tambahin apa apa lagi di bawah ini)
		// basically hilangin kolom yang semuanya 0 dan sisanya digeser
		boolean AllColZero;
		for (i = 1;i < (Mirror.col - 1);i++){
			AllColZero = true;
			for (j = 0; j < Mirror.row; j++){
				if (Mirror.Mat[j][i] != 0){AllColZero = false; break;};
			}
			if (AllColZero){
				for (k = i; k < (Mirror.col - 1); k++){
					for (l = 0; l < Mirror.row; l++){
						Mirror.Mat[l][k] = Mirror.Mat[l][k+1];
					}
				};
				for (l = 0; l < Mirror.row; l++){
					Mirror.Mat[l][Mirror.col - 1] = 0;
				};
			};
		};
		return Mirror;
	}
	
	public static SPLTuples GaussJordan(Matrix m) {
		Matrix m1;
		m1 = new Matrix(m.row,m.col);
		m1 = Echelon.RREF(m);
		boolean isSol;
		boolean isPara;
		double [] Solution = new double [m.row];
		for(int i = 0; i<m.row; i++) {
			Solution[i] = Double.NaN;
		}
		isSol = true;
		isPara = true;
		for(int i = m.row-1; i>=0; i--) {
			if(i == m.row-1) { //lagi di baris paling bawah
				if(!Matrix.AllRowZero(m1, i)) { //baris paling bawah tidak 0 semua
					 isPara = false; //solusinya cuma 1
					 break;
				} else { //baris paling bawah 0 semua
					if(m1.Mat[i][m.col-1] != 0) { //pojok kanan bawah bukan nol 
						isSol = false; //solusinya gak ada
						isPara = false;
						break;
					}
				} //cek terus sampai barisnya gak 0 semua
			} else { // bukan di baris paling bawah
				if(Matrix.AllRowZero(m1, i)) { //kalo baris ke-i 0 semua
					if(m1.Mat[i][m.col-1] != 0) { //kalo konstantanya bukan 0, solusinya gak ada
						isSol = false;
						isPara = false;
						break;
					}
				} else { //kalo ada yang gak nol, berhentiin iterasinya biar ketahuan parametrik apa bukan
					break;
				}
			}
		}
		
		if(isSol && !isPara) {
			for(int i = 0; i<m.row; i++) {
				Solution[i] = m1.Mat[i][m.col-1];
			}
		}
		
		SPLTuples ret = new SPLTuples(m1, Solution, isSol, isPara);
		return ret;
	}

	// prekondisi GaussJordan(m).isSolvable = true, GaussJordan(m).isParametric = true
	public static Matrix ParametricGaussJordan(Matrix m){
		Matrix m1, Mirror;
		m1 = new Matrix(m.row, m.col);
		m1 = Echelon.RREF(m); // literally the only thing changed
		Mirror = new Matrix(m1.col-1, m1.col);
		boolean [] isVariable = new boolean [m1.col - 1]; // true jika variabel dijadikan parameter
		int i, j, k, l;

		//searching variabel mana yang menjadi parameter
		isVariable[0] = false;
		for (i = (m1.col - 2);i >= 1;i--){
			for (j = (m1.row-1);j >= 0;j--){
				isVariable[i] = false;
				if (m1.Mat[j][i] != 0){
					for(k = (i - 1);k >= 0;k--){
						if (m1.Mat[j][k] != 0){
							isVariable[i] = true;
							break;
						};
					};
					break;
				};
			};
		};

		// cari solusi parameter
		for (i = (m1.col - 2);i >= 0;i--){ // i loop col minus
			if (isVariable[i]){
				Mirror.Mat[i][m1.col - 1 - i] = 1;
			} else {
				// cari j
				for (j = (m1.row - 1);j >= 0;j--){ // j loop row minus
					if (m1.Mat[j][i] != 0){break;};
				};

				//cari solusi
				for (k = (m1.col - 1);k > i;k--){ // k loop col minus
					if (k == (m1.col - 1)){
						Mirror.Mat[i][0] += (m1.Mat[j][k]/m1.Mat[j][i]);
					} else {
						for (l = 0;l < Mirror.col;l++){ // l loop Mirror col plus
							Mirror.Mat[i][l] -= ((m1.Mat[j][k] * Mirror.Mat[k][l])/m1.Mat[j][i]); // very confusing
						};
					};
				};
			};
		};

		// merapikan solusi untuk siap siap diprint (kalau bisa jangan tambahin apa apa lagi di bawah ini)
		// basically hilangin kolom yang semuanya 0 dan sisanya digeser
		boolean AllColZero;
		for (i = 1;i < (Mirror.col - 1);i++){
			AllColZero = true;
			for (j = 0; j < Mirror.row; j++){
				if (Mirror.Mat[j][i] != 0){AllColZero = false; break;};
			}
			if (AllColZero){
				for (k = i; k < (Mirror.col - 1); k++){
					for (l = 0; l < Mirror.row; l++){
						Mirror.Mat[l][k] = Mirror.Mat[l][k+1];
					}
				};
				for (l = 0; l < Mirror.row; l++){
					Mirror.Mat[l][Mirror.col - 1] = 0;
				};
			};
		};
		return Mirror;
	}

	public static SPLTuples Inverse(Matrix m) { //tidak feasible untuk di daur ulang karena tidak bisa menentukan solusi parametrik
		Matrix m1,m2,konstan,solution,variabel;
		SPLTuples ret;
		boolean isSolvable,isPara;
		solution = new Matrix(m.row,1);
		double [] solutionarr = new double[m.row];
		for(int i =0; i<m.row; i++) {
			solutionarr[i] = Double.NaN;
		}
		m1 = new Matrix(m.row,2*m.row);
		variabel = new Matrix(m.row,m.row);
		for(int i = 0; i<m.row; i++) {
			for(int j = 0; j<m.row; j++) {
				variabel.Mat[i][j] = m.Mat[i][j];
			}
		}
		m1 = Inverse.GaussJordan(variabel);
		if(Matrix.AllRowZero(m1,m1.row-1)) { //beda dengan gauss/gj, kalo inverse dia gak bisa ditentuin punya solusi parametrik atau gak punya solusi
			isSolvable = false;
			isPara = false;
			ret = new SPLTuples(m1,solutionarr,isSolvable,isPara);
			return ret;
		} 
		m2 = Inverse.GetInverseGJ(m1);

		konstan = new Matrix(m.row,1);
		
		for(int i = 0; i<m.row; i++) {
			konstan.Mat[i][0] = m.Mat[i][m.col-1];
		}

		
		solution = Matrix.Multiply(m2, konstan);
		for (int i = 0; i<m.row; i++) {
			solutionarr[i] = solution.Mat[i][0];
		}
		isSolvable = true;
		isPara = false;
		ret = new SPLTuples(m1,solutionarr,isSolvable,isPara);
		return ret;
	}
}
