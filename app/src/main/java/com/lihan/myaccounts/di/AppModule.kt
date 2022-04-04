package com.lihan.myaccounts.di

import android.app.Application
import androidx.room.Room
import com.lihan.myaccounts.data.AccountDatabase
import com.lihan.myaccounts.data.AccountRepository
import com.lihan.myaccounts.data.AccountRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccountDatabase(app:Application):AccountDatabase{
        return Room.databaseBuilder(
            app,
            AccountDatabase::class.java,
            "account_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(db : AccountDatabase) : AccountRepository{
        return AccountRepositoryImp(dao = db.dao)
    }


}