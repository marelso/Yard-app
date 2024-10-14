package io.marelso.shineyard.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.squareup.moshi.Moshi

object Session {
    private var store: SharedPreferences? = null

    var account: Account? = null
        set(value) {
            store?.edit(commit = true) {
                putString(Constants.KEY_ACCOUNT, value?.toJson())
            }
            field = value
        }

    fun init(context: Context) {
        store = context.getSharedPreferences(Constants.KEY_SESSION_PREFERENCE, Context.MODE_PRIVATE)
        account = store?.getString(Constants.KEY_ACCOUNT, null)?.toEntity()
    }
}

inline fun <reified T : Any> T.toJson(): String {
    val jsonAdapter = Moshi.Builder().build().adapter<T>(T::class.java)
    return jsonAdapter.toJson(this)
}

inline fun <reified T : Any> String.toEntity(): T? {
    val jsonAdapter = Moshi.Builder().build().adapter<T>(T::class.java)
    return jsonAdapter.fromJson(this)
}