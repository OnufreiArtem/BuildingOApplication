package com.onufrei.buildingo.service.machineryStorage.interfaces

import com.onufrei.buildingo.model.MachineryStorage
import com.onufrei.buildingo.service.CrudGenericService


/** Interface MachineryStorageService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface MachineryStorageService : CrudGenericService<MachineryStorage> {
    fun getMainInfo() : List<Pair<String, String>>
}