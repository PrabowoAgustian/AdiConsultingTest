package com.dirumahajanews.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

open class JsonParser @Inject constructor(private val gson: Gson) {

    fun <T> getObject(`object`: Any?, classOfT: Class<T>?): T? {
        return try {
            val jsonObject = gson.toJsonTree(`object`).asJsonObject
            gson.fromJson(jsonObject.toString(), classOfT)
        } catch (e: IllegalStateException) {
            null
        }
    }

    fun <T> getObjects(`object`: Any?, type: Type?): T {
        val jsonArray = gson.toJsonTree(`object`).asJsonArray
        return gson.fromJson(jsonArray, type)
    }

    fun <T> getObject(`object`: Any?, type: Type?): T {
        val jsonArray = gson.toJsonTree(`object`).asJsonObject
        return gson.fromJson(jsonArray, type)
    }

    fun <T> getObject(value: String?, classOfT: Class<T>?): T {
        return gson.fromJson(value, classOfT)
    }

    fun getObjectToJson(`object`: Any?): String {
        val jsonObject = gson.toJsonTree(`object`).asJsonObject
        return jsonObject.toString()
    }

    private fun convertMapToJsonObject(data: Map<String, String>): JsonObject {
        val jsonObject = JsonObject()
        for (key in data.keys) jsonObject.addProperty(key, data[key])
        return jsonObject
    }

    fun <T> getListObjectToJson(listOfObject: List<T>?): String {
        return gson.toJson(listOfObject)
    }

    fun <T> getListFromJsonArray(jsonArray: String?, type: Type?): T {
        return gson.fromJson(jsonArray, type)
    }

    fun <T> getObject(
        data: Map<String, String>,
        classOfT: Class<T>?
    ): T {
        val jsonObject = convertMapToJsonObject(data)
        return gson.fromJson(jsonObject.toString(), classOfT)
    }

    fun convertMaptoString(data: Map<String?, String?>?): String {
        return gson.toJson(data)
    }

    fun convertStringtoMap(data: String?): Map<String, String> {
        val type = object :
            TypeToken<Map<String?, String?>?>() {}.type
        return gson.fromJson(data, type)
    }

}