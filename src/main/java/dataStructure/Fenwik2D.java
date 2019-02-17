class NumMatrix {

    int t[][];
    int matrix[][];
    int n,m;
    
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
        if (n==0) return;
        m = matrix[0].length;
        t = new int[n][m];
        init();
    }
    
    private void init() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                increment(i,j,matrix[i][j]);
            }
        }
    }
    
    private void increment(int x, int y, int val) {
        for (int i=x; i<n; i= ((i+1)|i) ) {
            for (int j=y; j<m; j= ((j+1)|j) ) {
                t[i][j]+=val;
            }
        }
    }
    
    private int sum (int x, int y) {
        int result = 0;
        for (int i=x; i>=0; i=((i+1)&i)-1) {
            for (int j=y; j>=0; j=((j+1)&j)-1) {
                result += t[i][j];
            }
        }
        return result;
    }
    
    public void update(int row, int col, int val) {
        int cur = sumRegion(row, col, row, col);
        int delta = val - cur;
        increment(row, col, delta);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int left = 0;
        if (col1-1>=0) left = sum(row2, col1-1);
        
        int top = 0;
        if (row1-1>=0) top = sum(row1-1, col2);
        
        int topLeft = 0;
        if (row1-1>=0 && col1-1>=0) topLeft = sum(row1-1, col1-1);
        
        return sum(row2,col2)-left-top+topLeft;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
