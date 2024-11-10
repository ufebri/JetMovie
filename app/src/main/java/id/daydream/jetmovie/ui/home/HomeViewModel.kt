package id.daydream.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.daydream.jetmovie.data.source.DataRepository
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.data.source.local.entity.GenreEntity
import id.daydream.jetmovie.vo.Resource

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun trending(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getTrending()
    fun popular(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getPopular()
    fun genre(mediaType: String): LiveData<Resource<List<GenreEntity>>> =
        dataRepository.getGenre(mediaType)

}