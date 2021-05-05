package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.data.DataEntity
import com.bedboy.jetmovie.data.DetailDataEntity
import com.bedboy.jetmovie.data.FeaturedEntity

object DataDummy {

    private const val imgLink: String = "https://image.tmdb.org/t/p/w400"

    fun generateMovie(): List<DataEntity> {

        val film = ArrayList<DataEntity>()

        film.add(
            DataEntity(
                "1",
                "${imgLink}/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
            )
        )

        film.add(
            DataEntity(
                "2",
                "${imgLink}/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )

        film.add(
            DataEntity(
                "3",
                "${imgLink}/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )

        film.add(
            DataEntity(
                "4",
                "${imgLink}/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )

        film.add(
            DataEntity(
                "5",
                "${imgLink}/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )

        film.add(
            DataEntity(
                "6",
                "${imgLink}/iBq4od6j1ZAvDVO7hwmHT2Xf2Bt.jpg"
            )
        )

        film.add(
            DataEntity(
                "7",
                "${imgLink}/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg"
            )
        )

        film.add(
            DataEntity(
                "8",
                "${imgLink}/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
            )
        )

        film.add(
            DataEntity(
                "9",
                "${imgLink}/ygGmAO60t8GyqUo9xYeYxSZAR3b.jpg"
            )
        )

        film.add(
            DataEntity(
                "10",
                "${imgLink}/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )

        return film
    }

    fun generateTVShow(): List<FeaturedEntity> {

        val tvShow = ArrayList<FeaturedEntity>()

        tvShow.add(
            FeaturedEntity(
                "11", "${imgLink}/suopoADq0k8YZr4dQXcU6pToj6s.jpg", "Game Of Thrones",
                "8.4", "Action"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "12", "${imgLink}/dVHeJXUzHJJGadB2wvpuAn6fsdN.jpg", "Lupin",
                "8.5", "Mystery"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "13", "${imgLink}/uAjMQlbPkVHmUahhCouANlHSDW2.jpg", "The Promised Neverland",
                "9.1", "Animation"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "14", "${imgLink}/7RySzFeK3LPVMXcPtqfZnl6u4p1.jpg", "The Big Bang Theory",
                "7.7", "Drama"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "15", "${imgLink}/5BMwFwNzSidVYArn561acqtktxv.jpg", "How I Met Your Mother",
                "8.1", "Drama"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "16", "${imgLink}/qBw99h23veY7bTccAzV4bRCg86n.jpg", "The End of the F***ing World",
                "8.3", "Drama"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "17", "${imgLink}/4pfXAnWxOfEJsUgDPW0zqzs5UWv.jpg", "Silicon Valley",
                "8.1", "Drama"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "18", "${imgLink}/8NK9un1DPUTgRzGtJHMvpdIB5Ay.jpg", "Start-Up",
                "8.4", "Drama"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "19",
                "${imgLink}/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "The Falcon and the Winter Soldier",
                "7.9", "Action"
            )
        )
        tvShow.add(
            FeaturedEntity(
                "20", "${imgLink}/57vVjteucIF3bGnZj6PmaoJRScw.jpg", "Wandavision",
                "8.4", "Action"
            )
        )

        return tvShow
    }

    fun generateDetailData(idData: String): List<DetailDataEntity> {

        val detailData = ArrayList<DetailDataEntity>()

        detailData.add(
            DetailDataEntity(
                "1",
                "A Star is Born",
                "7.6",
                "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
                "Romance, Documentary",
                "R-17",
                "nSbzyEJ8X9E"
            )
        )

        detailData.add(
            DetailDataEntity(
                "2",
                "Alita - The Battle Angel",
                "7.2",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Sci-Fic",
                "R-17",
                "w7pYhpJaJW8"
            )
        )

        detailData.add(
            DetailDataEntity(
                "3",
                "Aquaman",
                "6.9",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action",
                "17",
                "297802"
            )
        )

        detailData.add(
            DetailDataEntity(
                "4",
                "Bohemian Rhapsody",
                "8.0",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Drama, Musical, Documentary",
                "18+",
                "mP0VHJYFOAU"
            )
        )

        detailData.add(
            DetailDataEntity(
                "5",
                "Cold Pursuit",
                "5.7",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Action, Crime",
                "18",
                "0phuNQQ_gHI"
            )
        )

        detailData.add(
            DetailDataEntity(
                "6",
                "Creed",
                "7.4",
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                "Action, Crime",
                "18+",
                "Uv554B7YHk4"
            )
        )

        detailData.add(
            DetailDataEntity(
                "7",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "6.9",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Action, Magic",
                "17",
                "ViuDsy7yb8M"
            )
        )

        detailData.add(
            DetailDataEntity(
                "8",
                "Glass",
                "6.7",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "Thriller, Action",
                "17+",
                "95ghQs5AmNk"
            )
        )

        detailData.add(
            DetailDataEntity(
                "9",
                "How to Train Your Dragon",
                "7.8",
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior fathe",
                "Animation",
                "7+",
                "IT_ufPxiXl8"
            )
        )

        detailData.add(
            DetailDataEntity(
                "10",
                "The Avengers: Infinity War",
                "8.3",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Action, Sci-Fic",
                "13+",
                "6ZfuNTqbHE8"
            )
        )

        detailData.add(
            DetailDataEntity(
                "11",
                "Game Of Thrones",
                "8.4",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Action",
                "8.4",
                "bjqEWgDVPe0"
            )
        )

        detailData.add(
            DetailDataEntity(
                "12",
                "Lupin",
                "8.5",
                "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.",
                "Mystery, Action, History",
                "R-17",
                "ga0iTWXCGa0"
            )
        )

        detailData.add(
            DetailDataEntity(
                "13",
                "The Promised Neverland",
                "9.1",
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \\\"Mama,\\\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\\n\\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                "Animation, Horror, Thriller, Action, Sci-Fi",
                "R-16",
                "ApLudqucq-s"
            )
        )
        detailData.add(
            DetailDataEntity(
                "14",
                "The Big Bang Theory",
                "7.7",
                "The sitcom is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.",
                "Drama, Comedy, Romance",
                "17",
                "3g2yTcg1QFI"
            )
        )
        detailData.add(
            DetailDataEntity(
                "15",
                "How I Met Your Mother",
                "8.1",
                "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
                "Drama, Romance, Comedy",
                "17",
                "XgUmyAGwxgw"
            )
        )
        detailData.add(
            DetailDataEntity(
                "16",
                "The End of the F***ing World",
                "8.3",
                "James is 17 and is pretty sure he is a psychopath. Alyssa, also 17, is the cool and moody new girl at school. The pair make a connection and she persuades him to embark on a darkly comedic road trip in search of her real father.",
                "Drama, Comedy, Romance, Thriller",
                "17",
                "SqILm2JuHx4"
            )
        )
        detailData.add(
            DetailDataEntity(
                "17",
                "Silicon Valley",
                "8.1",
                "In the high-tech gold rush of modern Silicon Valley, the people most qualified to succeed are the least capable of handling success. Partially inspired by Mike Judge’s own experiences as a Silicon Valley engineer in the late ‘80s, Silicon Valley is an American sitcom that centers around six programmers who are living together and trying to make it big in the Silicon Valley.",
                "Drama, Comedy",
                "17",
                "69V__a49xtw"
            )
        )
        detailData.add(
            DetailDataEntity(
                "18",
                "Start-Up",
                "8.4",
                "Young entrepreneurs aspiring to launch virtual dreams into reality compete for success and love in the cutthroat world of Korea's high-tech industry.",
                "Drama, Comedy, Romance",
                "17",
                "BemKyzbLDDc"
            )
        )
        detailData.add(
            DetailDataEntity(
                "19",
                "The Falcon and the Winter Soldier",
                "7.9",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "Action",
                "R-13",
                "IWBsDaFWyTE"
            )
        )
        detailData.add(
            DetailDataEntity(
                "20",
                "Wandavision",
                "8.4",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "Action, Romance, Comedy",
                "R-13",
                "sj9J2ecsSpo"
            )
        )

        return detailData
    }
}