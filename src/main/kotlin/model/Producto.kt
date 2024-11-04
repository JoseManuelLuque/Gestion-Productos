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
import java.util.Date


@Suppress("JpaObjectClassSignatureInspection")
@Entity
@Table(name = "Productos")
class Producto(
    @Id
    @Column("id", length = 9)
    var id_producto: String,

    @Column(nullable = false, length = 10)
    var categoria: String,

    @Column(nullable = false, length = 50)
    var nombre: String,

    @Column
    var descripcion: String,

    @Column("Precio sin IVA", nullable = false)
    var precio_sin_IVA: Float,

    @Column("Precio", nullable = false)
    var precio_IVA: Float,

    @Column(name = "Fecha de Alta", nullable = false)
    @Temporal(TemporalType.DATE)
    var fecha_alta: Date,

    @Column(nullable = false)
    var stock: Int,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_proveedor")
    var proveedor: Proveedor
) {
}