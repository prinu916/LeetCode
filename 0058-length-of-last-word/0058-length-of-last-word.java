class Solution {
    public int lengthOfLastWord(String s) {
        String[] str = s.split("\\s+");
        int n = str.length - 1;
        return str[n].length();
    }
}