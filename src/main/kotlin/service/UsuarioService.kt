package service

import repository.UsuarioRepository

class UsuarioService(val repository: UsuarioRepository) {
    fun crearUsuario() {
        val nombre = console.pedirString("Introduzca el nuevo nombre de usuario: ", false, 0, 20)
        val password = console.pedirString("Introduzca la contraseña de usuario: ", false, 0, 20)
        val usuario = repository.leerUsuario(nombre)
        if (usuario == null) {
            repository.insertarUsuario(
                Usuario(nombre, password)
            )
        }
    }
}