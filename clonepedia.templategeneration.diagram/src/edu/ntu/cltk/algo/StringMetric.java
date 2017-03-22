package edu.ntu.cltk.algo;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringMetric {

	public static final int HAMMING_DISTANCE = 1;

	// a.k.a Edit distance
	public static final int LEVENSHTEIN_DISTANCE = 2;

	// a.k.a Sellers' Algorithm
	public static final int NEEDLEMAN_WUNSCH = 3;

	public static final int SMITH_WATERMAN = 4;

	public static final int BLOCK_DISTANCE = 5;

	public static final int JARO_WINKLER_DISTANCE = 6;

	public static final int SOUDEX_DISTANCE = 7;

	public static final int SIMPLE_MATCHING_COEFFICIENT = 8;

	public static final int DICES_COEFFICIENT = 9;

	public static final int JACCARD_SIMILARITY = 10;

	public static final int TVERSKY_INDEX = 11;

	public static final int OVERLAP_COEFFICIENT = 12;

	public static final int EUCLIDEAN_DISTANCE = 13;

	public static final int COSINE_SIMILARITY = 14;

	public static final int VARIATIONAL_DISTANCE = 15;

	public static final int HELLINGER_DISTANCE = 16;

	public static final int INFORMATION_RADIUS = 17;

	public static final int HARMONIC_MEAN = 18;

	public static final int SKEW_DIVERGENCE = 19;

	public static final int CONFUSION_PROBABILITY = 20;

	public static final int TAU_METRIC = 21;

	public static final int FELLEGI_SUNTERS_METRIC = 22;

	public static final int TFIDF = 23;

	public static final int MAXIMAL_MATCHES = 24;

	public static final int LEE_DISTANCE = 25;

	// algorithm for similarity calculation
	private int algo;
	private static StringMetric instance = null;

	private StringMetric(int algo) {
		this.algo = algo;
	}

	public static synchronized StringMetric getInstance(int algo) {
		if (instance == null)
			instance = new StringMetric(algo);
		return instance;
	}

	public double similarity(String str1, String str2) {
		switch (algo) {
		case StringMetric.JACCARD_SIMILARITY:
			return jaccardIndex(str1, str2);
		case StringMetric.LEVENSHTEIN_DISTANCE:
			return levenshteinDistance(str1, str2);
		case StringMetric.COSINE_SIMILARITY:
			return cosineSimilarity(str1, str2);
		default:
			return 0;
		}
	}

	public double similarity(String[] sar1, String[] sar2) {
		switch (algo) {
		case StringMetric.JACCARD_SIMILARITY:
			return jaccardIndex(sar1, sar2);
		case StringMetric.EUCLIDEAN_DISTANCE:
			return euclideanDistance(sar1, sar2);
		default:
			return 0;
		}
	}

	/**
	 * http://en.wikipedia.org/wiki/Cosine_similarity Cosine similarity is a
	 * measure of similarity between two vectors of an inner product space that
	 * measures the cosine of the angle between them. The cosine of 0° is 1, and
	 * it is less than 1 for any other angle. It is thus a judgement of
	 * orientation and not magnitude: two vectors with the same orientation have
	 * a Cosine similarity of 1, two vectors at 90° have a similarity of 0, and
	 * two vectors diametrically opposed have a similarity of -1, independent of
	 * their magnitude. Cosine similarity is particularly used in positive
	 * space, where the outcome is neatly bounded in [0,1].
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private double cosineSimilarity(String str1, String str2) {
		return 0;
	}

	/**
	 * http://en.wikipedia.org/wiki/Levenshtein_distance In information theory
	 * and computer science, the Levenshtein distance is a string metric for
	 * measuring the difference between two sequences. Informally, the
	 * Levenshtein distance between two words is the minimum number of
	 * single-character edits (insertion, deletion, substitution) required to
	 * change one word into the other. The phrase edit distance is often used to
	 * refer specifically to Levenshtein distance. It is named after Vladimir
	 * Levenshtein, who considered this distance in 1965.
	 * 
	 * Mathematically, the similarity of two strings a,b can be represented as
	 * follows: similarity = Levenshtein distance / max{ len(a), len(b) }
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private double levenshteinDistance(String str1, String str2) {

		// To save the memory, we use two one-dimension array to store the
		// temporary results.
		boolean str1gtstr2 = str1.length() > str2.length();
		String shortStr = str1gtstr2 ? str2 : str1;
		String longStr = str1gtstr2 ? str1 : str2;

		int[][] res = new int[2][shortStr.length() + 1];
		int cur = 0, cost;

		for (int i = 1; i <= longStr.length(); i++) {
			for (int j = 1; j <= shortStr.length(); j++) {
				cost = (longStr.charAt(i - 1) == shortStr.charAt(j - 1) ? 0 : 1);
				res[cur][j] = Math.min((Math.min(res[cur ^ 1][j - 1] + cost,
						res[cur ^ 1][j] + 1)), res[cur][j - 1] + 1);
			}
			cur ^= 1;
		}
		return 1.0 * (longStr.length() - res[cur ^ 1][shortStr.length()])
				/ longStr.length();
	}

	/**
	 * http://en.wikipedia.org/wiki/Jaccard_indexU The Jaccard index, also known
	 * as the Jaccard similarity coefficient (originally coined coefficient de
	 * communauté by Paul Jaccard), is a statistic used for comparing the
	 * similarity and diversity of sample sets. The Jaccard coefficient measures
	 * similarity between sample sets, and is defined as the size of the
	 * intersection divided by the size of the union of the sample sets: 
	 * J(A, B) = | A ∪ B | / | A U B|
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private double jaccardIndex(String str1, String str2) {

		CharBuffer conjunctStr = CharBuffer.allocate(16);
		CharBuffer disjunctStr = CharBuffer.allocate(16);

		for (int i = 0; i < str1.length(); i++) {
			if (!findChar(disjunctStr, str1.charAt(i))) {
				disjunctStr.append(str1.charAt(i));
			}
			if (!findChar(conjunctStr, str1.charAt(i))
					&& findChar(str2, str1.charAt(i)))
				conjunctStr.append(str1.charAt(i));
		}

		for (int i = 0; i < str2.length(); i++) {
			if (!findChar(disjunctStr, str2.charAt(i))) {
				disjunctStr.append(str2.charAt(i));
			}
		}
		return 1.0 * conjunctStr.length() / disjunctStr.length();
	}

	/**
	 * Find if one string exists in an array
	 * 
	 * @param arr
	 * @param src
	 * @return
	 */
	private boolean findString(String[] arr, String src) {
		if (arr == null || src == null)	return false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(src)) {
				return true;
			}
		}
		return false;
	}

	private double jaccardIndex(String[] sar1, String[] sar2) {
		if (sar1 == null || sar2 == null)	return 0.0;
		List<String> conjunctArr = new ArrayList<String>();
		List<String> disjunctArr = new ArrayList<String>();

		for (int i = 0; i < sar1.length; i++) {
			if (!disjunctArr.contains(sar1[i])) {
				disjunctArr.add(sar1[i]);
			}

			if (!conjunctArr.contains(sar1[i]) && findString(sar2, sar1[i])) {
				conjunctArr.add(sar1[i]);
			}
		}

		for (int i = 0; i < sar2.length; i++) {
			if (!disjunctArr.contains(sar2[i])) {
				disjunctArr.add(sar2[i]);
			}
		}
		return 1.0 * conjunctArr.size() / disjunctArr.size();
	}

	/**
	 * In mathematics, the Euclidean distance or Euclidean metric is the
	 * "ordinary" distance between two points that one would measure with a
	 * ruler, and is given by the Pythagorean formula. By using this formula as
	 * distance, Euclidean space (or even any inner product space) becomes a
	 * metric space. The associated norm is called the Euclidean norm. Older
	 * literature refers to the metric as Pythagorean metric.
	 * 
	 * Euclidean Distance = sqrt(sum((p[i] - q[i])^2))
	 * 
	 * Euclidean Distance Based Similarity = 1 / (1 + ED)
	 * 
	 * @param sar1
	 * @param sar2
	 * @return
	 */
	private double euclideanDistance(String[] sar1, String[] sar2) {
		
		List<String> disjunctArr = new ArrayList<String>();
		
		for (int i = 0, j = 0 ; ; ){
			if (i < sar1.length && !disjunctArr.contains(sar1[i])) {
				disjunctArr.add(sar1[i++]);
			}
			if (j < sar2.length && !disjunctArr.contains(sar2[j])) {
				disjunctArr.add(sar2[j++]);
			}
			if (i >= sar1.length && j >= sar2.length)	break;
					
		}

		//Put the terms in an increasing order
		Collections.sort(disjunctArr);
		
		int [] vect1 = new int[disjunctArr.size()];
		int [] vect2 = new int[disjunctArr.size()];
		
		for (int i = 0 ; i < sar1.length ; i ++){
			int index = getIndex(sar1[i], disjunctArr);
			if (index != -1){
				vect1[index]++;
			}
		}
		
		for (int i = 0 ; i < sar2.length ; i ++){
			int index = getIndex(sar2[i], disjunctArr);
			if (index != -1){
				vect2[index]++;
			}
		}
		
		double ed2 = 0;
		for (int i = 0 ; i < vect1.length; i++){
			ed2 += Math.pow((vect1[i] - vect2[i]) * 1.0, 2);
		}
		double ed = Math.sqrt(ed2);
		
		return 1.0 / ( 1.0 + ed);
	}
	
	/**
	 * Binary search in a sorted list. Find the index of the searched string
	 * @param str
	 * @param sorted
	 * @return
	 */
	private int getIndex(String str, List<String> sorted){
		int start = 0, end = sorted.size() - 1;
		int mid = 0;
		while (start <= end){
			mid = (start + end)/2;
			if (sorted.get(mid).compareTo(str) > 0){
				end = mid - 1;
			}else if (sorted.get(mid).compareTo(str) < 0){
				start = mid + 1; 
			}else
				return mid;
		}
		return -1;
	}

	/**
	 * Check if one specified character exists in a charsequence
	 * 
	 * @param cb
	 *            The char sequence to retrieve
	 * @param ch
	 *            The target char to find
	 * @return true or false
	 */
	private boolean findChar(CharSequence cb, char ch) {
		for (int i = 0; i < cb.length(); i++) {
			if (cb.charAt(i) == ch)
				return true;
		}
		return false;
	}

}
