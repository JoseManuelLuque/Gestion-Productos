package service

import console.Console
import model.Usuario
import repository.UsuarioRepository

class UsuarioService(val repository: UsuarioRepository, val console: Console) {
    fun crearUsuario() {
        val nombre = console.readStr("Introduzca el nuevo nombre de usuario: ", false, 0, 20)
        val password = console.readStr("Introduzca la contraseña de usuario: ", false, 0, 20)
        val usuario = repository.leerUsuario(nombre)
        if (usuario == null) {
            repository.insertarUsuario(
                Usuario(nombre, password)
            )
        }
    }

    fun comprobarUsuario(): Boolean {
        val nombre = console.readStr("Introduzca el nuevo nombre de usuario: ", false, 0, 20)
        val password = console.readStr("Introduzca la contraseña de usuario: ", false, 0, 20)
        val usuario = repository.leerUsuario(nombre)
        return if (usuario != null) {
            usuario.password == password
        } else {
            console.printMsg("Usuario o contraseña incorrectos.", false)
            false
        }
    }
}