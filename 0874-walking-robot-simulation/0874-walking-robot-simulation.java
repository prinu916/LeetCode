import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        final int lb = 30000;

        // Using HashSet instead of unordered_set
        HashSet<Long> obSet = new HashSet<>();
        for (int[] ob : obstacles) {
            long x = ob[0] + lb;
            long y = ob[1] + lb;
            obSet.add((x << 16) + y);
        }

        int[][] dir = {
            {0, 1},   // North
            {-1, 0},  // West
            {0, -1},  // South
            {1, 0}    // East
        };

        int x = 0, y = 0;
        int face = 0;  // initially facing North
        int maxD2 = 0;

        for (int c : commands) {
            if (c == -2) {  // turn left
                face = (face + 1) & 3;
            } else if (c == -1) {  // turn right
                face = (face + 3) & 3;
            } else {
                for (int i = 0; i < c; i++) {
                    int nx = x + dir[face][0];
                    int ny = y + dir[face][1];

                    if (obSet.contains(((long)(nx + lb) << 16) + (ny + lb))) {
                        break;
                    }

                    x = nx;
                    y = ny;

                    maxD2 = Math.max(maxD2, x * x + y * y);
                }
            }
        }

        return maxD2;
    }
}