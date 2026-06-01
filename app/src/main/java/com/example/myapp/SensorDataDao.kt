package com.example.myapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SensorDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSensorData(data: SensorDataEntity)

    @Query("SELECT * FROM sensor_data ORDER BY timestamp DESC")
    fun getSensorHistory(): Flow<List<SensorDataEntity>>

    @Query("DELETE FROM sensor_data")
    suspend fun clearHistory()
}
