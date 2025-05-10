package com.cekroda.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inspections")
data class Inspection(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carName: String,
    val carType: String,
    val licensePlate: String,
    val inspectionDate: Long,
    val inspectorName: String,
    val notes: String,
    val status: InspectionStatus,
    val photoUri: String? = null
)
