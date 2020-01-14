package zfaria.computor

class Term(term: String, var left: Boolean, negative: Boolean = false) {

    var coefficient: Double

    var degree: Int



    init {
        val terms = term.split("*")

        coefficient = terms[0].trim().toDouble()

        if (negative) {
            coefficient = -coefficient
        }

        if (terms.size == 1) {
            degree = 0
        }

        val exp = terms[1].split("^")

        if (exp.size == 1) {
            degree = 1
        } else {
            degree = exp[1].trim().toInt()
        }
    }

    override fun toString(): String {
        return "$coefficient * X^$degree"
    }

    operator fun plus(term: Term): Term {
        coefficient += term.coefficient

        return this
    }
}