
class Solution {
    public boolean isAnagram(String s, String t) {
       if(s.length() != t.length()){
            return false;
       }
       StringBuilder str = new StringBuilder();
       boolean isAnagram = true;

       for(int i=0; i<s.length(); i++){
        char target = s.charAt(i);
        if(str.indexOf(String.valueOf(target)) != -1){
            continue;
        }
        int count = 0;
        int count2 = 0;
        for(int j=0; j<s.length(); j++){
            if(s.charAt(j) == target){
                count++;
            }
        }
        for(int j=0; j<t.length(); j++){
            if(t.charAt(j) == target){
                count2++;
            }
        }if(count != count2){
           return false;
        }
        str.append(target);
       }
       return true;
    }
}