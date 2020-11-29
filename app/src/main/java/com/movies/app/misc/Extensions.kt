package com.movies.app.misc

import com.google.gson.Gson

/**
 * Parse model to string & string to Model
 */
inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)
