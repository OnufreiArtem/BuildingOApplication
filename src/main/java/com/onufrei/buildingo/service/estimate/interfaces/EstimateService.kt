package com.onufrei.buildingo.service.estimate.interfaces

import com.onufrei.buildingo.model.Estimate
import com.onufrei.buildingo.service.CrudGenericService


/** Interface EstimateService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface EstimateService : CrudGenericService<Estimate> {
    fun getMainInfo() : List<Pair<String, String>>
}