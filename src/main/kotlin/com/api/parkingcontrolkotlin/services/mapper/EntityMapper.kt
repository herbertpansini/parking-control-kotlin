package com.api.parkingcontrolkotlin.services.mapper

interface EntityMapper<D, E> {
    fun toEntity(dto: D): E
    fun toDto(entity: E): D

    fun toEntity(dtoList: List<D>): List<E>
    fun toDto(entityList: List<E>): List<D>
}