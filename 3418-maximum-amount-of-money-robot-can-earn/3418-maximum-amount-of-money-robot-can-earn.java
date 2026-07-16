class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        int[][][] dp = new int[m][n][3];

        // Initialize
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        // Start
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) continue;

                int val = coins[i][j];

                for (int k = 0; k < 3; k++) {

                    // FROM TOP
                    if (i > 0) {
                        if (val >= 0) {
                            if (dp[i-1][j][k] != Integer.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k] + val);
                            }
                        } else {
                            // don't neutralize
                            if (dp[i-1][j][k] != Integer.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k] + val);
                            }

                            // neutralize
                            if (k > 0 && dp[i-1][j][k-1] != Integer.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i-1][j][k-1]);
                            }
                        }
                    }

                    // FROM LEFT
                    if (j > 0) {
                        if (val >= 0) {
                            if (dp[i][j-1][k] != Integer.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k] + val);
                            }
                        } else {
                            // don't neutralize
                            if (dp[i][j-1][k] != Integer.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k] + val);
                            }

                            // neutralize
                            if (k > 0 && dp[i][j-1][k-1] != Integer.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k],
                                    dp[i][j-1][k-1]);
                            }
                        }
                    }
                }
            }
        }

        return Math.max(dp[m-1][n-1][0],
                Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}