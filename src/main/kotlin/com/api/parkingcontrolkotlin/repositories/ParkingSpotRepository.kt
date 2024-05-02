package com.api.parkingcontrolkotlin.repositories

import com.api.parkingcontrolkotlin.models.ParkingSpot
import com.api.parkingcontrolkotlin.services.dto.ParkingSpotView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ParkingSpotRepository: JpaRepository<ParkingSpot, UUID> {
    fun findByLicensePlateCar(licensePlateCar: String): ParkingSpotView?

    fun findByParkingSpotNumber(parkingSpotNumber: String): ParkingSpotView?

    fun findByApartmentAndBlock(apartment: String, block: String): ParkingSpotView?
}