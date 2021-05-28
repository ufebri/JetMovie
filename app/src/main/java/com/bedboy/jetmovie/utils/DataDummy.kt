package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.BuildConfig
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos

object DataDummy {

    /**
     * There two method:
     * - generateX for local
     * - generateRemote for Remote
     */

    private const val imgLink: String = BuildConfig.IMGLINK

    fun generateVideo(): List<VideoEntity> =
        arrayListOf(
            VideoEntity("1", "nSbzyEJ8X9E"),
            VideoEntity("2", "w7pYhpJaJW8"),
            VideoEntity("3", "297802"),
            VideoEntity("4", "mP0VHJYFOAU"),
            VideoEntity("5", "0phuNQQ_gHI"),
            VideoEntity("6", "nSbzyEJ8X9E"),
            VideoEntity("7", "w7pYhpJaJW8"),
            VideoEntity("8", "297802"),
            VideoEntity("9", "mP0VHJYFOAU"),
            VideoEntity("10", "0phuNQQ_gHI"),
            VideoEntity("11", "bjqEWgDVPe0"),
            VideoEntity("12", "ga0iTWXCGa0"),
            VideoEntity("13", "3g2yTcg1QFI"),
            VideoEntity("14", "XgUmyAGwxgw"),
            VideoEntity("15", "SqILm2JuHx4"),
            VideoEntity("16", "bjqEWgDVPe0"),
            VideoEntity("17", "ga0iTWXCGa0"),
            VideoEntity("18", "3g2yTcg1QFI"),
            VideoEntity("19", "XgUmyAGwxgw"),
            VideoEntity("20", "SqILm2JuHx4"),
        )

    fun generateData(): List<DataMovieTVEntity> {

        val detailData = ArrayList<DataMovieTVEntity>()

        detailData.add(
            DataMovieTVEntity(
                "1",
                "$imgLink/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "A Star is Born",
                7.6,
                "Drama",
                null,
                "movie",
                "",
                "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "2",
                "$imgLink/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "Alita - The Battle Angel",
                7.2,
                "Drama",
                null,
                "movie",
                "",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "3",
                "$imgLink/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Aquaman",
                6.9,
                "Drama",
                null,
                "movie",
                "",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "4",
                "$imgLink/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                8.0,
                "Drama",
                null,
                "movie",
                "",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "5",
                "$imgLink/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                5.7,
                "Drama",
                null,
                "movie",
                "",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "6",
                "$imgLink/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "A Star is Born",
                7.6,
                "Drama",
                null,
                "movie",
                "",
                "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "7",
                "$imgLink/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "Alita - The Battle Angel",
                7.2,
                "Drama",
                null,
                "movie",
                "",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "8",
                "$imgLink/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Aquaman",
                6.9,
                "Drama",
                null,
                "movie",
                "",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "9",
                "$imgLink/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                8.0,
                "Drama",
                null,
                "movie",
                "",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "10",
                "$imgLink/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                5.7,
                "Drama",
                null,
                "movie",
                "",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "11",
                "",
                null,
                8.4,
                "Drama",
                "Game Of Thrones",
                "tv",
                "$imgLink/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "12",
                "",
                null,
                8.5,
                "Drama",
                "Lupin",
                "tv",
                "$imgLink/dVHeJXUzHJJGadB2wvpuAn6fsdN.jpg",
                "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "13",
                "",
                null,
                7.7,
                "Drama",
                "The Big Bang Theory",
                "tv",
                "$imgLink/7RySzFeK3LPVMXcPtqfZnl6u4p1.jpg",
                "The sitcom is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.",
            )
        )
        detailData.add(
            DataMovieTVEntity(
                "14",
                "",
                null,
                8.1,
                "Drama",
                "How I Met Your Mother",
                "tv",
                "$imgLink/5BMwFwNzSidVYArn561acqtktxv.jpg",
                "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
            )
        )
        detailData.add(
            DataMovieTVEntity(
                "15",
                "",
                null,
                8.3,
                "Drama",
                "The End of the F***ing World",
                "tv",
                "$imgLink/qBw99h23veY7bTccAzV4bRCg86n.jpg",
                "James is 17 and is pretty sure he is a psychopath. Alyssa, also 17, is the cool and moody new girl at school. The pair make a connection and she persuades him to embark on a darkly comedic road trip in search of her real father.",
            )
        )
        detailData.add(
            DataMovieTVEntity(
                "16",
                "",
                null,
                8.4,
                "Drama",
                "Game Of Thrones",
                "tv",
                "$imgLink/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "17",
                "",
                null,
                8.5,
                "Drama",
                "Lupin",
                "tv",
                "$imgLink/dVHeJXUzHJJGadB2wvpuAn6fsdN.jpg",
                "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.",
            )
        )

        detailData.add(
            DataMovieTVEntity(
                "18",
                "",
                null,
                7.7,
                "Drama",
                "The Big Bang Theory",
                "tv",
                "$imgLink/7RySzFeK3LPVMXcPtqfZnl6u4p1.jpg",
                "The sitcom is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.",
            )
        )
        detailData.add(
            DataMovieTVEntity(
                "19",
                "",
                null,
                8.1,
                "Drama",
                "How I Met Your Mother",
                "tv",
                "$imgLink/5BMwFwNzSidVYArn561acqtktxv.jpg",
                "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
            )
        )
        detailData.add(
            DataMovieTVEntity(
                "20",
                "",
                null,
                8.3,
                "Drama",
                "The End of the F***ing World",
                "tv",
                "$imgLink/qBw99h23veY7bTccAzV4bRCg86n.jpg",
                "James is 17 and is pretty sure he is a psychopath. Alyssa, also 17, is the cool and moody new girl at school. The pair make a connection and she persuades him to embark on a darkly comedic road trip in search of her real father.",
            )
        )

        return detailData
    }

    fun generateDetailDataMovie(): DataMovieTVEntity {
        return DataMovieTVEntity(
            "1",
            "$imgLink/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            "A Star is Born",
            7.6,
            "Drama",
            null,
            "movie",
            "",
            "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
            false
        )
    }

    fun generateDetailDataTVShow(): DataMovieTVEntity {
        return DataMovieTVEntity(
            "14",
            "",
            null,
            8.1,
            "Drama",
            "How I Met Your Mother",
            "tv",
            "$imgLink/5BMwFwNzSidVYArn561acqtktxv.jpg",
            "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
            false
        )
    }

    fun generateGenre(): List<GenreEntity> =
        arrayListOf(
            GenreEntity(10759, "Action & Adventure"),
            GenreEntity(16, "Animation"),
            GenreEntity(35, "Comedy"),
            GenreEntity(28, "Action"),
            GenreEntity(12, "Adventure")
        )

    fun generateRemoteData(): List<ResultsItem> {

        val detailData = ArrayList<ResultsItem>()

        detailData.add(
            ResultsItem(
                "602269",
                "$imgLink/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "A Star is Born",
                7.6,
                listOf(28),
                null,
                "movie",
                "",
                "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "1",
                "$imgLink/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "Alita - The Battle Angel",
                7.2,
                listOf(12),
                null,
                "movie",
                "",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "3",
                "$imgLink/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Aquaman",
                6.9,
                listOf(16),
                null,
                "movie",
                "",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "4",
                "$imgLink/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                8.0,
                listOf(28, 12),
                null,
                "movie",
                "",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "5",
                "$imgLink/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                5.7,
                listOf(28, 16),
                null,
                "movie",
                "",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "6",
                "$imgLink/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "A Star is Born",
                7.6,
                listOf(12, 16),
                null,
                "movie",
                "",
                "After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "7",
                "$imgLink/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "Alita - The Battle Angel",
                7.2,
                listOf(28, 12, 16),
                null,
                "movie",
                "",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "8",
                "$imgLink/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Aquaman",
                6.9,
                listOf(28, 12, 16),
                null,
                "movie",
                "",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "9",
                "$imgLink/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                8.0,
                listOf(28, 12, 16),
                null,
                "movie",
                "",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "10",
                "$imgLink/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                5.7,
                listOf(28, 12, 16),
                null,
                "movie",
                "",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "11",
                "",
                null,
                8.4,
                listOf(10759, 16, 35),
                "Game Of Thrones",
                "tv",
                "$imgLink/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "12",
                "",
                null,
                8.5,
                listOf(10759, 16, 35),
                "Lupin",
                "tv",
                "$imgLink/dVHeJXUzHJJGadB2wvpuAn6fsdN.jpg",
                "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "13",
                "",
                null,
                7.7,
                listOf(10759, 16, 35),
                "The Big Bang Theory",
                "tv",
                "$imgLink/7RySzFeK3LPVMXcPtqfZnl6u4p1.jpg",
                "The sitcom is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.",
                null
            )
        )
        detailData.add(
            ResultsItem(
                "14",
                "",
                null,
                8.1,
                listOf(10759, 16, 35),
                "How I Met Your Mother",
                "tv",
                "$imgLink/5BMwFwNzSidVYArn561acqtktxv.jpg",
                "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
                null
            )
        )
        detailData.add(
            ResultsItem(
                "15",
                "",
                null,
                8.3,
                listOf(10759, 16, 35),
                "The End of the F***ing World",
                "tv",
                "$imgLink/qBw99h23veY7bTccAzV4bRCg86n.jpg",
                "James is 17 and is pretty sure he is a psychopath. Alyssa, also 17, is the cool and moody new girl at school. The pair make a connection and she persuades him to embark on a darkly comedic road trip in search of her real father.",
                null
            )
        )
        detailData.add(
            ResultsItem(
                "16",
                "",
                null,
                8.4,
                listOf(10759, 16, 35),
                "Game Of Thrones",
                "tv",
                "$imgLink/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "17",
                "",
                null,
                8.5,
                listOf(10759, 16, 35),
                "Lupin",
                "tv",
                "$imgLink/dVHeJXUzHJJGadB2wvpuAn6fsdN.jpg",
                "Inspired by the adventures of Arsène Lupin, gentleman thief Assane Diop sets out to avenge his father for an injustice inflicted by a wealthy family.",
                null
            )
        )

        detailData.add(
            ResultsItem(
                "18",
                "",
                null,
                7.7,
                listOf(10759, 16, 35),
                "The Big Bang Theory",
                "tv",
                "$imgLink/7RySzFeK3LPVMXcPtqfZnl6u4p1.jpg",
                "The sitcom is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.",
                null
            )
        )
        detailData.add(
            ResultsItem(
                "19",
                "",
                null,
                8.1,
                listOf(10759, 16, 35),
                "How I Met Your Mother",
                "tv",
                "$imgLink/5BMwFwNzSidVYArn561acqtktxv.jpg",
                "A father recounts to his children - through a series of flashbacks - the journey he and his four best friends took leading up to him meeting their mother.",
                null
            )
        )
        detailData.add(
            ResultsItem(
                "20",
                "",
                null,
                8.3,
                listOf(10759, 16, 35),
                "The End of the F***ing World",
                "tv",
                "$imgLink/qBw99h23veY7bTccAzV4bRCg86n.jpg",
                "James is 17 and is pretty sure he is a psychopath. Alyssa, also 17, is the cool and moody new girl at school. The pair make a connection and she persuades him to embark on a darkly comedic road trip in search of her real father.",
                null
            )
        )

        return detailData
    }

    fun generateRemoteVideo(): List<ResultsVideos> =
        arrayListOf(
            ResultsVideos("", "", "", "nSbzyEJ8X9E"),
            ResultsVideos("", "", "", "w7pYhpJaJW8"),
            ResultsVideos("", "", "", "297802"),
            ResultsVideos("", "", "", "mP0VHJYFOAU"),
            ResultsVideos("", "", "", "0phuNQQ_gHI"),
            ResultsVideos("", "", "", "nSbzyEJ8X9E"),
            ResultsVideos("", "", "", "w7pYhpJaJW8"),
            ResultsVideos("", "", "", "297802"),
            ResultsVideos("", "", "", "mP0VHJYFOAU"),
            ResultsVideos("", "", "", "0phuNQQ_gHI"),
            ResultsVideos("", "", "", "bjqEWgDVPe0"),
            ResultsVideos("", "", "", "ga0iTWXCGa0"),
            ResultsVideos("", "", "", "3g2yTcg1QFI"),
            ResultsVideos("", "", "", "XgUmyAGwxgw"),
            ResultsVideos("", "", "", "SqILm2JuHx4"),
            ResultsVideos("", "", "", "bjqEWgDVPe0"),
            ResultsVideos("", "", "", "ga0iTWXCGa0"),
            ResultsVideos("", "", "", "3g2yTcg1QFI"),
            ResultsVideos("", "", "", "XgUmyAGwxgw"),
            ResultsVideos("", "", "", "SqILm2JuHx4"),
        )

    fun generateRemoteGenre(): List<ResultsGenre> =
        arrayListOf(
            ResultsGenre(10759, "Action & Adventure"),
            ResultsGenre(16, "Animation"),
            ResultsGenre(35, "Comedy"),
            ResultsGenre(28, "Action"),
            ResultsGenre(12, "Adventure")
        )
}