package com.cbellmont.neoland2021.request

import android.util.Log
import com.cbellmont.neoland2021.model.entity.Student
import com.cbellmont.neoland2021.request.model.StudentModel
import com.cbellmont.neoland2021.request.model.StudentModelMap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject


class GetAllUsers {

    companion object {
        private const val URL = "https://randomuser.me/api/?results=50"

        suspend fun send() : List<Student> {
            val client = OkHttpClient()
            val request = Request.Builder().url(URL).build()
            return withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                response.body?.string()?.let {
                    val jsonObject = JSONObject(it)
                    val results = jsonObject.optJSONArray("results")
                    results?.let {
                        Log.w(GetAllUsers::class.simpleName, results.toString())
                        val gson = Gson()
                        val itemType = object : TypeToken<List<StudentModel>>() {}.type
                        return@withContext StudentModelMap.map(gson.fromJson(results.toString(), itemType))
                    }
                }
                return@withContext emptyList()
            }
        }
    }
}