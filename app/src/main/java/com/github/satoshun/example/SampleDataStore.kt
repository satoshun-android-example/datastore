package com.github.satoshun.example

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class SampleDataStore(private val context: Context) {
  private val dataStore = context.createDataStore(name = "sample")

  val value = dataStore.data
    .map { it[KEY] ?: false }
    .distinctUntilChanged()

  suspend fun update(isValue: Boolean) {
    dataStore.edit {
      it[KEY] = isValue
    }
  }

  companion object {
    val KEY = preferencesKey<Boolean>("test_key")
  }
}
