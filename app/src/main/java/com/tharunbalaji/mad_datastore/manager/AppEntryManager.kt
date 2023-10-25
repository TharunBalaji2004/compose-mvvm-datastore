package com.tharunbalaji.mad_datastore.manager

import kotlinx.coroutines.flow.Flow

interface AppEntryManager {
    suspend fun saveAppEntry()
    suspend fun readAppEntry(): Flow<Boolean>
}