import java.util.Arrays;
import math.Erf;

public class Hopfield {
	
	private double[][] weights, patterns;
	private double[] state;
	private double P, N;
	private double beta;
	public double[] m1;
	//Constructor
	public Hopfield(int netSize, int numberOfBits){
		weights = new double[numberOfBits][numberOfBits];
		P = netSize;
		N = numberOfBits;
		patterns = new double[(int) P][(int) N];
		state = new double[(int) N];
	}
	
	public Hopfield(int netSize, int numberOfBits, double noise){
		weights = new double[numberOfBits][numberOfBits];
		P = netSize;
		N = numberOfBits;
		patterns = new double[(int) P][(int) N];
		state = new double[(int) N];
		m1 = new double[(int) N];
		beta = noise;
	}
	
	public double[] initialState(){
		int rand  =(int)(Math.random()*patterns.length); 
		for(int i=0;i<N;i++){
			state[i] = patterns[rand][i];
		}
		return state;
	}
	
	public void doStuff(){
		generatePatterns();
		setWeights();
	}
	
	public void doStuff2(){
		generatePatterns();
		setWeights2();
	}
	
	public double[] calculateState(double[] oldState){
		double[] newState = new double[(int) N];
		double sum = 0;
		Arrays.fill(newState,0);

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				sum += weights[i][j]*oldState[j];
			}
			newState[i] = Math.signum(sum);
			if( newState[i] == 0 ) newState[i] = 1;
			sum = 0;
		}
		return newState;
	}
	
	public double[] calculateStochasticState(double[] oldState){
		double[] newState = new double[(int) N];
		double sum = 0;
		double g;
		Arrays.fill(newState,0);

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				sum += weights[i][j]*oldState[j];
			}
			g = 1/(Math.pow(2,-2*sum*beta)+1);
			m1[i] = Math.tanh(beta*sum);
			newState[i] = (((double)(Math.random()*100)/100)>=g)?1:-1;
			sum = 0;
		}
		return newState;	
	}
	
	public double[][] getStates(){
		return patterns;
	}
	
	private void generatePatterns(){
		double[] tempPattern = new double[(int) N];
		for(int i=0;i<P;i++){
			tempPattern = this.generateRandom();
			for(int j=0;j<N;j++){
				patterns[i][j] = tempPattern[j];
			}
		}
	}
	
	public double calculateError(){
		double a = P/N;
		return 0.5*(1-Erf.erf(1/Math.sqrt(2*a)));
	}
	
	private void setWeights(){
		//first we have to put 0 Array weights, so we can sum up
		Arrays.fill(weights[0],0);

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				for(int k=0;k<P;k++){
					if( i==j ){	weights[i][j] = 0;}
					else{		weights[i][j] += (1/N)*patterns[k][i]*patterns[k][j];}
				}
			}
		}
	}
	
	private void setWeights2(){
		//first we have to put 0 Array weights, so we can sum up
		Arrays.fill(weights[0],0);

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				for(int k=0;k<P;k++){
					weights[i][j] += (1/N)*patterns[k][i]*patterns[k][j];
				}
			}
		}
	}
	
	private double[] generateRandom(){
		double[] randomPattern = new double[(int) N];
		for(int i=0;i<N;i++){
			randomPattern[i] = (Math.random()<0.5)?-1:1;
		}
		return randomPattern;
	}
	
}
