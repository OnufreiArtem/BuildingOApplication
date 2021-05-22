package com.onufrei.buildingo.service.plot.interfaces

import com.onufrei.buildingo.model.Plot
import com.onufrei.buildingo.service.CrudGenericService


/** Interface PlotService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface PlotService : CrudGenericService<Plot> {
    fun getAddressList() : List<Pair<String, String>>
}