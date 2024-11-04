package repository

import jakarta.persistence.EntityManagerFactory
import model.Proveedor

class ProveedorRespository(private val emf: EntityManagerFactory) {

    fun insertarProveedor(proveedor: Proveedor) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.persist(proveedor)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun leerProveedor(id: Long?): Proveedor? {
        val em = emf.createEntityManager()
        return try {
            em.find(Proveedor::class.java, id)
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun leerProveedores(): List<Proveedor>? {
        val em = emf.createEntityManager()
        return try {
            val query = em.createQuery("SELECT p FROM Proveedor p", Proveedor::class.java)
            query.resultList
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun actualizarProveedor(proveedor: Proveedor) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.merge(proveedor)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun borrarProveedor(id: String) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            val proveesor = em.find(Proveedor::class.java, id)
            if (proveesor != null) {
                em.remove(proveesor)
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