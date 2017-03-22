package edu.ntu.cltk.algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PermutationGenerator<T> implements Iterator<List<T>> {

	private T[] data;
	private int[] ind;
	private Stack<int[]> opers = new Stack<int[]>();
	private boolean first = true;
	private boolean completed = false;
	
	public PermutationGenerator(List<T> orig){
		if (orig == null || orig.size() <= 0){
			completed = true;
		}else{
			completed = false;
			this.data = (T[]) orig.toArray();
			ind = new int[orig.size()];
			
			for (int i = 0; i < orig.size(); i++){
				opers.add(new int[]{i,i});	
			}
		}
	}
	
	private void swap(int a, int b){
		T tmp = this.data[a];
		this.data[a] = this.data[b];
		this.data[b] = tmp;
	}
	
	private void internalHasNext(){
		int[] lastOp;
		do{
			lastOp = opers.pop();
			if (lastOp[0] != lastOp[1])
				swap(lastOp[0], lastOp[1]);
		}while(lastOp[1] == this.data.length-1);
		lastOp[1]++;
		opers.add(new int[]{lastOp[0], lastOp[1]});
		swap(lastOp[0], lastOp[1]);
		for (int i = lastOp[0]+1; i < this.data.length; i++){
			opers.add(new int[]{i, i});
		}
		
	}
	/*void permute(char a[], int i, int n)
	{
	   int j;
	   if (i == n)
	     cout << a << endl;
	   else
	   {
	       for (j = i; j <= n; j++)
	       {
	          swap(a[i], a[j]);          
	          permute(a, i+1, n);
	          swap(a[i], a[j]);
	       }
	   }
	} */
	@Override
	public boolean hasNext() {
		if (first){
			first = false;
			return !completed;
		}
		Iterator<int[]> iter = opers.iterator();
		while (iter.hasNext()){
			int[] oper = iter.next();
			if (oper[1] != this.data.length - 1){
				internalHasNext();
				return true;
			}
		}
		completed = true;
		return false;
	}

	@Override
	public List<T> next() {
		if (completed)	return null;
		List<T> res = new ArrayList<T>(data.length);
		for (int i = 0 ; i < data.length; i++){
			res.add(this.data[i]);
		}
		return res;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
