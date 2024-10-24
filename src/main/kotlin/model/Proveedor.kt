package model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
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

    var nombre: String,

    var direccion: String,

    @OneToMany(cascade = [(CascadeType.ALL)])
    var productos: List<Producto>
) {

}