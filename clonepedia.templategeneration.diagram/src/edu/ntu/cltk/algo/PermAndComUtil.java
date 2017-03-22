package edu.ntu.cltk.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aggregates the common algorithms in Permutation and Combination
 * @author pillar
 *
 */
public class PermAndComUtil {

	/**
	 * Find the max number of the sequence occurring in the data <br/>
	 * For example, the data contains the following strings <br/>
	 * 		a, a, b, c, a, c, b, d, e, f, e, a, b, c, e, c, b, a <br/>
	 * To find the number of sequence a,b,c occurring in the above lists <br/>
	 * The answer is 2, because <br/>
	 * 		a, <u>a</u>, <u>b</u>, <u>c</u>, <u>a</u>, c, <u>b</u>, d, e, f, e, a, b, <u>c</u>, e, c, b, a <br/> 
	 * @param <T>
	 * @param data
	 * @param seq
	 * @return
	 */
	public static int getMaxSequence(List<?> data, List<?> seq){
		int[] max = new int[data.size()];
		int i,j,k,cnt=0;
		for (i = 0 ; i < data.size(); i++){
			for (j = 0 ; j < seq.size(); j++){
				if (data.get(i).equals(seq.get(j))){
					break;
				}
			}
			if (j >= seq.size())	continue;
			if (j == 0)		max[i] = 1;
			else{
				for (k = i-1; k >=0 && max[k] == 0;k--);
				if (k >= 0 && max[k] == j){
					max[i] = max[k] + 1; 
				}
			}
			if (max[i] == seq.size())	
				cnt++;
		}
		return cnt;
	}
	
	/**
	 * Count the number of target occurring in the data
	 * @param <T>
	 * @param data
	 * @param target
	 * @return
	 */
	public static <T> int itemCount(List<T> data, T target){
		int cnt = 0;
		for (T d : data){
			if (d.equals(target))
				cnt++;
		}
		return cnt;
	}
	
	/**
	 * Return the permutation in a specified number of the data
	 * @param <T>
	 * @param data
	 * @param num
	 * @return
	 */
	public static <T> List<List<T>> permutation(List<T> data, int num){
		if (MathUtil.factorialExceedIntegerMax(data.size(), num)){
			return null;
		}
		List<List<T>> perms = new ArrayList<List<T>>();
		CombinationGenerator<T> cg = new CombinationGenerator<T>(data, num);
		while (cg.hasNext()){
			PermutationGenerator<T> pg = new PermutationGenerator<T>(cg.next());
			while (pg.hasNext()){
				perms.add(pg.next());
			}
		}
		return perms;
	}
	/**
	 * Determine if two sequences share the same prefix. <br/>
	 * For example: <br/>
	 * List A = {a,b,c,d,e} <br/>
	 * List B = {a,b,c,d,f} <br/>
	 * Then A and B share the 4 prefix <br/> 
	 * @param a
	 * @param b
	 * @param len
	 * @return
	 */
	public static boolean sharePrefix(List<?> a, List<?> b, int len){
		if (a == null || b == null || a.size() < len || b.size() < len)	return false;
		for (int i = 0 ; i < len ; i++){
			if (!a.get(i).equals(b.get(i))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean sharePrefix(List<?> a, List<?> b, int len, MiscellaneousComparerWrapper<?> wrapper){
		if (a == null || b == null || a.size() < len || b.size() < len)	return false;
		for (int i = 0 ; i < len ; i++){
			if (!wrapper.equals(a.get(i),b.get(i))){
				return false;
			}
		}
		return true;
	}
	/**
	 * Determine if two sequences share the same suffix
	 * @param a
	 * @param b
	 * @param len
	 * @return
	 */
	public static boolean shareSuffix(List<?> a, List<?> b, int len){
		if (a == null || b == null || a.size() < len || b.size() < len)	return false;
		for (int i = 0, pointA = a.size()-1, pointB = b.size()-1 ; i < len ; i++, pointA--, pointB--){
			if (!a.get(pointA).equals(b.get(pointB))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean shareSuffix(List<?> a, List<?> b, int len, MiscellaneousComparerWrapper<?> wrapper){
		if (a == null || b == null || a.size() < len || b.size() < len)	return false;
		for (int i = 0, pointA = a.size()-1, pointB = b.size()-1 ; i < len ; i++, pointA--, pointB--){
			if (!wrapper.equals(a.get(pointA),b.get(pointB))){
				return false;
			}
		}
		return true;
	}
}
