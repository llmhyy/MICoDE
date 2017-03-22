package edu.ntu.cltk.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Hungarian Algorithm
 * The bipartite graph is stored in an adjacent matrix. <br/>
 * User needs to specify which values demonstrates two nodes are connected <br/>
 * by calling setConfFlag.
 * For example, when setConFlag(0) denotes the cell(i,j) with value 0 means i and j is connected,
 * when setConFlag("connected") denotes the cell(i,j) with value "connected" showing i, j connected  
 * 
 * Step 1: Create a new instance of HungarianAlgo
 * Step 2: Set the flag of matching for different generic types
 * Step 3: Invoke the functional methods, such as maximalBipartiteGraphMatching
 * @author pillar
 *
 * @param <T>
 */
public class HungarianAlgo<T> {

	private T conFlag;
	
	public HungarianAlgo(){
		
	}
	public void setConFlag(T flag){
		this.conFlag = flag;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Find the maximal bipartite graph matching
	 * @param matrix
	 * @return	an array 
	 * 			For example, {3, 1, 2, -1} 
	 * 			meaning 1 is connected to 3
	 * 					2 is connected to 1
	 * 					3 is connected to 2
	 * 					4 is connected to NULL
	 */
	@SuppressWarnings("unchecked")
	public int[] maximalBipartiteGraphMatching(T[][] matrix){
		if (matrix == null)	return null;
		int rowCount = matrix.length;
		if (rowCount < 1)	return null;
		int colCount = matrix[0].length;
		if (colCount < 1)	return null;
		if (this.conFlag == null){
			Class elem = matrix[0][0].getClass();
			if (elem.equals(Integer.class) || elem.equals(Short.class) || elem.equals(Long.class)){
				this.conFlag = (T) new Integer(1);
			}else if (elem.equals(Double.class) || elem.equals(Float.class)){
				this.conFlag = (T) new Double(0);
			}else{
				try {
					throw new Exception("Need to set flag to connectivity for hungarian algorithm");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}
		adjTable = new ArrayList<List<Integer>>();
		for (int i = 0 ; i < rowCount; i++){
			List<Integer> row = new ArrayList<Integer>();
			for (int j = 0 ; j < colCount; j++){
				if (matrix[i][j].equals(this.conFlag)){
					row.add(j);
				}
			}
			adjTable.add(row);
		}
		flagA = new boolean[rowCount];
		flagB = new boolean[colCount];
		
		augmentingPath = new int[rowCount];
		reversePath = new int[colCount];
		for (int i = 0 ; i < rowCount; i++){
			augmentingPath[i] = -1;
		}
		for (int i = 0 ; i < colCount; i++){
			reversePath[i] = -1;
		}
		return canonicalMaximalBipartiteGraphMatching();
	}
	
	//To show if the node is in the augmenting path
	private boolean flagA[];
	private boolean flagB[];
	
	private List<List<Integer>> adjTable;
	private int augmentingPath[];
	private int reversePath[];
	
	
	private int[] canonicalMaximalBipartiteGraphMatching(){
		int rowCount = adjTable.size();
		for (int i = 0 ; i < rowCount; i++){
			for (int j = 0 ; j < adjTable.get(i).size(); j++){
				int agent = adjTable.get(i).get(j);
				for (int k = 0 ; k < flagA.length; k++)	flagA[k] = false;
				for (int k = 0 ; k < flagB.length; k++) flagB[k] = false;
				flagA[i] = true;
				
				if (findAugmentingPath(agent, false)){
					augmentingPath[i] = agent;
					reversePath[agent] = i;
					break;
				}
				//flagA[i] = false;
			}
		}
		return augmentingPath;
	}
	/**
	 * DFS for augmenting path
	 * @param agent
	 * @param checkPath
	 * @return
	 */
	private boolean findAugmentingPath(int agent, boolean checkPath){
		if (checkPath && flagA[agent]==false){	//The node already in augmenting path
			flagA[agent] = true;
			for (int i = 0 ; i < adjTable.get(agent).size(); i++){
				if (adjTable.get(agent).get(i) != augmentingPath[agent] && 
						findAugmentingPath(adjTable.get(agent).get(i), !checkPath)){
					augmentingPath[agent] = adjTable.get(agent).get(i);
					reversePath[adjTable.get(agent).get(i)] = agent;
					return true;
				}
			}
		}else if(!checkPath && flagB[agent] == false){
			flagB[agent] = true;
			return (reversePath[agent] == -1) || findAugmentingPath(reversePath[agent], !checkPath);
		}
		return false;
	}
	
	/**
	 * Solve the task assignment problem by Hungarian Algorithm <br/>
	 * The Hungarian Method: <br/>
	 * <strong>Step 1.</strong> Subtract the smallest entry in each row from all the entries of its row.<br/>
	 * <strong>Step 2.</strong> Subtract the smallest entry in each column from all the entries of its column.<br/>
	 * <strong>Step 3.</strong> Draw lines through appropriate rows and columns so that all the zero entries of the cost
	 * matrix are covered and the minimum number of such lines is used.<br/>
	 * <strong>Step 4.</strong> Test for Optimality: (i) If the minimum number of covering lines is n, an optimal assignment of zeros 
	 * is possible and we are finished. (ii) If the minimum number of covering lines is less than n, an optimal assignment of zeros is 
	 * not yet possible. In that case, proceed to Step 5.<br/>
	 * <strong>Step 5.</strong> Determine the smallest entry not covered by any line. Subtract this entry from each uncovered row, and 
	 * then add it to each covered column. Return to Step 3.<br/>
	 * @param matrix
	 * @return
	 */
	public double minimalTaskAssignment(T[][] matrix){
		if (matrix == null)	return -1;
		int rowCount = matrix.length;
		if (rowCount < 1)	return -1;
		int colCount = matrix[0].length;
		if (colCount < 1)	return -1;
		T[][] transformed = transformation(matrix);
		int [] res = maximalBipartiteGraphMatching(transformed);
		double weight = 0;
		for (int i = 0 ; i < res.length; i++){
			//weight += matrix[i][res[i]];
		}
		return weight;
	}
	
	private T[][] transformation(int[][] matrix){
		
		return null;
	}
	
	private T[][] transformation(T[][] matrix){
		//T[][] transformed = new int[matrix.length][matrix[0].length];
		//return transformed;
		return null;
	}
	
	private int minimalTaskAssignment(int[][] matrix){
		if (matrix == null)	return -1;
		int rowCount = matrix.length;
		if (rowCount < 1)	return -1;
		int colCount = matrix[0].length;
		if (colCount < 1)	return -1;
		T[][] transformed = transformation(matrix);
		int [] res = maximalBipartiteGraphMatching(transformed);
		int weight = 0;
		for (int i = 0 ; i < res.length; i++){
			weight += matrix[i][res[i]];
		}
		return weight;
	}
	
	private int numOfWorker;   	//the number of vertices in one part
	private static final int INF = 100000000;    	//just infinity
	private int cost[][];       //cost matrix
	private int n, max_match;   //n workers and n jobs
	private int lx[], ly[];     //labels of X and Y parts
	private int xy[];           //xy[x] - vertex that is matched with x,
	private int yx[];           //yx[y] - vertex that is matched with y
	private boolean S[], T[];   //sets S and T in algorithm
	private int slack[];        //as in the algorithm description
	private int slackx[];       //slackx[y] such a vertex, that
	                         // l(slackx[y]) + l(y) - w(slackx[y],y) = slack[y]
	private int prev[];         //array for memorizing alternating paths
	
	private void initLabels()
	{
		lx = new int[numOfWorker];
		ly = new int[numOfWorker];
		for (int i = 0 ; i < numOfWorker; i++)	lx[i] = 0;
		for (int i = 0 ; i < numOfWorker; i++)	ly[i] = 0;
	    for (int x = 0; x < n; x++)
	        for (int y = 0; y < n; y++)
	            lx[x] = Math.max(lx[x], cost[x][y]);
	}
	
	private void augment() // main function of the algorithm
	{
		if (max_match == n)
			return; // check wether matching is already perfect
		int x, y, root = 0; // just counters and root vertex
		int[] q = new int[numOfWorker];
		int wr = 0, rd = 0; // q - queue for bfs, wr,rd - write and read
							// pos in queue
		S = new boolean[numOfWorker]; // init set S
		T = new boolean[numOfWorker]; // init set T
		for (int i = 0; i < numOfWorker; i++)
			S[i] = false;
		prev = new int[numOfWorker];
		for (int i = 0; i < numOfWorker; i++)
			prev[i] = -1; // init set prev - for the alternating tree
		for (x = 0; x < n; x++)
			// finding root of the tree
			if (xy[x] == -1) {
				q[wr++] = root = x;
				prev[x] = -2;
				S[x] = true;
				break;
			}

		slack = new int[numOfWorker];
		slackx = new int[numOfWorker];

		for (y = 0; y < n; y++) // initializing slack array
		{
			slack[y] = lx[root] + ly[y] - cost[root][y];
			slackx[y] = root;
		}

		// second part of augment() function
		while (true) // main cycle
		{
			while (rd < wr) // building tree with bfs cycle
			{
				x = q[rd++]; // current vertex from X part
				for (y = 0; y < n; y++)
					// iterate through all edges in equality graph
					if (cost[x][y] == lx[x] + ly[y] && !T[y]) {
						if (yx[y] == -1)
							break; // an exposed vertex in Y found, so
									// augmenting path exists!
						T[y] = true; // else just add y to T,
						q[wr++] = yx[y]; // add vertex yx[y], which is matched
											// with y, to the queue
						add_to_tree(yx[y], x); // add edges (x,y) and (y,yx[y])
												// to the tree
					}
				if (y < n)
					break; // augmenting path found!
			}
			if (y < n)
				break; // augmenting path found!

			update_labels(); // augmenting path not found, so improve labeling
			wr = rd = 0;
			for (y = 0; y < n; y++)
				// in this cycle we add edges that were added to the equality
				// graph as a
				// result of improving the labeling, we add edge (slackx[y], y)
				// to the tree if
				// and only if !T[y] && slack[y] == 0, also with this edge we
				// add another one
				// (y, yx[y]) or augment the matching, if y was exposed
				if (!T[y] && slack[y] == 0) {
					if (yx[y] == -1) // exposed vertex in Y found - augmenting
										// path exists!
					{
						x = slackx[y];
						break;
					} else {
						T[y] = true; // else just add y to T,
						if (!S[yx[y]]) {
							q[wr++] = yx[y]; // add vertex yx[y], which is
												// matched with
												// y, to the queue
							add_to_tree(yx[y], slackx[y]); // and add edges
															// (x,y) and (y,
															// yx[y]) to the
															// tree
						}
					}
				}
			if (y < n)
				break; // augmenting path found!
		}

		if (y < n) // we found augmenting path!
		{
			max_match++; // increment matching
			// in this cycle we inverse edges along augmenting path
			for (int cx = x, cy = y, ty; cx != -2; cx = prev[cx], cy = ty) {
				ty = xy[cx];
				yx[cy] = cx;
				xy[cx] = cy;
			}
			augment(); // recall function, go to step 1 of the algorithm
		}
	}// end of augment() function
	void update_labels()
	{
	    int x, y, delta = INF;             //init delta as infinity
	    for (y = 0; y < n; y++)            //calculate delta using slack
	        if (!T[y])
	            delta = Math.min(delta, slack[y]);
	    for (x = 0; x < n; x++)            //update X labels
	        if (S[x]) lx[x] -= delta;
	    for (y = 0; y < n; y++)            //update Y labels
	        if (T[y]) ly[y] += delta;
	    for (y = 0; y < n; y++)            //update slack array
	        if (!T[y])
	            slack[y] -= delta;
	}
	
	void add_to_tree(int x, int prevx) 
	//x - current vertex,prevx - vertex from X before x in the alternating path,
	//so we add edges (prevx, xy[x]), (xy[x], x)
	{
	    S[x] = true;                    //add x to S
	    prev[x] = prevx;                //we need this when augmenting
	    for (int y = 0; y < n; y++)    //update slacks, because we add new vertex to S
	        if (lx[x] + ly[y] - cost[x][y] < slack[y])
	        {
	            slack[y] = lx[x] + ly[y] - cost[x][y];
	            slackx[y] = x;
	        }
	}
	

	public int maximalTaskAssignment(int[][] matrix){
		if (MatrixUtil.isEmpty(matrix)){
			return 0;
		}
		this.cost = matrix;
	    int ret = 0;                      //weight of the optimal matching
	    max_match = 0;                    //number of vertices in current matching
	    numOfWorker = matrix.length;
	    xy = new int[numOfWorker];
	    yx = new int[numOfWorker];
	    for (int i = 0 ; i < numOfWorker; i++)	xy[i] = -1;
	    for (int i = 0 ; i < numOfWorker; i++)	yx[i] = -1;
	    initLabels();	                  //step 0
	    augment();                        //steps 1-3
	    for (int i = 0 ; i < numOfWorker; i++){
	    	System.out.println(i + " -> " + xy[i]);
	    }
	    for (int x = 0; x < n; x++)       //forming answer there
	        ret += cost[x][xy[x]];
	    return ret;
	}
	
	
	private int munkresStep = 1;
	private int munkresRowCount;
	private int munkresColCount;
	private int[][] munkresMatrix;
	private int[][] origMunkresMatrix;
	private int[][] munkresMark;
	private int[] munkresRowCover;
	private int[] munkresColCover;
	private int pathRow0 = 0;
	private int pathCol0 = 0;
	private int[][] path;
	private int pathCount;
	/**
	 * Find the minimal assignment
	 * @param matrix
	 * @return
	 */
	public int minimalAssignment(int[][] matrix){
		if (MatrixUtil.isEmpty(matrix)){
			return 0;
		}
		munkresRowCount = matrix.length;
		munkresColCount = matrix[0].length;
		munkresMatrix = matrix;
		origMunkresMatrix = new int[munkresRowCount][munkresColCount];
		munkresMark = new int[munkresRowCount][munkresColCount];
		munkresRowCover = new int[munkresRowCount];
		munkresColCover = new int[munkresColCount];
		path = new int[munkresRowCount+munkresColCount][2];
		
		for (int i = 0 ; i < munkresRowCount; i++){
			for (int j = 0 ; j < munkresColCount; j++)
				origMunkresMatrix[i][j] = munkresMatrix[i][j];
		}
		return runMunkres();
	}
	private void showCostMatrix()
    {
		System.out.println("\n");
		System.out.println(String.format("------------Step %d-------------", munkresStep));
		for (int i = 0 ; i < munkresRowCount; i++){
			System.out.println();
			System.out.print("     ");
			for (int j = 0 ; j < munkresColCount; j++){
				System.out.print(String.format("%5d", munkresMatrix[i][j]));
			}
		}
    }

    private void showMaskMatrix()
    {
    	System.out.println();
    	System.out.print("\n     ");
    	for (int i = 0 ; i < munkresColCount; i++)
    		System.out.print(String.format("%5d", munkresColCover[i]));
    	for (int i = 0 ; i < munkresRowCount; i++){
    		System.out.print("\n  " + munkresRowCover[i] + "  ");
    		for (int j = 0 ; j < munkresColCount; j++){
    			System.out.print(String.format("%5d", munkresMark[i][j]));
    		}
    	}
    }

	private int runMunkres(){
		boolean done = false;
		int step = 1;
		while (!done){
			//showCostMatrix();
			//showMaskMatrix();
			switch(munkresStep){
				case 1:
					munkresStepOne();
					break;
				case 2:
					munkresStepTwo();
					break;
				case 3:
					munkresStepThree();
					break;
				case 4:
					munkresStepFour();
					break;
				case 5:
					munkresStepFive();
					break;
				case 6:
					munkresStepSix();
					break;
				case 7:
					//munkresStepSeven();
					done = true;
					break;
			
			}
		}
		int ret = 0;
		for (int i = 0 ; i < munkresRowCount; i++){
			for (int j = 0 ; j < munkresColCount; j++){
				if (munkresMark[i][j] == 1){
					ret += origMunkresMatrix[i][j];
				}
			}
		}
		return ret;
	}
	/**
	 * Step 1:  For each row of the matrix, find the smallest element <br/>
	 * and subtract it from every element in its row.  Go to Step 2.
	 */
	private void munkresStepOne(){
		int min = Integer.MAX_VALUE;
		for (int i = 0 ; i < munkresRowCount; i++){
			min = munkresMatrix[i][0];
			for (int j = 0 ; j < munkresColCount; j++){
				min = Math.min(min, munkresMatrix[i][j]);
			}
			for (int j = 0 ; j < munkresColCount; j++){
				munkresMatrix[i][j] -= min;
			}
		}
		this.munkresStep = 2;
	}
	/**
	 * Step 2:  Find a zero (Z) in the resulting matrix.  <br/>
	 * If there is no starred zero in its row or column, star Z. <br/>
	 *  Repeat for each element in the matrix. Go to Step 3.
	 */
	private void munkresStepTwo(){
		for (int i = 0 ; i < munkresRowCount; i++){
			for (int j = 0 ; j < munkresColCount; j++){
				if (munkresMatrix[i][j] == 0 && munkresRowCover[i] == 0 
						&& munkresColCover[j] == 0){
					munkresMark[i][j] = 1;
					munkresRowCover[i] = 1;
					munkresColCover[j] = 1;
				}
			}
		}
		for (int i = 0 ; i < munkresRowCount; i++){
			munkresRowCover[i] = 0;
		}
		for (int i = 0 ; i < munkresColCount; i++){
			munkresColCover[i] = 0;
		}
		munkresStep = 3;
	}
	/**
	 * Step 3:  Cover each column containing a starred zero.  <br/>
	 * If K columns are covered, the starred zeros describe a <br/>
	 * complete set of unique assignments.  In this case, Go to DONE, <br/>
	 * otherwise, Go to Step 4.
	 */
	private void munkresStepThree(){
		int colCount = 0;
		for (int i = 0 ; i < munkresRowCount; i++){
			for (int j = 0; j < munkresColCount; j++){
				if (munkresMark[i][j] == 1)
					munkresColCover[j] = 1;
			}
		}
		for (int i = 0 ; i < munkresColCount; i++){
			if (munkresColCover[i] == 1)
				colCount++;
		}
		if (colCount >= munkresColCount || colCount >= munkresRowCount)
			munkresStep = 7;
		else
			munkresStep = 4;
	}

	/**
	 * Step 4:  Find a noncovered zero and prime it.  <br/>
	 * If there is no starred zero in the row containing this primed zero, Go to Step 5.  <br/>
	 * Otherwise, cover this row and uncover the column containing the starred zero. <br/>
	 * Continue in this manner until there are no uncovered zeros left. <br/>
	 * Save the smallest uncovered value and Go to Step 6.
	 */
	private void munkresStepFour(){
		int row = -1;
		int col = -1;
		boolean done = false;
		while (!done){
			int[]cor = findAZero();
			row = cor[0];
			col = cor[1];
			if (row == -1){
				done = true;
				munkresStep = 6;
			}else{
				munkresMark[row][col] = 2;
				if (starInRow(row)){
					col = findStarInRow(row);
					munkresRowCover[row] = 1;
					munkresColCover[col] = 0;
				}else{
					done = true;
					munkresStep = 5;
					pathRow0 = row;
					pathCol0 = col;
				}
			}
		}
	}
	/**
	 * In this step, statements such as "find a noncovered zero" are <br/>
	 * clearly distinct operations that deserve their own functional blocks.  <br/>
	 * We have decomposed this step into a main method and three subprograms <br/>
	 * (2 methods and a boolean function).
	 * @return
	 */
	private int[] findAZero(){
		int r = 0;
		int c = 0;
		int row = -1;
		int col = -1;
		boolean done = false;
		while (!done){
			c = 0;
			while (true){
				if (munkresMatrix[r][c] == 0 && munkresRowCover[r] == 0 
						&& munkresColCover[c] == 0){
					row = r;
					col = c;
					done = true;
				}
				c += 1;
				if (c >= munkresColCount || done)
					break;
			}
			r += 1;
			if (r >= munkresRowCount)
				done = true;
		}
		return new int[]{row, col};
	}
	private boolean starInRow(int row){
		boolean tmp = false;
		for (int i = 0 ; i < munkresColCount; i++){
			if (munkresMark[row][i] == 1)
				tmp = true;
		}
		return tmp;
	}
	private int findStarInRow(int row){
		int col = -1;
		for (int i = 0 ; i < munkresColCount; i++){
			if (munkresMark[row][i] == 1)
				col = i;
		}
		return col;
	}
	/**
	 * Step 5:  Construct a series of alternating primed and starred zeros as follows.  <br/>
	 * Let Z0 represent the uncovered primed zero found in Step 4.  <br/>
	 * Let Z1 denote the starred zero in the column of Z0 (if any). <br/>
	 * Let Z2 denote the primed zero in the row of Z1 (there will always be one).  <br/>
	 * Continue until the series terminates at a primed zero that has no starred zero in its column.  <br/>
	 * Unstar each starred zero of the series, star each primed zero of the series, <br/>
	 * erase all primes and uncover every line in the matrix.  Return to Step 3.
	 */
	private void munkresStepFive(){
		boolean done = false;
		int r = -1;
		int c = -1;
		pathCount = 1;
		path[pathCount-1][0] = pathRow0;
		path[pathCount-1][1] = pathCol0;
		while (!done){
			r = findStarInCol(path[pathCount-1][1]);
			if (r > -1){
				pathCount += 1;
				path[pathCount-1][0] = r;
				path[pathCount-1][1] = path[pathCount-2][1];
			}else{
				done = true;
			}
			if (!done){
				c = findPrimeInRow(path[pathCount-1][0], c);
				pathCount++;
				path[pathCount-1][0] = path[pathCount-2][0];
				path[pathCount-1][1] = c;
			}
		}
		augmentPath();
		clearCovers();
		erasePrimes();
		munkresStep = 3;
	}
	
	private int findStarInCol(int c){
		int r = -1;
		for (int i = 0 ; i < munkresRowCount; i++){
			if (munkresMark[i][c] == 1)
				r = i;
		}
		return r;
	}
	private int findPrimeInRow(int r, int c){
		for (int i = 0 ; i < munkresColCount; i++){
			if (munkresMark[r][i] == 2)
				c = i;
		}
		return c;
	}
	private void augmentPath(){
		for (int i = 0 ; i < pathCount; i++){
			if (munkresMark[path[i][0]][path[i][1]] == 1)
				munkresMark[path[i][0]][path[i][1]] = 0;
			else
				munkresMark[path[i][0]][path[i][1]] = 1;
		}
	}
	private void clearCovers(){
		for (int i = 0 ; i < munkresRowCount; i++)
			munkresRowCover[i] = 0;
		for (int i = 0 ; i < munkresColCount; i++)
			munkresColCover[i] = 0;
	}
	private void erasePrimes(){
		for (int i = 0 ; i < munkresRowCount; i++){
			for (int j = 0 ; j < munkresColCount; j++)
				if (munkresMark[i][j] == 2)
					munkresMark[i][j] = 0;
		}
	}

	/**
	 * Step 6:  Add the value found in Step 4 to every element of each covered row, <br/>
	 * and subtract it from every element of each uncovered column. <br/>
	 * Return to Step 4 without altering any stars, primes, or covered lines.
	 */
	private void munkresStepSix(){
		int minVal = findSmallest();
		for (int i = 0 ; i < munkresRowCount; i++){
			for (int j = 0 ; j < munkresColCount; j++){
				if (munkresRowCover[i] == 1)
					munkresMatrix[i][j] += minVal;
				if (munkresColCover[j] == 0)
					munkresMatrix[i][j] -= minVal;
			}
		}
		munkresStep = 4;
	}
	private int findSmallest(){
		int minVal = Integer.MAX_VALUE;
		for (int i = 0; i < munkresRowCount; i++){
			for (int j = 0 ; j < munkresColCount; j++){
				if (munkresRowCover[i] == 0 && munkresColCover[j] == 0)
					minVal = Math.min(minVal, munkresMatrix[i][j]);
			}
		}
		return minVal;
	}
}
