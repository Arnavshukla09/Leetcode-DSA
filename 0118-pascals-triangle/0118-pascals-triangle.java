class Solution {
    public List<List<Integer>> generate(int numRows) {
                List<List<Integer>> ans = new ArrayList<>();

        for (int n = 0; n < numRows; n++) {

            List<Integer> row = new ArrayList<>();

            long val = 1;

            for (int k = 0; k <= n; k++) {
                row.add((int) val);

                val = val * (n - k) / (k + 1);
            }

            ans.add(row);
        }

        return ans;
    }
}