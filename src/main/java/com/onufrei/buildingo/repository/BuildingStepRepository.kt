package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.BuildingStep
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface BuildingStepRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface BuildingStepRepository : MongoRepository<BuildingStep, String> {
}