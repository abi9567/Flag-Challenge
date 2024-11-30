package com.abi.flagchallenge.utils

import android.content.Context
import android.text.TextUtils
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.abi.flagchallenge.data.GameSettings
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    private fun <T> objectToString(obj: T?): String {
        return GsonBuilder().create().toJson(obj)
    }

    private fun <T> getObjectFromString(dataString: String?, clazz: Class<T>): T? {
        if (TextUtils.isEmpty(dataString)) return null
        return GsonBuilder().create().fromJson(dataString, clazz)
    }

    suspend fun storeGameSettings(gameSettings: GameSettings?) {
        context.dataStore.edit { preference ->
            if (gameSettings == null) preference.remove(FLAG_GAME_SETTINGS) else
                preference[FLAG_GAME_SETTINGS] = objectToString(gameSettings)
        }
    }

    val getGameSettings = context.dataStore.data.map {
        getObjectFromString(it[FLAG_GAME_SETTINGS], GameSettings::class.java)
    }

    companion object {
        val FLAG_GAME_SETTINGS = stringPreferencesKey(name = "is_game_started")
        private const val DATA_STORE_NAME = "flag_challenge"
    }
}