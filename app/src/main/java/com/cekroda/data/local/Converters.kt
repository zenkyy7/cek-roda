package com.cekroda.data.local

import androidx.room.TypeConverter
import com.cekroda.model.InspectionStatus

class Converters {
    @TypeConverter
    fun fromStatus(value: InspectionStatus): String = value.name

    @TypeConverter
    fun toStatus(value: String): InspectionStatus = InspectionStatus.valueOf(value)
}