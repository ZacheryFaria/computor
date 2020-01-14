package zfaria.computor

import kotlin.math.pow
import kotlin.math.sqrt

fun solve(map: MutableMap<Int, Term>) {
    val degree = map.values.sortedByDescending { v -> v.degree }[0].degree

    println("Reduced form: ${getReducedForm(map)}")
    println("Polynomial degree: $degree")

    when (degree) {
        2 -> solveQuad(map)
        1 -> solveLinear(map)
        0 -> noDegree(map[0]!!)
        else -> cantSolve()
    }
}

fun getReducedForm(map: MutableMap<Int, Term>): String {
    val sorted = map.values.toList().sortedBy { v -> v.degree }

    val builder: StringBuilder = StringBuilder()

    val iter = sorted.iterator()

    while (iter.hasNext()) {
        builder.append(iter.next())
        if (iter.hasNext()) {
            builder.append(" + ")
        }
    }
    builder.append(" = 0")

    return builder.toString()
}

fun solveQuad(map: MutableMap<Int, Term>) {
    var a = map[2]?.coefficient
    var b = map[1]?.coefficient ?: 0f
    var c = map[0]?.coefficient ?: 0f

    var res = b.pow(2) - (4 * a!! * c)

    when {
        res > 0f -> quadTwoSol(res, b, a)
        res == 0f -> quadOneSol(res, b, a)
        res < 0f -> quadImagSol(res, b, a)
    }
}

fun quadTwoSol(disc: Float, b: Float, a: Float) {
    var res = sqrt(disc)

    var first = -b - res
    first /= 2 * a

    var sec = -b + res
    sec /= 2 * a

    println("Discriminant is strictly positive, the two solutions are:")
    println(first)
    println(sec)
}



fun quadOneSol(disc: Float, b: Float, a: Float) {
    var sol = -b / 2 * a

    println("Discriminant is strictly zero, the solution is:")
    println(sol)
}

fun quadImagSol(res: Float, b: Float, a: Float) {


    println("Discriminant is strictly negative, the two solutions are:")
}

fun solveLinear(map: MutableMap<Int, Term>) {
    var a = map[1]?.coefficient
    var b = map[0]?.coefficient ?: 0f

    var res = -b / a!!

    println("The solution is:")
    println(res)
}

fun cantSolve() {
    println("The polynomial degree is strictly greater than 2, I can't solve.")
}

fun noDegree(term: Term) {
    if (term.coefficient == 0f)
        println("∞")
    else {
        println("No solutions.")
    }
}
