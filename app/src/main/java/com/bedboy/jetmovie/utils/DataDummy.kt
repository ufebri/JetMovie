package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.data.MovieEntity

object DataDummy {

    fun generateMovie(): List<MovieEntity> {

        val film = ArrayList<MovieEntity>()

        film.add(
            MovieEntity(
                "A Star is Born",
                "7.6",
                "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
                "19 October 2018",
                "https://image.tmdb.org/t/p/w500/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Alita - The Battle Angel",
                "7.2",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "31 January 2019",
                "https://image.tmdb.org/t/p/w500/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Aquaman",
                "6.9",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "7 September 2018",
                "https://image.tmdb.org/t/p/w500/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Bohemian Rhapsody",
                "8.0",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "24 September 2018",
                "https://image.tmdb.org/t/p/w500/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Cold Pursuit",
                "5.7",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "7 February 2019",
                "https://image.tmdb.org/t/p/w500/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Creed",
                "7.4",
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                "15 November 2015",
                "https://image.tmdb.org/t/p/w500/iBq4od6j1ZAvDVO7hwmHT2Xf2Bt.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Fantastic Beasts: The Crimes of Grindelwald",
                "6.9",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "14 November 2018",
                "https://image.tmdb.org/t/p/w500/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg"
            )
        )

        film.add(
            MovieEntity(
                "Glass",
                "6.7",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "16 January 2019",
                "https://image.tmdb.org/t/p/w500/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
            )
        )

        film.add(
            MovieEntity(
                "How to Train Your Dragon",
                "7.8",
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior fathe",
                "13 September 2010",
                "https://image.tmdb.org/t/p/w500/ygGmAO60t8GyqUo9xYeYxSZAR3b.jpg"
            )
        )

        film.add(
            MovieEntity(
                "The Avengers: Infinity War",
                "8.3",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "25 April 2018",
                "https://image.tmdb.org/t/p/w500/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )

        return film
    }
}