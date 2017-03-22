package edu.ntu.cltk.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomAlgo {

	/**
	 * Distribute candies to several baskets
	 * For example, there are 3 baskets and 6 candies, the result may return
	 * Basket 0: 1, 2
	 * Basket 1: 3, 5
	 * Basket 2: 0, 4
	 * 
	 * It cannot guarantee all baskets have at least one candy, it only ensure every candy is put into a certain basket
	 * @param basNum the number of baskets
	 * @param canNum the number of candies
	 * @return
	 */
	public static List<List<Integer>> distributeCandy(int baskNum, int canNum){
		List<List<Integer>> baskets = new ArrayList<List<Integer>>();
		for (int i = 0 ; i < baskNum; i++){
			baskets.add(new ArrayList<Integer>());
		}
		
		Random ran = new Random();
		for (int i = 0 ; i < canNum; i++){
			int baskNo = ran.nextInt(baskNum);
			baskets.get(baskNo).add(i);
		}
		
		return baskets;
	}
	
	/**
	 * Distribute candies to baskets. And it ensures every basket will have at least one candy.
	 * If the number of baskets is larger than the number of candies, it will throw an exception
	 * @param baskNum
	 * @param canNum
	 * @return
	 * @throws Exception
	 */
	public static List<List<Integer>> distributeCandyWithoutEmptyBasket(int baskNum, int canNum) throws Exception{
		if (baskNum > canNum)	throw new Exception("No. of Baskets (" + baskNum + ") is larger than No. of Candies (" + canNum + ").");
		List<List<Integer>> baskets = new ArrayList<List<Integer>>();
		for (int i = 0 ; i < baskNum; i++){
			baskets.add(new ArrayList<Integer>());
		}
		
		List<Integer> distributed = new ArrayList<Integer>();
		for (int i = 0 ; i < canNum; i++)	distributed.add(i < baskNum ? i : -1);
		Collections.shuffle(distributed);
		
		Random ran = new Random();
		for (int i = 0; i < canNum; i++){
			
			if (distributed.get(i) >= 0)		baskets.get(distributed.get(i)).add(i);
			else{
				int baskNo = ran.nextInt(baskNum);
				baskets.get(baskNo).add(i);
			}		
		}
		
		return baskets;
	}
}
