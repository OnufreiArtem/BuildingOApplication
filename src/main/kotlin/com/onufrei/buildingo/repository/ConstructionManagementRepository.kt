package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.ConstructionManagement
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface ConstructionManagementRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface ConstructionManagementRepository : MongoRepository<ConstructionManagement, String> {
}