package zfaria.computor

fun main(args: Array<String>) {
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

    var leftTerms = populateMap(termList, true)
    var rightTerms = populateMap(termList, false)

    mergeTerms(leftTerms, rightTerms)

    solve(leftTerms)
}

fun populateMap(termList: MutableList<Term>, left: Boolean): MutableMap<Int, Term> {
    var map = mutableMapOf<Int, Term>()
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
    return map
}

fun mergeTerms(left: MutableMap<Int, Term>, right: MutableMap<Int, Term>){
    right.forEach { (i, term) ->
        run {
            var curr = left[i]
            if (curr != null) {
                curr -= term
            } else {
                curr = term
            }
            left[i] = curr
        }
    }
}
