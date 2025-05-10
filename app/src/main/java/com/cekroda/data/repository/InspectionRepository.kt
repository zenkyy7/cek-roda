package com.cekroda.data.repository

import com.cekroda.model.Inspection
import kotlinx.coroutines.flow.Flow

interface InspectionRepository {
    // Fungsi untuk mengambil semua inspeksi
    suspend fun getAll(): Flow<List<Inspection>>

    // Fungsi untuk menyimpan inspeksi baru
    suspend fun insert(inspection: Inspection)

    // Fungsi untuk mengambil inspeksi berdasarkan ID
    suspend fun getInspectionById(id: Int): Inspection?

    // Fungsi untuk menghapus inspeksi
    suspend fun deleteInspection(inspection: Inspection)
}