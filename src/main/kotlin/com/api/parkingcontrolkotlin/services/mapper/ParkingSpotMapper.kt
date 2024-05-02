package com.api.parkingcontrolkotlin.services.mapper

import com.api.parkingcontrolkotlin.models.ParkingSpot
import com.api.parkingcontrolkotlin.services.dto.ParkingSpotDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ParkingSpotMapper: EntityMapper<ParkingSpotDto, ParkingSpot> {
}