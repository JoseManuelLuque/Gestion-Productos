package service

import console.Console
import repository.ProductoRepository

class ProductoService(
    val repository: ProductoRepository,
    private val repositoryProveedor: ProveedorRespository,
    private val console: Console
) {

    fun darAltaProducto() {

        val idProveedor = console.pedirLong("Introduce la id del proveedor: ", false, 0)
        val proveedor = repositoryProveedor.readProveedor(idProveedor)

        if (proveedor != null) {
            val producto = Producto(
                categoria = console.pedirString("Introduzca la categoría del producto: ", false, 3, 20),
                nombre = console.pedirString("Introduzca el nombre del producto: ", false, 3, 20),
                descripcion = console.pedirString("Introduzca la descripción del producto: ", false, 3, 50),
                precioSinIva = console.pedirFloat("Introduzca el precio sin IVA del producto: ", false, 0f),
                stock = console.pedirInt("Introduzca el stock del producto: ", false, 0),
                proveedor = proveedor
            )

            proveedor.addProducto(producto)
            repositoryProveedor.updateProveedor(proveedor)
        } else {
            console.mostrarMensaje("El proveedor especificado no existe.", true)
        }
    }

    fun darBajaProducto() {
        val id = console.pedirString("Introduzca la id del producto: ", false, 3, 20)
        val producto = repository.readProducto(id)
        if (producto != null) {
            val proveedor = repository.readProveedorProducto(id)
            if (proveedor != null) {
                proveedor.removeProducto(producto)
                repositoryProveedor.updateProveedor(proveedor)
            }
        } else {
            console.mostrarMensaje("No hay ningún producto con esa id.", true)
        }
    }

    fun modificarNombreProducto() {
        val id = console.pedirString("Introduzca la id del producto: ", false, 0)
        val nuevoNombre = console.pedirString("Introduzca el nuevo nombre del producto: ", false, 0)
        repository.updateProductoNombre(id, nuevoNombre)
    }

    fun modificarStockProducto() {
        val id = console.pedirString("Introduzca la id del producto: ", false, 0)
        val nuevoStock = console.pedirInt("Introduzca el nuevo stock del producto: ", false, 0)
        repository.updateProductoStock(id, nuevoStock)
    }

    fun obtenerUnProducto() {
        val id = console.pedirString("Introduzca la id del producto: ", false, 0)
        val producto = repository.readProducto(id)
        if (producto != null) {
            console.mostrarMensaje("$producto", true)
        } else {
            console.mostrarMensaje("No hay ningun producto con esa id.", true)
        }
    }

    fun obtenerProductosSinStock() {
        val productos = repository.readProductoSinStock()
        if (productos.isEmpty()) {
            console.mostrarMensaje("No hay productos sin stock.", true)
        } else {
            for (producto in productos) {
                console.mostrarMensaje("$producto", true)
            }
        }
    }

    fun obtenerProductosConStock() {
        val productos = repository.readProductoConStock()
        println(productos.size)
        if (productos.isEmpty()) {
            console.mostrarMensaje("No hay productos con stock.", true)
        } else {
            for (producto in productos) {
                console.mostrarMensaje("$producto", true)
            }
        }
    }

    fun obtenerProveedorProducto() {
        val id = console.pedirString("Introduzca la id del producto: ", false, 0)
        val proveedor = repository.readProveedor(id)

        if (proveedor == null) {
            console.mostrarMensaje("No se encuentra ningún proveedor para esa id.", true)
        } else {
            console.mostrarMensaje("$proveedor", true)
        }
    }
}