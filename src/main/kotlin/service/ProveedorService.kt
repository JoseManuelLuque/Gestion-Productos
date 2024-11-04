package service

import console.Console
import repository.ProveedorRespository

class ProveedorService(private val repository: ProveedorRespository, val console: Console) {

    fun obtenerProveedores() {
        val proveedores = repository.leerProveedores()

        if (proveedores!!.isEmpty()) {
            console.printMsg("No hay proveedores.", true)
        } else {
            for (proveedor in proveedores!!) {
                console.printMsg("$proveedor", true)
            }
        }
    }
}