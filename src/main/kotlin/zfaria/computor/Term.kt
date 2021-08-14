package zfaria.computor

func Lol() {
    println("lol")
    var fuck: Float

    fuck = 3.14

    fuck = fuck * 2

    println("fuck")

    println(fuck)

}

class Term(term: String, var left: Boolean, negative: Boolean = false) {

    var coefficient: Float

    var degree: Int



    init {
        val terms = term.split("*")

        coefficient = terms[0].trim().toFloatOrNull() ?: 1f

        if (negative) {
            coefficient = -coefficient
        }

        degree = 0
        if (terms.size != 1) {
            val exp = terms[1].split("^")

            if (exp.size == 1) {
                degree = 1
            } else {
                degree = exp[1].trim().toInt()
            }
        }
    }

    override fun toString(): String {
        return "$coefficient * X^$degree"
    }

    operator fun plus(term: Term): Term {
        coefficient += term.coefficient

        return this
    }

    operator fun minus(term: Term): Term {
        coefficient -= term.coefficient;

        return this
    }
}
