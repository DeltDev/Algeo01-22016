package BicubicSITuple;

import LinearAlgebra.*;
public class BicubicSITuple {
	public Matrix Mat;
	public double xTaksir;
	public double yTaksir;
	
	public BicubicSITuple (Matrix m, double x, double y){
		this.Mat = m;
		this.xTaksir = x;
		this.yTaksir = y;
	}
}
