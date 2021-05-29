package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Machinery
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface MachineryRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface MachineryRepository : MongoRepository<Machinery, String> {
}