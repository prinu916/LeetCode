class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int mod = 12345;
        int size = n * m;

        int[] flat = new int[size];
        
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flat[idx++] = grid[i][j] % mod;
            }
        }


        int[] prefix = new int[size];
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = (prefix[i - 1] * flat[i - 1]) % mod;
        }

        
        int[] suffix = new int[size];
        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * flat[i + 1]) % mod;
        }

    
        int[][] result = new int[n][m];
        idx = 0;
        for (int i = 0; i < size; i++) {
            int val = (prefix[i] * suffix[i]) % mod;
            result[i / m][i % m] = val;
        }

        return result;
    }
}