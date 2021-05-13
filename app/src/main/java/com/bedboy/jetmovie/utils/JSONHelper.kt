package com.bedboy.jetmovie.utils

import android.content.Context
import com.bedboy.jetmovie.data.source.remote.response.GenreResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JSONHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadGenre(): List<GenreResponse> {

        val list = ArrayList<GenreResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("genre.json").toString())
            val listArray = responseObject.getJSONArray("genre")
            for (i in 0 until listArray.length()) {
                val course = listArray.getJSONObject(i)

                val id = course.getInt("id")
                val name = course.getString("name")

                val genreResponse = GenreResponse(id, name)
                list.add(genreResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}