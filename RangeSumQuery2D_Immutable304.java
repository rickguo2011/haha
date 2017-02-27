
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

Note:

    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.

public class NumMatrix {
    private int[][] dpSum;
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length; 
        int n = matrix[0].length;
        dpSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++){
            int dpRow = 0;
            for (int j = 0; j < n; j++){
               //good, but time larger dpSum[i + 1][j + 1] = dpSum[i][j + 1] + dpSum[i + 1][j] - dpSum[i][j] + matrix[i][j];
               dpRow += matrix[i][j]; //Remember the trick DP here
               dpSum[i + 1][j + 1] = dpSum[i][j + 1] + dpRow;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dpSum[row2 + 1][col2 + 1] - dpSum[row1][col2 + 1] - dpSum[row2 + 1][col1] + dpSum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
