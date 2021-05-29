package com.onufrei.buildingo.service.contract.interfaces

import com.onufrei.buildingo.model.Contract
import com.onufrei.buildingo.service.CrudGenericService


/** Interface ContractService
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

interface ContractService : CrudGenericService<Contract> {
    fun countPlotsInProgress() : Int
}