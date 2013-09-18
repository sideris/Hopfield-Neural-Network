package math;
/**
 * 
 *
 * @author PeterGeorge
 * Integration method from CS Princeton chapter 9.3 (http://introcs.cs.princeton.edu/java/93integration/)
 */
public class Operation {
	public static final double pi = Math.PI;
	public static final double e = Math.E;
    
	public static final double exp(double a) {return Math.exp(a);}  
	public static final double sqrt(double a) {return Math.sqrt(a);}  
	public static final double pow(double a,double b) {return Math.pow(a, b);}  


	public static double f(double x) {
		return 1/pow(e, pow(x,2));
	}
	
    public static double integrate(double a, double b, double val) {
        int N = 10000;// precision parameter
        double h = (b - a) / (N - 1);  // step size
   
        // 1/3 terms
        double sum = 1.0 / 3.0 * (f(a) + f(b));

        // 4/3 terms
        for (int i = 1; i < N - 1; i += 2) {
           double x = a + h * i;
           sum += 4.0 / 3.0 * f(x);
        }

        // 2/3 terms
        for (int i = 2; i < N - 1; i += 2) {
           double x = a + h * i;
           sum += 2.0 / 3.0 * f(x);
        }

        return sum * h;
     }
    
    
	public static double BellCurve(double x) {
		return Math.exp(- x * x / 2) / Math.sqrt(2 * Math.PI);
	}
	
	public int dot(int[] a,int[] b){
		int sum = 0;
		for(int i=0;i<a.length; i++){
			sum += a[i] * b[i];
		}
		return sum;
	}
	
	public int[][] dot(int a[][],int b[][]){
		   int n = a[0].length;
		   int m = a.length;
		   int q = b[0].length;
		   
		   int res[][] = new int[m][q];
		 
		   for(int i=0;i<m;i++){
		      for(int j=0;j<q;j++){
		         for(int k=0;k<n;k++){
		        	 res[i][j] += a[i][k] * b[k][j];
		         }
		      }
		   }
		   return res;
	}
	
	 public int[][]  transpose(int[][] a){
         int[][] res = new int[a[0].length][a.length];
         
         for(int i=0;i<a.length;i++){
                 for(int j=0;j<a[0].length;j++){
                	 res[j][i] = a[i][j];
                 }
         }
         return res;
	 }
	 
	 
	 
}
