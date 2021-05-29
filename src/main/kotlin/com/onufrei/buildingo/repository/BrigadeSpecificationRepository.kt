package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.BrigadeSpecification
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


/** Interface BrigadeSpecificationRepository
 * @author Artem Onufrei
 * @version 1
 * @since 11.05.2021
 */

@Repository
interface BrigadeSpecificationRepository : MongoRepository<BrigadeSpecification, String> {

}