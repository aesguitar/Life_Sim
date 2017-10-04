package utils;

import java.util.Random;

public class Utils {
	
	public static double normalRandom(double min, double max, double center, double scale)
	{
		Random r = new Random();
		double val;
		do {
			val = scale*r.nextGaussian()+center;
			//System.out.println(val);
		}while(val < min || val > max);
		
		return val;
	}
	
	public static  double expRandom(double min, double max, double lambda, double scale) {
		Random rand = new Random();
		double val;
		do {
			val =  scale * Math.log(1-rand.nextDouble())/(-lambda);
			//System.out.println(val);
		}while(val < min || val > max);
	    return val;
	}

}
