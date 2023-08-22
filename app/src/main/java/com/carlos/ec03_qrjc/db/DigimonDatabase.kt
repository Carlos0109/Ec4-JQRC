package com.carlos.ec03_qrjc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlos.ec03_qrjc.model.Digimont

@Database(entities = [Digimont::class], version = 1)
abstract class DigimonDatabase: RoomDatabase() {
    abstract fun digimonDao():DigimonDao

    companion object{
        @Volatile
        private var instance: DigimonDatabase? = null
        fun getDatabase(context: Context):DigimonDatabase{
            if (instance==null){
                synchronized(this){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context):DigimonDatabase?{
            return Room.databaseBuilder(
                context.applicationContext,
                DigimonDatabase::class.java,
                "digimon_database"
            ).build()
        }
    }
}