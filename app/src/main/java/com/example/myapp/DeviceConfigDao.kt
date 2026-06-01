package com.example.myapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeviceConfigDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveConfig(config: DeviceConfigEntity)

    @Query("SELECT * FROM device_config WHERE id = 1 LIMIT 1")
    suspend fun getConfig(): DeviceConfigEntity?
}
