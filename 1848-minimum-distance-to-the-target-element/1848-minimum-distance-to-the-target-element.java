class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;

        int Dmin = Math.min(start, (n - 1) - start);
        int Dmax = Math.max(start, (n - 1) - start);

        int d = 0;

        // Check both sides from start
        for (d = 0; d <= Dmin; d++) {
            int left = nums[start - d];
            int right = nums[start + d];

            if (left == target || right == target) {
                return d;
            }
        }

        // Decide remaining direction
        int sgn = (start == Dmin) ? 1 : -1;

        // Check remaining side
        for (d = Dmin + 1; d <= Dmax; d++) {
            int index = start + sgn * d;

            if (nums[index] == target) {
                return d;
            }
        }

        return -1; // theoretically never reached
    }
}