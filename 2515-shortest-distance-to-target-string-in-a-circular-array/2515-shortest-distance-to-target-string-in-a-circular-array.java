class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<n;i++){
            if(words[i].equals(target)){
                int forward = (i - startIndex + n) % n;
                int backward = (startIndex - i + n) % n;
                int dis = Math.min(forward, backward);
                min = Math.min(min, dis);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}