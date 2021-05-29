package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.ScheduleEvent
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface ScheduleEventRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface ScheduleEventRepository : MongoRepository<ScheduleEvent, String> {
}