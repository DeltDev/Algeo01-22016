package MulRegTuple;

import LinearAlgebra.*;
public class MulRegTuple {
	public int variables;
	public int samples;
	public Matrix inputMat;
	public Matrix NEE;
	public double [] xtest;
	
	public MulRegTuple(int k, int n, Matrix iM, Matrix nee, double [] xT) {
		this.variables = k;
		this.samples = n;
		this.inputMat = iM;
		this.NEE = nee;
		this.xtest = xT;
	}
}
