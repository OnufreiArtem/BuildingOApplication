package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Customer
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface CustomerRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface CustomerRepository : MongoRepository<Customer, String> {
}