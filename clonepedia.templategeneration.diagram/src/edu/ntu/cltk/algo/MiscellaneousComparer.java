package edu.ntu.cltk.algo;

public interface MiscellaneousComparer<T> {

	boolean equals(T t1, T t2);
	
	boolean larger(T t1, T t2);
	
	boolean smaller(T t1, T t2);

}
