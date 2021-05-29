package com.onufrei.buildingo.service.plot.impls

import com.onufrei.buildingo.model.Plot
import com.onufrei.buildingo.repository.PlotRepository
import com.onufrei.buildingo.service.plot.interfaces.PlotService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.streams.toList

@Service
class PlotServiceImpl(private val repo: PlotRepository) : PlotService {
    override fun getAddressList(): List<Pair<String, String>> {
        return findAll().stream().map { Pair(it.id, it.address) }.toList()
    }

    override fun findAll(): List<Plot> {
        return repo.findAll()
    }

    override fun findById(id: String): Plot? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Plot): Plot? {
        if (findById(e.id) == null) {
            val plot = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(plot)
            return plot
        }

        return null
    }

    override fun update(e: Plot): Plot? {
        findById(e.id)?.let {
            val plot = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(plot)
            return plot
        } ?: return null
    }

    override fun delete(id: String): Plot? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}