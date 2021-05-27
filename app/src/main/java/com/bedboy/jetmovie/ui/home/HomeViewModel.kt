package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.vo.Resource

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private var mediaType = MutableLiveData<String>()

    fun selectedData(media_type: String) {
        this.mediaType.value = media_type
    }

    fun trending(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getTrending()
    fun popular(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getPopular()
    var genre: LiveData<Resource<List<GenreEntity>>> =
        Transformations.switchMap(mediaType) { mMediaType ->
            dataRepository.getGenre(mMediaType)
        }

}