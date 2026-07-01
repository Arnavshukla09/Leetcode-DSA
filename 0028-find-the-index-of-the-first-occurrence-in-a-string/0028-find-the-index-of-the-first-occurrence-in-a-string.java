class Solution {
    public int strStr(String haystack, String needle) {
        int hayLength = haystack.length();
        int needleLength = needle.length();

        if (needleLength == 0)
            return 0;

        if (hayLength < needleLength)
            return -1;

        for (int i = 0; i <= hayLength - needleLength; i++) {
            int j = 0;

            while (j < needleLength &&
                   haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            if (j == needleLength)
                return i;
        }

        return -1;
    }
}