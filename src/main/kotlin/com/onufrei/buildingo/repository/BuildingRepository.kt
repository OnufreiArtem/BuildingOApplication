package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Building
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface BuildingRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface BuildingRepository : MongoRepository<Building, String> {
}