package org.example.tp2_lab4.model.entity;

import jakarta.persistence.*    ;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pais {
    @Id
    @Column(name = "codigo_pais")
    private Integer codigoPais;

    @Column( length = 50, nullable = false)
    private String nombrePais;

    @Column( length = 50, nullable = false)
    private String capitalPais;

    @Column( length = 50, nullable = false)
    private String region;

    @Column( length = 50, nullable = false)
    private String subregion;

    @Column( nullable = false)
    private Long poblacion;

    @Column( nullable = false)
    private Double latitud;

    @Column( nullable = false)
    private Double longitud;
}
