package cn.yuyao.leetcode.day0312;

/**
 * 八皇后问题
 */
public class EightQueue {

    public void solveQueue(int n){
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = '.';
            }
        }
        solve(chess, 0);
    }

    private void solve(char[][] chess, int row) {
        if (row == chess.length) {
            return;
        }

        for (int col = 0; col < chess.length; col++) {
            if (valid(chess, row, col)) {
               chess[row][col] = 'Q';
               solve(chess, row + 1);
               chess[row][col] = '.';
            }
        }
    }

    private boolean valid(char[][] chess, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col +1; i>= 0 && j <chess.length; i--,j++) {
            if (chess[i][j] == 'Q') return false;
        }

        for (int i = row - 1, j = col -1; i >= 0 && j>= 0; i--,j--) {
            if (chess[i][j] == 'Q') return false;
        }
        return true;
    }
}
