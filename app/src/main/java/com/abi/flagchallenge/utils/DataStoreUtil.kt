package com.abi.flagchallenge.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context : Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "flag_challenge")

    suspend fun setUserLoginAndSetKey(isLoggedIn : Boolean, userId: Int?) {
        context.dataStore.edit {
            it[USER_LOGGED_IN_KEY] = isLoggedIn
            it[CURRENT_USER_ID_KEY] = userId ?: 0
        }
    }

    val isUserLoggedIn = context.dataStore.data.map {
        it[USER_LOGGED_IN_KEY] ?: false
    }

    val getUserId = context.dataStore.data.map {
        it[CURRENT_USER_ID_KEY]
    }


    companion object {
        val USER_LOGGED_IN_KEY = booleanPreferencesKey("is_user_logged_in_key")
        val CURRENT_USER_ID_KEY = intPreferencesKey("current_user_key")
    }
}