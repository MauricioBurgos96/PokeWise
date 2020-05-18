package com.dacodes.censos.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao <Entity>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: Entity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<Entity>)

    @Delete
    fun delete(entity: Entity)

    @Update
    fun update(vararg entity: Entity)
}