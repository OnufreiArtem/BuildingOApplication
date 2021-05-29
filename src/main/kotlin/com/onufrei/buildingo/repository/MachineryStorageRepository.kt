package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.MachineryStorage
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface MachineryStorageRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface MachineryStorageRepository : MongoRepository<MachineryStorage, String> {
}