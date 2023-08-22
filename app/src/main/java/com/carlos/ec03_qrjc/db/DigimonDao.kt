package com.carlos.ec03_qrjc.db

import androidx.room.*
import com.carlos.ec03_qrjc.model.Digimont

@Dao
interface DigimonDao {
    @Query("SELECT * FROM digimon")
    fun getFavorites():List<Digimont>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(digimon: Digimont)

    @Query("DELETE FROM digimon WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Delete
    suspend fun deleteFavorite(digimon: Digimont)
}