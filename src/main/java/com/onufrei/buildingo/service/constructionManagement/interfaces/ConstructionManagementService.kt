package com.onufrei.buildingo.service.constructionManagement.interfaces

import com.onufrei.buildingo.model.ConstructionManagement
import com.onufrei.buildingo.service.CrudGenericService


/** Interface ConstructionManagementService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface ConstructionManagementService : CrudGenericService<ConstructionManagement> {
    fun getAllAddresses() : List<Pair<String, String>>
}