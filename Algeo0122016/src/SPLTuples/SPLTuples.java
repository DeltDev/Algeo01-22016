package SPLTuples;
import LinearAlgebra.*;
public class SPLTuples {
	public Matrix Mat;
	public double [] Solution;
	public boolean isSolvable;
	public boolean isParametric;
	
	public SPLTuples (Matrix m, double [] sol, boolean solvable, boolean para){
		this.Mat = m;
		this.Solution = sol;
		this.isSolvable = solvable;
		this.isParametric = para;
	}
}
