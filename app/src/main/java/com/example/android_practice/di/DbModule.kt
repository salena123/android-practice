package com.example.android_practice.di

import android.content.Context
import androidx.room.Room
import com.example.android_practice.data.database.DogDatabase
import org.koin.dsl.module

val dbModule = module {
    single { DatabaseBuilder.getInstance(get())}
}

object DatabaseBuilder {
    private var INSTANCE: DogDatabase? = null

    fun getInstance(context: Context): DogDatabase {
        if (INSTANCE == null) {
            synchronized(DogDatabase::class) {
                INSTANCE = buildRoomDb(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDb(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogs"
        ).build()
}
