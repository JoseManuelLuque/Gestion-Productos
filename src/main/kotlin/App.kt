
import utils.HibernateUtils
import console.Console
import model.Proveedor
import repository.ProductoRepository
import repository.ProveedorRespository
import repository.UsuarioRepository
import service.ProductoService
import service.ProveedorService
import service.UsuarioService

fun main() {
    val emf = HibernateUtils.getEntityManagerFactory("GestionProductos")
    val productoRepository = ProductoRepository(emf)
    val proveedorRespository = ProveedorRespository(emf)
    val usuarioRepository = UsuarioRepository(emf)
    val console = Console()
    val productoService = ProductoService(productoRepository, proveedorRespository, console)
    val proveedorService = ProveedorService(proveedorRespository, console)
    val usuarioService = UsuarioService(usuarioRepository, console)

    val proveedor1 = Proveedor(null, "Josema", "Calle1")
    val proveedor2 = Proveedor(null, "Paco", "Plaza1")
    proveedorRespository.insertarProveedor(proveedor1)
    proveedorRespository.insertarProveedor(proveedor2)

    login(usuarioService)

    var opcion: Int
    do {
        opcion = menu()
        when (opcion) {
            1 -> productoService.darAltaProducto()
            2 -> productoService.darBajaProducto()
            3 -> productoService.modificarNombreProducto()
            4 -> productoService.modificarStockProducto()
            5 -> productoService.obtenerUnProducto()
            6 -> productoService.obtenerProductosConStock()
            7 -> productoService.obtenerProductosSinStock()
            8 -> productoService.obtenerProveedorProducto()
            9 -> proveedorService.obtenerProveedores()
        }
    } while (opcion != 0)

    HibernateUtils.shutdown()
}

fun menu(): Int {
    println(
        "1. Alta de Producto\n" +
                "2. Baja de Producto\n" +
                "3. Modificar el nombre de producto\n" +
                "4. Modificar el stock del producto\n" +
                "5. Obtener un producto\n" +
                "6. Obtener productos con Stock\n" +
                "7. Obtener productos sun stock\n" +
                "8. Obtener el proveedor de un producto\n" +
                "9. Obtener todos los proveedores\n" +
                "0. Salir"
    )
    return Option("Seleccione una opción: ", 9)
}

fun Option(mensaje: String = "Seleccione una opción: ", maxRango: Int): Int {

    var opcion: Int
    do {
        opcion = try {
            print(mensaje)
            readln().toInt()
        } catch (e: NumberFormatException) {
            println("Error, el valor introducido no es un número entero entre 0 y $maxRango.")
            -1
        }
    } while (opcion !in 0..maxRango)

    return opcion
}

fun login(usuarioService: UsuarioService) {

    var opcion: Int
    var login = false
    do {
        println("Login:")
        println("1. Crear Usuario")
        println("2. Entrar con mi usuario.")
        println("0. Salir.")
        opcion = Option("Seleccione una opción: ", 2)

        when (opcion) {
            1 -> usuarioService.crearUsuario()
            2 -> login = usuarioService.comprobarUsuario()
        }

    } while (!login)
}