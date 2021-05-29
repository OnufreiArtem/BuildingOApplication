package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Contract
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface ContractRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface ContractRepository : MongoRepository<Contract, String> {
}