package com.onufrei.buildingo.service.buildingStep.interfaces

import com.onufrei.buildingo.model.BuildingStep
import com.onufrei.buildingo.service.CrudGenericService


/** Interface BuildingStepService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface BuildingStepService : CrudGenericService<BuildingStep> {
    fun allStepNames() : List<Pair<String, String>>
}