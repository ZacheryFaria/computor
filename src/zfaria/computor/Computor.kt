package zfaria.computor

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No arguments given.")
        return
    }

    val reg = Regex("(?<=[+\\-=])|(?=[+\\-=])")

    val terms = args[0].split(reg)

    val termList = mutableListOf<Term>()

    var left = true

    val iterator = terms.iterator()
    while (iterator.hasNext()) {
        val t = iterator.next()
        if (t == "=") {
            left = false
        } else if (t == "-") {
            termList.add(Term(iterator.next(), left, negative = true))
        } else if (t != "+"){
            termList.add(Term(t, left))
        }
    }

    termList.sortByDescending { term -> term.degree }

    var leftTerms = mutableMapOf<Int, Term>()
    var rightTerms = mutableMapOf<Int, Term>()

    populateMap(leftTerms, termList, true)
    populateMap(rightTerms, termList, false)


    println(leftTerms)
    println(rightTerms)

    mergeTerms(leftTerms, rightTerms)

    println(leftTerms)

    println("Polynomial degree: ${termList[0].degree}")

}

fun populateMap(map: MutableMap<Int, Term>, termList: MutableList<Term>, left: Boolean) {
    for (term in termList) {
        if (term.left != left) continue

        var curr = map[term.degree]
        if (curr != null) {
            curr += term
        } else {
            curr = term
        }
        map[curr.degree] = curr
    }
}

fun mergeTerms(left: MutableMap<Int, Term>, right: MutableMap<Int, Term>){
    right.forEach { (i, term) ->
        run {
            var curr = left[i]
            if (curr != null) {
                curr += term
            } else {
                curr = term
            }
            left[i] = curr
        }
    }
}

fun bacon(moo: Any) {
    if (moo !is String) {
        return
    }

    moo.length

}