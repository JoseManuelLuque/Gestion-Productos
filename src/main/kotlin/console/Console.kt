package console

class Console {
    fun mostrarMensaje(message: String, lineBreak: Boolean) {
        if (lineBreak) {
            println(message)
        } else {
            print(message)
        }
    }

    fun readStr(message: String, lineBreak: Boolean, minLength: Int = 0, maxLength: Int = Int.MAX_VALUE): String {
        var string: String
        do {
            mostrarMensaje(message, lineBreak)
            string = readln().trim()
            if (string.length !in minLength..maxLength) {
                mostrarMensaje("Error, la cadena introducida no cumple con los requisitos.", true)
            }
        } while (string.length !in minLength.. maxLength)

        return string
    }

    fun readInt(message: String, lineBreak: Boolean, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int {
        var numero: Int?
        do {
            try {
                mostrarMensaje(message, lineBreak)
                numero = readln().trim().toInt()
                if (numero !in min..max) {
                    mostrarMensaje("Error, el número introducido no cumple los requisitos.", true)
                }
            } catch (e: NumberFormatException) {
                mostrarMensaje("Error, el valor introducido no es un número entero.", true)
                numero = null
            }
        } while (numero !in min.. max)

        return numero!!
    }

    fun readFlt(message: String, lineBreak: Boolean, min: Float = Float.MIN_VALUE, max: Float = Float.MAX_VALUE): Float {
        var numero: Float?
        do {
            try {
                mostrarMensaje(message, lineBreak)
                numero = readln().trim().toFloat()
                if (numero !in min..max) {
                    mostrarMensaje("Error, el número introducido no cumple los requisitos.", true)
                }
            } catch (e: NumberFormatException) {
                mostrarMensaje("Error, el valor introducido no es un número entero.", true)
                numero = null
            }
        } while (numero!! !in min.. max)

        return numero
    }

    fun readLong(message: String, lineBreak: Boolean, min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE): Long {
        var numero: Long?
        do {
            try {
                mostrarMensaje(message, lineBreak)
                numero = readln().trim().toLong()
                if (numero !in min..max) {
                    mostrarMensaje("Error, el número introducido no cumple los requisitos.", true)
                }
            } catch (e: NumberFormatException) {
                mostrarMensaje("Error, el valor introducido no es un número entero.", true)
                numero = null
            }
        } while (numero !in min.. max)

        return numero!!
    }
}
}