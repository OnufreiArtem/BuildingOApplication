package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Estimate
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface EstimateRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface EstimateRepository : MongoRepository<Estimate, String> {
}