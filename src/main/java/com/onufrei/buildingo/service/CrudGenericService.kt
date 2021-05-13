package com.onufrei.buildingo.service

interface CrudGenericService<T> {
    fun findAll() : List<T>
    fun findById(id: String) : T?
    fun add(e: T) : T?
    fun update(e: T) : T?
    fun delete(id: String) : T?
}