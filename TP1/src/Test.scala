object Test {
    def runTests(): Unit = {
        println("Running Rational number tests...")

        // --- Test 1: Construction, Simplification, et toString ---
        assert(Rational(1).toString == "1", "Whole number '1'")
        assert(Rational(5).toString == "5", "Whole number '5'")
        assert(Rational(1, 2).toString == "1/2", "Basic construction")
        assert(Rational(2, 4).toString == "1/2", "Simplification on construction")
        assert(Rational(10, 10).toString == "1", "Simplification to whole number")
        assert(Rational(1, -2).toString == "-1/2", "Sign normalization")
        assert(Rational(0, 5).toString == "0", "Zero numerator")

        // --- Test 2: Égalité ---
        assert(Rational(1, 2) == Rational(2, 4), "Equality of simplified fractions")
        assert(Rational(3, 1) == Rational(3), "Equality with auxiliary constructor")

        // --- Test 3: Utilisation des constantes du compagnon ---
        assert(Rational(0, 1) == Rational.ZERO, "Companion object constant ZERO")

        // --- Test 4: Addition ---
        val r1_2 = Rational(1, 2)
        val r1_3 = Rational(1, 3)
        assert((r1_2 + r1_3) == Rational(5, 6), "Addition 1/2 + 1/3")
        assert((r1_2 + r1_2) == Rational.ONE, "Addition results in ONE")

        // --- Test 5: Multiplication ---
        val r2_3 = Rational(2, 3)
        assert((r1_2 * r2_3) == Rational(1, 3), "Multiplication with simplification")
        assert((r1_3 * Rational.ZERO) == Rational.ZERO, "Multiplication by zero")

        println("All tests passed successfully!")
    }

    def main(args: Array[String]): Unit = {
        runTests()
    }
}