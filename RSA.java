package v1;

import java.util.Random;

public class RSA
{
	public static void main(String[] args) {
		RSA rsa = new RSA();
		int n = 20;
//		int a = rsa.generatePrime();
//		System.out.println("A: " + a);
	}
	
	/***
	 * Generates a random prime number in the range of m - n.
	 * @author Francis Fasola
	 * @param m The lower bound.
	 * @param The upper bound.
	 * @return Random prime number.
	 */
	static long randPrime(int m, int n, Random rand) {
		boolean isPrime = false;
		int number = 0;
		// nextInt takes an upper bound, so we calculate the range where our numbr can be
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
		while(!isRelPrime) {
			number = (long)(rand.nextDouble() * bound);
			System.out.println("num: " + number + " n: " + n);
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
