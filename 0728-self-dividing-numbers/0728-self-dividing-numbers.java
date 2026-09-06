class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> result = new ArrayList<>();

        for (int num = left; num <= right; num++) {

            int temp = num;
            boolean isSelfDividing = true;

            while (temp > 0) {
                int digit = temp % 10;

                // Digit 0 cannot divide the number
                if (digit == 0 || num % digit != 0) {
                    isSelfDividing = false;
                    break;
                }

                temp = temp / 10;
            }

            if (isSelfDividing) {
                result.add(num);
            }
        }

        return result;
    }
}