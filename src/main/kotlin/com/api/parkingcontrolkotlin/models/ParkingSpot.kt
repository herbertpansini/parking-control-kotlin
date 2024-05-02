package com.api.parkingcontrolkotlin.models

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.Table

@Entity
@Table(name = "TB_PARKING_SPOT")
data class ParkingSpot(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID?,

    @Column(nullable = false, unique = true, length = 10)
    var parkingSpotNumber: String,

    @Column(nullable = false, unique = true, length = 7)
    var licensePlateCar: String,

    @Column(nullable = false, length = 70)
    var brandCar: String,

    @Column(nullable = false, length = 70)
    var modelCar: String,

    @Column(nullable = false, length = 70)
    var colorCar: String,

    @Column(nullable = false)
    var registrationDate: LocalDateTime?,

    @Column(nullable = false, length = 130)
    var responsibleName: String,

    @Column(nullable = false, length = 30)
    var apartment: String,

    @Column(nullable = false, length = 30)
    var block: String
) {
    @PrePersist
    fun onPrePersist() {
        this.registrationDate = LocalDateTime.now()
    }
}