package model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Suppress("JpaObjectClassSignatureInspection")
@Entity
@Table(name = "Usarios")
class Usuario(
    @Id
    @Column("Nombre")
    var nombreUsuario: String,

    @Column(name = "Conraseñas", nullable = false, length = 20)
    var password: String,
) {
}