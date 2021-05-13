package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.EmployeeSpecification
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeSpecificationRepository : MongoRepository<EmployeeSpecification, String> {
}