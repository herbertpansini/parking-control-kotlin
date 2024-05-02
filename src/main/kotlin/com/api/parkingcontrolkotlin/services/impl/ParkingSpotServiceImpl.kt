package com.api.parkingcontrolkotlin.services.impl

import com.api.parkingcontrolkotlin.repositories.ParkingSpotRepository
import com.api.parkingcontrolkotlin.services.ParkingSpotService
import com.api.parkingcontrolkotlin.services.dto.ParkingSpotDto
import com.api.parkingcontrolkotlin.services.mapper.ParkingSpotMapper
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
@Transactional
class ParkingSpotServiceImpl: ParkingSpotService {
    @Autowired
    private lateinit var parkingSpotRepository: ParkingSpotRepository

    @Autowired
    private lateinit var parkingSpotMapper: ParkingSpotMapper

    override fun save(parkingSpotDto: ParkingSpotDto): ParkingSpotDto {
        this.validate(parkingSpotDto)

        return this.parkingSpotMapper.toDto(
            this.parkingSpotRepository.save(
                this.parkingSpotMapper.toEntity(parkingSpotDto)
            )
        )
    }

    override fun update(id: UUID, parkingSpotDto: ParkingSpotDto): ParkingSpotDto {
        val parkingSpot = this.findById(id)
        BeanUtils.copyProperties(parkingSpotDto, parkingSpot, "id", "registrationDate")
        return this.save(parkingSpot)
    }

    @Transactional(readOnly = true)
    override fun findById(id: UUID) =
            this.parkingSpotMapper.toDto(
                this.parkingSpotRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
            )

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable) =
            this.parkingSpotRepository.findAll(pageable).map(this.parkingSpotMapper::toDto)

    override fun deleteById(id: UUID) = this.parkingSpotRepository.deleteById(id)

    private fun validate(parkingSpotDto: ParkingSpotDto) {
        if (this.validateLicensePlateCar(parkingSpotDto)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        if (this.validateParkingSpotNumber(parkingSpotDto)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        if (this.validateApartmentAndBlock(parkingSpotDto)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }

    private fun validateLicensePlateCar(parkingSpotDto: ParkingSpotDto): Boolean {
        val view = parkingSpotRepository.findByLicensePlateCar(parkingSpotDto.licensePlateCar)
        return !(view == null || view.getId() == parkingSpotDto.id)
    }

    private fun validateParkingSpotNumber(parkingSpotDto: ParkingSpotDto): Boolean {
        val view = parkingSpotRepository.findByParkingSpotNumber(parkingSpotDto.parkingSpotNumber)
        return !(view == null || view.getId() == parkingSpotDto.id)
    }

    private fun validateApartmentAndBlock(parkingSpotDto: ParkingSpotDto): Boolean {
        val view = parkingSpotRepository.findByApartmentAndBlock(parkingSpotDto.apartment, parkingSpotDto.block)
        return !(view == null || view.getId() == parkingSpotDto.id)
    }
}