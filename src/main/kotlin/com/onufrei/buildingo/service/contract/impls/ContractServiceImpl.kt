package com.onufrei.buildingo.service.contract.impls

import com.onufrei.buildingo.model.Contract
import com.onufrei.buildingo.repository.ContractRepository
import com.onufrei.buildingo.service.contract.interfaces.ContractService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ContractServiceImpl(private val repo: ContractRepository) : ContractService {
    override fun countPlotsInProgress(): Int = findAll().filter { !it.failed && !it.finished }.count()

    override fun findAll(): List<Contract> {
        return repo.findAll()
    }

    override fun findById(id: String): Contract? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Contract): Contract? {
        if(findById(e.id) == null) {
            val contract = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(contract)
            return contract
        }

        return null
    }

    override fun update(e: Contract): Contract? {
        findById(e.id)?.let {
            val contract = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(contract)
            return contract
        } ?: return null
    }

    override fun delete(id: String): Contract? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}