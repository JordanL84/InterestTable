package model;

public class Interest {
	
	public static double calculateSimpleInterest(double prin, double rate, int year) {
		return prin + (prin * (rate/100) * year);
	}
	
	public static double calculateCompundInterest(double prin, double rate, int year) {
		return prin * Math.pow(1+rate/100, year);
	}
}
