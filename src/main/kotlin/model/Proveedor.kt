package model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Suppress("JpaObjectClassSignatureInspection")
@Entity
@Table(name = "Proveedores")
class Proveedor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_proveedor: Long?,

    @Column(name = "nombre", nullable = false, length = 55, unique = true)
    var nombre: String,

    @Column(name = "Direccion", nullable = false, length = 55)
    var direccion: String,

    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val productos: MutableList<Producto> = mutableListOf()
) {
    fun addProducto(producto: Producto) {
        productos.add(producto)
        producto.proveedor = this
    }

    fun removeProducto(producto: Producto) {
        productos.remove(producto)
    }

    override fun toString(): String {
        return "[$nombre] Dirección: $direccion, Id: $id_proveedor, Productos: $productos"
    }
}