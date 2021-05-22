package com.onufrei.buildingo.service.employee.interfaces

import com.onufrei.buildingo.model.Employee
import com.onufrei.buildingo.service.CrudGenericService

interface EmployeeService : CrudGenericService<Employee> {
    fun getIdNamePairs() : List<Pair<String, String>>
}