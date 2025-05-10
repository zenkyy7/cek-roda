package com.cekroda.data.repository

import com.cekroda.data.local.dao.InspectionDao
import com.cekroda.model.Inspection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class InspectionRepositoryImpl(
    private val dao: InspectionDao
): InspectionRepository {
    override suspend fun getAll(): Flow<List<Inspection>> {
        return dao.getAllInspections()
    }

    override suspend fun insert(inspection: Inspection) {
        return dao.insertInspection(inspection)
    }

    override suspend fun getInspectionById(id: Int): Inspection? {
        return dao.getInspectionById(id)
    }

    override suspend fun deleteInspection(inspection: Inspection) {
        return dao.deleteInspection(inspection)
    }
}