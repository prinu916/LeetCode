import java.util.*;

class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] num = new int[n];

        // Map robot -> distance
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(robots[i], distance[i]);
        }

        // Sort robots
        int[] sortedRobots = robots.clone();
        Arrays.sort(sortedRobots);

        // Sort walls
        int[] sortedWalls = walls.clone();
        Arrays.sort(sortedWalls);

        int m = sortedWalls.length;

        for (int i = 0; i < n; i++) {
            int robot = sortedRobots[i];
            int dist = map.get(robot);

            // walls ≤ robot
            int pos1 = upperBound(sortedWalls, robot);

            int leftPos;
            if (i >= 1) {
                int leftBound = Math.max(robot - dist, sortedRobots[i - 1] + 1);
                leftPos = lowerBound(sortedWalls, leftBound);
            } else {
                leftPos = lowerBound(sortedWalls, robot - dist);
            }

            left[i] = pos1 - leftPos;

            int rightPos;
            if (i < n - 1) {
                int rightBound = Math.min(robot + dist, sortedRobots[i + 1] - 1);
                rightPos = upperBound(sortedWalls, rightBound);
            } else {
                rightPos = upperBound(sortedWalls, robot + dist);
            }

            int pos2 = lowerBound(sortedWalls, robot);
            right[i] = rightPos - pos2;

            if (i == 0) continue;

            int pos3 = lowerBound(sortedWalls, sortedRobots[i - 1]);
            num[i] = pos1 - pos3;
        }

        int subLeft = left[0];
        int subRight = right[0];

        for (int i = 1; i < n; i++) {
            int currentLeft = Math.max(
                    subLeft + left[i],
                    subRight - right[i - 1] + Math.min(left[i] + right[i - 1], num[i])
            );

            int currentRight = Math.max(
                    subLeft + right[i],
                    subRight + right[i]
            );

            subLeft = currentLeft;
            subRight = currentRight;
        }

        return Math.max(subLeft, subRight);
    }

    // lower_bound
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    // upper_bound
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}