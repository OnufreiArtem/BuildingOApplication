package com.onufrei.buildingo.service.building.interfaces

import com.onufrei.buildingo.model.Building
import com.onufrei.buildingo.service.CrudGenericService


/** Interface BuildingService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface BuildingService : CrudGenericService<Building> {
    fun getMainInfo() : List<Pair<String, String>>

}