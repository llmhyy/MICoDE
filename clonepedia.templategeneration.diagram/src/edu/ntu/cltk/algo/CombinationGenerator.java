package edu.ntu.cltk.algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used for generating the combination for a dataset iteratively
 * @author pillar
 *
 * @param <T>
 */
public class CombinationGenerator<T> implements Iterator<List<T>>{

	private List<T> data;
	private int num;
	private boolean completed;
	private boolean first = true;
	private int[] ind;
	
	public CombinationGenerator(List<T> data, int num){
		this.data = data;
		this.num = num;
		
		if (num > data.size() || num <= 0){
			completed = true;
		}else{
			ind = new int[num];
			for (int i = 0 ; i < num; i++){
				ind[i] = i;
			}
			completed = false;
		}
	}

	private boolean internalHasNext(){
		if (first){
			first = false;
			return !completed;
		}
		int i;
		for (i = num - 1; i >=0 ; i--){
			if (ind[i] != this.data.size()-num+i)
				break;
		}
		if (i >= 0){
			completed = false;
			internalNext();		//At first time, do not move to the next element		
			return true;
		}
		completed = true;
		return false;
	}
	
	private void internalNext(){
		if (ind[num-1] < this.data.size() - 1){
			ind[num-1]++;
		}else{
			int i;
			for (i = 1; i <= num && ind[num-i] == this.data.size() - i; i++);
			if (i > num){
				// It could not be possible to get here, because it is only 
				// called by internalHasNext(), which already checks 
				completed = true;
			}else{
				ind[num-i]++;
				for (int j = num-i+1; j < num; j++){
					ind[j] = ind[j-1]+1;
				}
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		return internalHasNext();
	}

	@Override
	public List<T> next() {
		if (completed)	return null; 
		List<T> res = new ArrayList<T>(num);
		for (int i = 0 ; i < num ; i ++){
			res.add(this.data.get(ind[i]));
		}		
		return res;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	
}
