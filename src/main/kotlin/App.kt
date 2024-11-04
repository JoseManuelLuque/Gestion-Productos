import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import model.Usuario

fun main() {
    // Crear EntityManagerFactory usando Persistence
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("GestionProductos")
    // Crear EntityManager
    var em: EntityManager = emf.createEntityManager()


}

fun menu() {
    println(
        "1. Alta de Producto\n" +
                "2. Baja de Producto\n" +
                "3. Modificar el nombre de producto\n" +
                "4. Modificar el stock del producto\n" +
                "5. Obtener un producto\n" +
                "6. Obtener productos con Stock\n" +
                "7. Obtener productos sun stock\n" +
                "8. Obtener el proveedor de un producto\n" +
                "9. Obtener todos los proveedores"
    )
}