package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Schedule
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface ScheduleRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface ScheduleRepository : MongoRepository<Schedule, String> {
}