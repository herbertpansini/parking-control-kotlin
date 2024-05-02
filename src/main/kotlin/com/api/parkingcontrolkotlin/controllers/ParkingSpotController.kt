package com.api.parkingcontrolkotlin.controllers

import com.api.parkingcontrolkotlin.services.ParkingSpotService
import com.api.parkingcontrolkotlin.services.dto.ParkingSpotDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("parking-spot")
class ParkingSpotController {
    @Autowired
    private lateinit var parkingSpotService: ParkingSpotService

    @PostMapping
    fun saveParkingSpot(@RequestBody @Valid parkingSpotDto: ParkingSpotDto) =
        ResponseEntity.status(HttpStatus.CREATED).body(this.parkingSpotService.save(parkingSpotDto))

    @PutMapping("{id}")
    fun updateParkingSpot(@PathVariable id: UUID, @RequestBody @Valid parkingSpotDto: ParkingSpotDto) =
        ResponseEntity.ok(this.parkingSpotService.update(id, parkingSpotDto))

    @GetMapping("{id}")
    fun findParkingSpotById(@PathVariable id: UUID) =
        ResponseEntity.ok(this.parkingSpotService.findById(id))

    @GetMapping
    fun findAllParkingSpots(pageable: Pageable) =
        ResponseEntity.ok(this.parkingSpotService.findAll(pageable))

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteParkingSpotById(@PathVariable id: UUID) =
        this.parkingSpotService.deleteById(id)
}