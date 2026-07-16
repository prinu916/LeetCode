class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[] s1Even = new int[26];
        int[] s1Odd = new int[26];
        int[] s2Even = new int[26];
        int[] s2Odd = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (i % 2 == 0) {
                s1Even[c1 - 'a']++;
                s2Even[c2 - 'a']++;
            } else {
                s1Odd[c1 - 'a']++;
                s2Odd[c2 - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (s1Even[i] != s2Even[i] || s1Odd[i] != s2Odd[i]) {
                return false;
            }
        }

        return true;
    }
}