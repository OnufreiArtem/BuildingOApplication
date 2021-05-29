package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Request
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface RequestRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface RequestRepository : MongoRepository<Request, String> {
}