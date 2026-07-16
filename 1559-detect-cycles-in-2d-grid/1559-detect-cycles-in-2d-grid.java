class Solution {
    int[] parent;
    int[] rank;

    public boolean containsCycle(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int totalCells = rows * cols;
        
        parent = new int[totalCells];
        rank = new int[totalCells];

        for (int i = 0; i < totalCells; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int u = r * cols + c;

                if (c + 1 < cols && grid[r][c] == grid[r][c + 1]) {
                    if (!union(u, r * cols + (c + 1))) return true;
                }

                if (r + 1 < rows && grid[r][c] == grid[r + 1][c]) {
                    if (!union(u, (r + 1) * cols + c)) return true;
                }
            }
        }
        return false;
    }

    private int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]); 
    }

    private boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI == rootJ) return false; 
        if (rank[rootI] > rank[rootJ]) parent[rootJ] = rootI;
        else if (rank[rootI] < rank[rootJ]) parent[rootI] = rootJ;
        else {
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
        return true;
    }
}