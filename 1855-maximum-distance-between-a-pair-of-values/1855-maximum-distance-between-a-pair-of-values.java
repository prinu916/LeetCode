class Solution {
    public static int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int dist = 0;

        int i = 0, j = 0;

        while (i < n1 && j < n2) {
            if (i <= j && nums1[i] <= nums2[j]) {
                dist = Math.max(dist, j - i);
                j++;
            } else if (i <= j) {
                i++;
            } else {
                j++;
            }
        }

        return dist;
    }
}