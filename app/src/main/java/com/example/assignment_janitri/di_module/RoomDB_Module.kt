package com.example.assignment_janitri.di_module

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import androidx.work.WorkManager
import com.example.assignment_janitri.roomdatabase.PregnancyVital
import com.example.assignment_janitri.roomdatabase.PregnancyVitalDAO
import com.example.assignment_janitri.workmanager.SendNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {



    @Provides
    @Singleton
    fun getApplicationContext(@ApplicationContext context: Context) : Context{
        return context
    }

    @Provides
    @Singleton
    fun getRoomDatabase( context : Application) : PregnancyVital{
        return Room.databaseBuilder(context,
            PregnancyVital::class.java,
            "Pregnancy_Vital_DB")
            .build()
    }
    @Provides
    fun providesPregnancyVitalDAO(database : PregnancyVital) : PregnancyVitalDAO{
        return database.pregnancyDAO()
    }

    @Provides
    @Singleton
    fun getWorkManager(context : Application) : WorkManager{
        return WorkManager.getInstance(context)
    }

    @Provides
    fun providesSendNotification(workManager: WorkManager) : SendNotification{
        return SendNotification(workManager)
    }
}