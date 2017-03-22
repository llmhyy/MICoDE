package edu.ntu.cltk.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BinaryMap<T, V> {

	private HashMap<T, Integer> duplicates;
	private HashMap<T, V> forwardMap;
	private HashMap<V, T> backwardMap;
	private MappedIncrement nextIndex;
	private int mappedLength = 0;
	private List<T> orig;
	private List<V> mapped;
	
	public interface MappedIncrement{
		Object getNextValue(int pos);
	}
	
	public BinaryMap(List data, MappedIncrement increment){
		forwardMap = new HashMap<T, V>();
		backwardMap = new HashMap<V, T>();
		duplicates = new HashMap<T, Integer>();
		this.nextIndex = increment;
		this.orig = data;
		parse(data);
	}
	
	private void parse(List data){
		int cnt = 0;
		for (int i = 0 ; i < data.size(); i++){
			T item = (T)data.get(i);
			if (!forwardMap.containsKey(item)){
				forwardMap.put(item, (V)nextIndex.getNextValue(cnt));
				backwardMap.put((V)nextIndex.getNextValue(cnt), item);
				duplicates.put(item, 1);
				this.mappedLength++;
				cnt++;
			}else{
				int a = duplicates.get(item);
				a++;
				duplicates.put(item, a);
			}
		}
	}

	public int getCount(T key){
		if (duplicates.containsKey(key)){
			return duplicates.get(key);
		}
		return 0;
	}
	
	public boolean containsKey(T key){
		return forwardMap.containsKey(key);
	}
	
	public boolean containsValue(V key){
		return backwardMap.containsKey(key);
	}
	
	public V getMappedValue(T key){
		return forwardMap.get(key);
	}
	
	public T getUnmappedValue(V key){
		return backwardMap.get(key);
	}
	
	public List<T> getOrigList(){
		return this.orig;
	}
	
	public List<T> getDistinctOrigList(){
		List<T> res = new ArrayList<T>();
		for (int i = 0 ; i < this.mappedLength; i++){
			res.add(backwardMap.get((V) nextIndex.getNextValue(i)));
		}
		return res; 
	}
	
	public List<V> getMappedList(){
		if (mapped == null){
			List<V> res = new ArrayList<V>();
			for (int i = 0 ; i < this.orig.size();i++){
				res.add(forwardMap.get(this.orig.get(i)));
			}
			mapped = res;
		}
		return mapped;
	}
	
	public List<V> getDistinctMappedList(){
		List<V> res = new ArrayList<V>();
		for (int i = 0 ; i < this.mappedLength; i++){
			res.add((V) nextIndex.getNextValue(i));
		}
		return res;
	}
}
