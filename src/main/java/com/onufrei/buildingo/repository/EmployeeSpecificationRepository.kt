package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.EmployeeSpecification
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeSpecificationRepository : MongoRepository<EmployeeSpecification, String> {
}