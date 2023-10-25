package com.tharunbalaji.mad_datastore.repository

import com.tharunbalaji.mad_datastore.manager.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AppEntryRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun saveAppEntry() {
        dataStoreManager.saveAppEntry()
    }

    suspend fun readAppEntry(): Flow<Boolean> {
        return dataStoreManager.readAppEntry()
    }
}