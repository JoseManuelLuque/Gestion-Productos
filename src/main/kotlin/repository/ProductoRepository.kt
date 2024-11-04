package repository

import jakarta.persistence.EntityManagerFactory
import model.Producto
import model.Proveedor

class ProductoRepository(private val emf: EntityManagerFactory) {

    fun insertarProducto(producto: Producto) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun leerProducto(id: String): Producto? {
        val em = emf.createEntityManager()
        return try {
            em.find(Producto::class.java, id)
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun leerProveedor(id: String): Proveedor? {
        val em = emf.createEntityManager()
        return try {
            val producto = em.find(Producto::class.java, id)
            producto.proveedor
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun leerProductoSinStock(): List<Producto>? {
        val em = emf.createEntityManager()
        return try {
            val query = em.createQuery("SELECT p FROM Producto p WHERE p.stock = 0", Producto::class.java)
            query.resultList
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun leerProductoConStock(): List<Producto>? {
        val em = emf.createEntityManager()
        return try {
            val query = em.createQuery("SELECT p FROM Producto p WHERE p.stock > 0", Producto::class.java)
            query.resultList
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun leerProveedorDeProducto(id: String): Proveedor? {
        val em = emf.createEntityManager()
        return try {
            val producto = em.find(Producto::class.java, id)
            producto.proveedor
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun leerProductos(): List<Producto>? {
        val em = emf.createEntityManager()
        return try {
            val query = em.createQuery("SELECT p FROM Producto p", Producto::class.java)
            query.resultList
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun actualizarNombre(id: String, nombre: String) {
        val em = emf.createEntityManager()
        try {
            val producto = leerProducto(id)
            if (producto != null) {
                producto.nombre = nombre
                em.transaction.begin()
                em.merge(producto)
                em.transaction.commit()
            }
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun ActualizarStock(id: String, stock: Int) {
        val em = emf.createEntityManager()
        try {
            val producto = leerProducto(id)
            if (producto != null) {
                producto.stock = stock
                em.transaction.begin()
                em.merge(producto)
                em.transaction.commit()
            }
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun borrarProducto(id: String){
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            val producto = leerProducto(id)
            if (producto != null) {
                em.remove(producto)
            }
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

}