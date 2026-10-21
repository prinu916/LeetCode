class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean found = false;
        for(int arr : nums){
            map.put(arr, map.getOrDefault(arr, 0) + 1);
        }for(int arr : nums){
            if(map.get(arr) != 1){
                found = true;
            }
        }
        return found;
    }
}