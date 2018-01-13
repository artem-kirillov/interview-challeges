/**
  * https://leetcode.com/problems/power-of-three
  */
object PowerOfThree {
  object Solution {
    private val MaxIntPowerOfThree = 1162261467

    def isPowerOfThree(n: Int): Boolean =
      n > 0 && MaxIntPowerOfThree % n == 0
  }
}
