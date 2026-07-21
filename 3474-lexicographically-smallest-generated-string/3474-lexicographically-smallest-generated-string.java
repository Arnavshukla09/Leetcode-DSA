class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;

        char[] word = new char[len];
        boolean[] fixed = new boolean[len];

        for (int i = 0; i < len; i++) {
            word[i] = '?';
        }

        // Apply all T constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    char c = str2.charAt(j);

                    if (word[pos] != '?' && word[pos] != c) {
                        return "";
                    }

                    word[pos] = c;
                    fixed[pos] = true;
                }
            }
        }

        // Fill remaining positions with 'a'
        for (int i = 0; i < len; i++) {
            if (word[i] == '?') {
                word[i] = 'a';
            }
        }

        // Handle F constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {

                boolean equal = true;

                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        equal = false;
                        break;
                    }
                }

                if (!equal) continue;

                int change = -1;

                // Rightmost mutable position
                for (int j = m - 1; j >= 0; j--) {
                    if (!fixed[i + j]) {
                        change = i + j;
                        break;
                    }
                }

                if (change == -1) {
                    return "";
                }

                word[change] = 'b';
            }
        }

        return new String(word);
    }
}