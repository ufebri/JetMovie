package com.bedboy.jetmovie.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bedboy.jetmovie.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_RESULT: String = "data"
        var ID_IMDB: String = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

    }
}
