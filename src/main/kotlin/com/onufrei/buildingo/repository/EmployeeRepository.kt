package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Employee
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : MongoRepository<Employee, String> {

}