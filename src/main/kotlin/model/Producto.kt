package model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import java.time.Instant
import java.util.Date


@Suppress("JpaObjectClassSignatureInspection")
@Entity
@Table(name = "Productos")
class Producto(

    @Column(nullable = false, length = 10)
    var categoria: String,

    @Column(nullable = false, length = 50)
    var nombre: String,

    @Column
    var descripcion: String,

    @Column(nullable = false)
    var stock: Int,

    @Column("Precio sin IVA", nullable = false)
    var precio_sin_IVA: Float,

    @Column(name = "Fecha de Alta", nullable = false)
    @Temporal(TemporalType.DATE)
    var fecha_alta: Date = Date.from(Instant.now()),

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_proveedor")
    var proveedor: Proveedor,


    @Id
    @Column("id", length = 9)
    var id_producto: String
) {
    constructor(categoria: String, nombre: String, descripcion: String, precio_sin_IVA: Float, stock: Int, proveedor: Proveedor)
            : this(categoria, nombre, descripcion, stock, precio_sin_IVA, Date.from(Instant.now()), proveedor, "${categoria.take(3)}${nombre.take(3)}${proveedor.nombre.take(3)}")

    val precioConIva: Float
        get() = precio_sin_IVA * 1.21f

    override fun toString(): String {
        return "[$nombre] Precio con IVA: %.2f, Categoría: $categoria Stock: $stock, Id: $id_producto, Fecha Alta: $fecha_alta".format(precioConIva)
    }
}