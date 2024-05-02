package com.api.parkingcontrolkotlin.services

import com.api.parkingcontrolkotlin.services.dto.ParkingSpotDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ParkingSpotService {
    fun save(parkingSpotDto: ParkingSpotDto): ParkingSpotDto
    fun update(id: UUID, parkingSpotDto: ParkingSpotDto): ParkingSpotDto
    fun findById(id: UUID): ParkingSpotDto
    fun findAll(pageable: Pageable): Page<ParkingSpotDto>
    fun deleteById(id: UUID)
}