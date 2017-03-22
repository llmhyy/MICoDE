package edu.ntu.cltk.algo;

import java.math.BigInteger;

public class MathUtil {

	/**
	 * Calculate the factorial
	 * @param num
	 * @return
	 */
	public static BigInteger factorial(int num){
		return factorial(num, num);
	}
	
	public static BigInteger factorial(int num, int cnt){
		BigInteger res = new BigInteger("1");
		for (int i = 0; i < cnt; i++){
			res.multiply(new BigInteger(num-i+""));
		}
		return res;
	}
	/**
	 * Determine if the factorial exceeds the maximal value of Integer
	 * @param num
	 * @param cnt
	 * @return
	 */
	public static boolean factorialExceedIntegerMax(int num, int cnt){
		BigInteger com = factorial(num, cnt);
		if (com.compareTo(new BigInteger(Integer.MAX_VALUE + "")) == 1){
			return true;
		}
		return false;
	}
	
	/**
	 * Get the variance for the list of the numbers
	 * @param nums
	 * @return
	 */
	public static double variance(double[] nums){
		double sum = 0;
		for (int i = 0 ; i < nums.length;i++){
			sum += nums[i];
		}
		double avg = sum / nums.length;
		double res = 0;
		for (int i = 0 ; i < nums.length; i++){
			res += (nums[i] - avg) * (nums[i] - avg);
		}
		return res;
	}
	
	/**
	 * Calculate the deviation for the list of the numbers
	 * @param nums
	 * @return
	 */
	public static double deviation(double[] nums){
		return Math.sqrt(variance(nums));
	}
}
