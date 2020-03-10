package jasonedwardes.com.electricbillcalculator.Pounds

fun Pounds(intPrice: Int): String {
    var strPrice = ""
    var intPound = 0
    var intCounter = 0
    var strPence = "09"
    for (x in 1..intPrice) {
        intCounter += 1
        if (intCounter == 100) {
            intCounter = 0
            intPound += 1
        }
        var intPence = intPrice - (intPound * 100)

        if (intPence in 1..9) {
            strPence = "0$intPence"
            strPrice = "£$intPound.$strPence"
        } else {
            strPrice = "£$intPound.$intPence"
        }
    }
    return strPrice
}
