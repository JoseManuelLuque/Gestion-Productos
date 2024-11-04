package repository

import jakarta.persistence.EntityManagerFactory
import model.Usuario

class UsuarioRepository(private val emf: EntityManagerFactory) {

    fun insertarUsuario(usuario: Usuario) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.persist(usuario)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun leerUsuario(nombre: String): Usuario? {
        val em = emf.createEntityManager()
        return try {
            em.find(Usuario::class.java, nombre)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            em.close()
        }
    }

    fun leerUsuarios(): List<Usuario>? {
        val em = emf.createEntityManager()
        return try {
            val query = em.createQuery("SELECT u FROM Usuario u", Usuario::class.java)
            query.resultList
        } catch (e: Exception) {
            println(e.message)
            null
        } finally {
            em.close()
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.merge(usuario)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            println(e.message)
        } finally {
            em.close()
        }
    }

    fun borrarUsuarios(usuario: Usuario) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            val usuario = em.find(Usuario::class.java, usuario.nombreUsuario)
            if (usuario != null) {
                em.remove(usuario)
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