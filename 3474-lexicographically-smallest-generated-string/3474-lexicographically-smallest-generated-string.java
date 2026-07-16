class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        char[] word = new char[n + m - 1];
        boolean[] fixed = new boolean[n + m - 1]; // track T-fixed positions

        // Step 1: fill with '?'
        for (int i = 0; i < word.length; i++) {
            word[i] = '?';
        }

        // Step 2: Apply 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?' || word[i + j] == str2.charAt(j)) {
                        word[i + j] = str2.charAt(j);
                        fixed[i + j] = true; // mark fixed
                    } else {
                        return "";
                    }
                }
            }
        }

        // Step 3: fill remaining with 'a'
        for (int i = 0; i < word.length; i++) {
            if (word[i] == '?') {
                word[i] = 'a';
            }
        }

        // Step 4: handle 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    boolean changed = false;

                    // Try to break match ONLY at non-fixed positions
                    for (int j = m - 1; j >= 0; j--) {
                        if (!fixed[i + j]) {
                            for (char c = 'a'; c <= 'z'; c++) {
                                if (c != str2.charAt(j)) {
                                    word[i + j] = c;
                                    changed = true;
                                    break;
                                }
                            }
                        }
                        if (changed) break;
                    }

                    // If we couldn't change anything → impossible
                    if (!changed) return "";
                }
            }
        }

        return new String(word);
    }
}