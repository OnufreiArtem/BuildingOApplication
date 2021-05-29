package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Spending
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface SpendingRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface SpendingRepository : MongoRepository<Spending, String> {
}