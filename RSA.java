package project_3;

import java.util.Random;

/**
 * RSA is a class that mimics the RSA cryptosystem which is used for secure
 * data transmission.
 * @author Travis Buff, Frankie Fasola, Eric Carpizo
 * 11-22-2016
 */

public class RSA
{
	public static void main(String[] args) {
		System.out.println(modPower(75,1,17));
		System.out.println(inverse(17,75));
		RSA rsa = new RSA();
		int n = 20;
//		int a = rsa.generatePrime();
//		System.out.println("A: " + a);
	}
	
	public RSA(){}
	
	/**
	 * Finds the multiplicative inverse of a long int e (mod m).
	 * @param e is the number that the method will find the inverse of.
	 * @param m is the mod.
	 * @return inverse of e mod m
	 */
	public static long inverse(long e, long m){
		//r1/r2 = q and next r2
		long r1 = m,r2 = e, swap = 0;
		long u = 0, uMin2 = 0, uMin1 = 1;
		long v = 0, vMin2 = 1, vMin1 = 0;
		long q = 0;
		while(r2 != 1){
			//gets the quotient
			q = r1/r2;
			//swap holds value r2 for use later.
			swap = r2;		
			//gets the next r2 value
			r2 = modPower(r1,1,r2);
			r1 = swap;
			
			u = uMin2 - q * uMin1;
			uMin2 = uMin1;
			uMin1 = u;
			v = vMin2 - q * vMin1;
			vMin2 = vMin1;
			vMin1 = v;	
			
			//for error checking to make sure euclidean alg is working correctly.
			if(r2 != (u*e + v*m)){
				System.err.println("The value for: "+ r2 + " has the wrong u or v");
			}
			
		}
		if(u < 0){
			u = u + m;
			return u;
		}else{
		return u;
		}
	}
	
	/**
	 * Raise a number, b, to a power , p, mod m
	 * @param b is the base number
	 * @param p is the power for the base number
	 * @param m is the mod
	 * @return the number for b^p (mod m)
	 */
	public static long modPower(long b, long p, long m){
		long result = 1;
		long base = b;
		while (p > 0) {
			if (p % 2 == 1) {
				result = (result * base) % m;
			}
			base = (base * base) % m;
			p = p/2;
		}
		return result % m;
	}
	
	/***
	 * Generates a random prime number in the range of m - n.
	 * @author Francis Fasola
	 * @param m The lower bound.
	 * @param The upper bound.
	 * @return Random prime number.
	 */
	public long randPrime(int m, int n, Random rand) {
		boolean isPrime = false;
		int number = 0;
		// nextInt takes an upper bound, so we calculate the range where our number can be
		// Then we add the lower bound to make our number in the specified range.
		int range = n - m;
		while(!isPrime) {
			number = rand.nextInt(range) + m;
			isPrime = isPrime(number);
		}
		return number;			
	}

	/***
	 * @author Francis Fasola
	 * Checks if a number is prime.
	 * @param number The number to check for primality.
	 * @return true if number is prime.
	 */
	public static boolean isPrime(int number) {
		// Cache the square root of the number,
		// then check all values of 2 - root
		double root = Math.sqrt(number);
		for(int i = 2; i < root; i++) {
			if(number % i == 0)
				return false;
		}
		return true;
	}
	
	/***
	 * @author Francis Fasola
	 * Finds a number between 2 - n-1 that is relatively prime to n.
	 * @param n The upper bound for searching.
	 * @return A number relatively prime to n.
	 */
	public long relPrime(long n, Random rand) {
		long number = 0;
		long bound = n;
		boolean isRelPrime = false;
		// Generate a random number
		// Then check if the number is relatively prime to n
		while(!isRelPrime) {
			number = (long)(rand.nextDouble() * bound);
			//System.out.println("num: " + number + " n: " + n);
			if(number > 1 && GCF(number, n) == 1) isRelPrime = true;
		}
		return number;
	}
	
	/***
	 * @author Francis Fasola
	 * Determins the greatest common factor of two numbers.
	 * @param a First number.
	 * @param b Second number.
	 * @return The greatest common factor.
	 */
	private long GCF(long a, long b) {
		long c = 0;
		while(b != 0) {
			c = a;
			a = b;
			b = c%b;
		}
		return a;
	}	
}
