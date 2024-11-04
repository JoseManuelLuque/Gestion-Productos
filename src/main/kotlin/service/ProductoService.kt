package service

import console.Console
import model.Producto
import repository.ProductoRepository
import repository.ProveedorRespository

class ProductoService(
    val repository: ProductoRepository,
    private val repositoryProveedor: ProveedorRespository,
    private val console: Console
) {

    fun darAltaProducto() {

        val idProveedor = console.readLong("Introduce la id del proveedor: ", false, 0)
        val proveedor = repositoryProveedor.leerProveedor(idProveedor)

        if (proveedor != null) {
            val producto = Producto(
                categoria = console.readStr("Introduzca la categoría del producto: ", false, 3, 20),
                nombre = console.readStr("Introduzca el nombre del producto: ", false, 3, 20),
                descripcion = console.readStr("Introduzca la descripción del producto: ", false, 3, 50),
                precio_sin_IVA = console.readFlt("Introduzca el precio sin IVA del producto: ", false, 0f),
                stock = console.readInt("Introduzca el stock del producto: ", false, 0),
                proveedor = proveedor)

            proveedor.addProducto(producto)
            repositoryProveedor.actualizarProveedor(proveedor)
        } else {
            console.printMsg("El proveedor especificado no existe.", true)
        }
    }

    fun darBajaProducto() {
        val id = console.readStr("Introduzca la id del producto: ", false, 3, 20)
        val producto = repository.leerProducto(id)
        if (producto != null) {
            val proveedor = repository.leerProveedorDeProducto(id)
            if (proveedor != null) {
                proveedor.removeProducto(producto)
                repositoryProveedor.actualizarProveedor(proveedor)
            }
        } else {
            console.printMsg("No hay ningún producto con esa id.", true)
        }
    }

    fun modificarNombreProducto() {
        val id = console.readStr("Introduzca la id del producto: ", false, 0)
        val nuevoNombre = console.readStr("Introduzca el nuevo nombre del producto: ", false, 0)
        repository.actualizarNombre(id, nuevoNombre)
    }

    fun modificarStockProducto() {
        val id = console.readStr("Introduzca la id del producto: ", false, 0)
        val nuevoStock = console.readInt("Introduzca el nuevo stock del producto: ", false, 0)
        repository.ActualizarStock(id, nuevoStock)
    }

    fun obtenerUnProducto() {
        val id = console.readStr("Introduzca la id del producto: ", false, 0)
        val producto = repository.leerProducto(id)
        if (producto != null) {
            console.printMsg("$producto", true)
        } else {
            console.printMsg("No hay ningun producto con esa id.", true)
        }
    }

    fun obtenerProductosSinStock() {
        val productos = repository.leerProductoSinStock()
        if (productos!!.isEmpty()) {
            console.printMsg("No hay productos sin stock.", true)
        } else {
            for (producto in productos!!) {
                console.printMsg("$producto", true)
            }
        }
    }

    fun obtenerProductosConStock() {
        val productos = repository.leerProductoConStock()
        println(productos!!.size)
        if (productos!!.isEmpty()) {
            console.printMsg("No hay productos con stock.", true)
        } else {
            for (producto in productos!!) {
                console.printMsg("$producto", true)
            }
        }
    }

    fun obtenerProveedorProducto() {
        val id = console.readStr("Introduzca la id del producto: ", false, 0)
        val proveedor = repository.leerProveedor(id)

        if (proveedor == null) {
            console.printMsg("No se encuentra ningún proveedor para esa id.", true)
        } else {
            console.printMsg("$proveedor", true)
        }
    }
}