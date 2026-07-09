class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();

        long val = 1;

        for (int k = 0; k <= rowIndex; k++) {
            row.add((int) val);
            val = val * (rowIndex - k) / (k + 1);
        }

        return row;
    }
}