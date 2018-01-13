/**
  * https://leetcode.com/problems/roman-to-integer/description/
  */
object RomanToInteger {
  object Solution {
    private val mapping = Map("I" -> 1, "IV" -> 4, "V" -> 5, "IX" -> 9, "X" -> 10,
      "XL" -> 40, "L" -> 50, "XC" -> 90, "C" -> 100, "CD" -> 400, "D" -> 500,
      "CM" -> 900, "M" -> 1000)

    def romanToInt(s: String): Int = {
      if (s.isEmpty) {
        return 0
      }
      var res = 0
      var skip = false
      s.sliding(2, 1).foreach { chunk: String =>
        if (skip) {
          skip = false
        } else if (mapping.contains(chunk)) {
          res += mapping(chunk)
          skip = true
        } else {
          res += mapping(chunk.head.toString)
          skip = false
        }
      }

      if (skip) res else res + mapping(s.last.toString)
    }
  }
}
