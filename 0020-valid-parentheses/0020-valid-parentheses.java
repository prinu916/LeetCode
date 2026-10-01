class Solution {
    public boolean isValid(String str) {
        if(str.length()%2 == 1)
            return false;
            char[] s = str.toCharArray();
            int j = 0;
            for(char c : s)
                if((c&3) != 1){
                    s[j++] = c;
                }else if(j == 0 || ((c - s[--j]+1) >> 1) != 1)
                    return false;
            return j == 0;
    }
}