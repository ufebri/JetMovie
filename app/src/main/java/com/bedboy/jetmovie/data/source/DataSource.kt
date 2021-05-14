package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos

interface DataSource {

    fun getTrending(): LiveData<List<ResultsItem>>
    fun getPopular(): LiveData<List<ResultsItem>>
    fun getVideoDetail(media_type: String, id: String): LiveData<List<ResultsVideos>>
    fun getGenre(media_type: String): LiveData<List<ResultsGenre>>
}