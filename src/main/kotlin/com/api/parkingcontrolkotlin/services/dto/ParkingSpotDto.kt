package com.api.parkingcontrolkotlin.services.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ParkingSpotDto(
        @Id
        var id: UUID?,

        @NotBlank
        var parkingSpotNumber: String,

        @NotBlank
        @Size(max = 7)
        var licensePlateCar: String,

        @NotBlank
        var brandCar: String,

        @NotBlank
        var modelCar: String,

        @NotBlank
        var colorCar: String,

        var registrationDate: LocalDateTime?,

        @NotBlank
        var responsibleName: String,

        @NotBlank
        var apartment: String,

        @NotBlank
        var block: String
): Serializable