class Solution:
    def gcdOfOddEvenSums(self, n: int) -> int:
        sumOdd=n**2
        sumEven=n*(n+1)
        return math.gcd(sumOdd,sumEven)