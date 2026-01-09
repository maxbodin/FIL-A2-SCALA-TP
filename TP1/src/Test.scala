object Test {
    def runTests(): Unit = {
        println("Running Rational number tests...")

        // --- Test 1: Construction and Simplification ---
        assert(new Rational(1).toString == "1/1", "1")
        assert(Rational(1, 2).toString == "1/2", "Basic construction")
        assert(Rational(-1, 2).toString == "-1/2", "Negative numerator")
        assert(Rational(0, 5).toString == "0/5", "Zero numerator")

        // --- Test 2: Auxiliary Constructor ---
        assert(new Rational(5).toString == "5/1", "Auxiliary constructor for whole numbers")

        assert(Rational(1, 2) != Rational(1, 3), "Inequality of different fractions")

        // --- Test 4: Addition ---
        val r1_2 = Rational(1, 2)
        val r1_3 = Rational(1, 3)
        assert((r1_2 + r1_3) == Rational(5, 6), "Addition 1/2 + 1/3")

        // --- Test 5: Multiplication ---
        val r2_3 = Rational(2, 3)
        assert((r1_2 * r2_3) == Rational(2, 6), "Multiplication 1/2 * 2/3")
        // Should throw error
        // assert((r1_3 * new Rational(0)) == Rational(0, 0), "Multiplication by zero")

        println("All tests passed successfully!")
    }

    def main(args: Array[String]): Unit = {
        runTests()
    }
}