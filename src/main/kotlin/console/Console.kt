package console

class Console {
    fun printMsg(message: String, lineBreak: Boolean) {
        if (lineBreak) {
            println(message)
        } else {
            print(message)
        }
    }

    fun readStr(message: String, lineBreak: Boolean, minLength: Int = 0, maxLength: Int = Int.MAX_VALUE): String {
        var string: String
        do {
            printMsg(message, lineBreak)
            string = readln().trim()
            if (string.length !in minLength..maxLength) {
                printMsg("Error, la cadena introducida no cumple con los requisitos.", true)
            }
        } while (string.length !in minLength.. maxLength)

        return string
    }

    fun readInt(message: String, lineBreak: Boolean, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int {
        var numero: Int?
        do {
            try {
                printMsg(message, lineBreak)
                numero = readln().trim().toInt()
                if (numero !in min..max) {
                    printMsg("Error, el número introducido no cumple los requisitos.", true)
                }
            } catch (e: NumberFormatException) {
                printMsg("Error, el valor introducido no es un número entero.", true)
                numero = null
            }
        } while (numero !in min.. max)

        return numero!!
    }

    fun readFlt(message: String, lineBreak: Boolean, min: Float = Float.MIN_VALUE, max: Float = Float.MAX_VALUE): Float {
        var numero: Float?
        do {
            try {
                printMsg(message, lineBreak)
                numero = readln().trim().toFloat()
                if (numero !in min..max) {
                    printMsg("Error, el número introducido no cumple los requisitos.", true)
                }
            } catch (e: NumberFormatException) {
                printMsg("Error, el valor introducido no es un número entero.", true)
                numero = null
            }
        } while (numero!! !in min.. max)

        return numero
    }

    fun readLong(message: String, lineBreak: Boolean, min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE): Long {
        var numero: Long?
        do {
            try {
                printMsg(message, lineBreak)
                numero = readln().trim().toLong()
                if (numero !in min..max) {
                    printMsg("Error, el número introducido no cumple los requisitos.", true)
                }
            } catch (e: NumberFormatException) {
                printMsg("Error, el valor introducido no es un número entero.", true)
                numero = null
            }
        } while (numero !in min.. max)

        return numero!!
    }
}