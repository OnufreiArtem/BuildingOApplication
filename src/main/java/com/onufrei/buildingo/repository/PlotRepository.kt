package com.onufrei.buildingo.repository

import com.onufrei.buildingo.model.Plot
import org.springframework.data.mongodb.repository.MongoRepository


/** Interface PlotRepository
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface PlotRepository : MongoRepository<Plot, String> {
}