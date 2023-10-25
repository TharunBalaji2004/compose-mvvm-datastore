package com.tharunbalaji.mad_datastore.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tharunbalaji.mad_datastore.utils.Constants
import com.tharunbalaji.mad_datastore.utils.Constants.PREF_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
): AppEntryManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override suspend fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }
}

val Context.dataStore by preferencesDataStore(name = PREF_NAME)

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}