import java.util.*;

class Solution {

    static class Robot {
        int pos, health, index;
        char dir;

        Robot(int p, int h, int i, char d) {
            pos = p;
            health = h;
            index = i;
            dir = d;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        List<Robot> robots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            robots.add(new Robot(positions[i], healths[i], i, directions.charAt(i)));
        }

        Collections.sort(robots, (a, b) -> a.pos - b.pos);

        Deque<Robot> stack = new ArrayDeque<>();

        for (Robot robot : robots) {
            if (robot.dir == 'R') {
                stack.push(robot);
            } else {
                while (!stack.isEmpty() && stack.peek().dir == 'R' && robot.health > 0) {
                    Robot top = stack.pop();

                    if (top.health == robot.health) {
                        robot.health = 0;
                        break;
                    } 
                    else if (top.health > robot.health) {
                        top.health--;
                        robot.health = 0;
                        stack.push(top);
                    } 
                    else {
                        robot.health--;
                    }
                }
                if (robot.health > 0) {
                    stack.push(robot);
                }
            }
        }

        List<Robot> survivors = new ArrayList<>(stack);

        Collections.sort(survivors, (a, b) -> a.index - b.index);

        List<Integer> result = new ArrayList<>();
        for (Robot r : survivors) {
            result.add(r.health);
        }

        return result;
    }
}