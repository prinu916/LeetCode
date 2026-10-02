class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if(n-- == 1) return List.of("()");
        dfs(n,n,"(");
        return res;
    }
    private void dfs(int o, int c, String s){
        if(o == 0 && c == 0){
            res.add(s + ")");
            return;
        }

    if(o > 0)
        dfs(o - 1, c, s + "(");
    if(c >= o){
        dfs(o, c - 1, s + ")");
    }
    }

}