package com.sf.jetpack.mymov.utils

import com.sf.jetpack.mymov.network.response.*

object DummyData {

    fun generateListMovieResponse(): MovieResponse {
        val listMovie = ArrayList<ListData>()
        listMovie.apply {
            add(
                ListData(
                    337404,
                    "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                    "/hjS9mH8KvRiGHgjk6VUZH7OT0Ng.jpg",
                    "2021-05-26",
                    "Cruella",
                    8.8
                )
            )
            add(
                ListData(
                    632357,
                    "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                    "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
                    "2021-03-31",
                    "The Unholy",
                    7.1
                )
            )
            add(
                ListData(
                    503736,
                    "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                    "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                    "2021-05-14",
                    "Army of the Dead",
                    6.6

                )
            )
            add(
                ListData(
                    637649,
                    "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                    "/YxopfHpsCV1oF8CZaL4M3Eodqa.jpg",
                    "2021-04-22",
                    "Wrath of Man",
                    7.9
                )
            )
            add(
                ListData(
                    460465,
                    "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                    "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                    "2021-04-07",
                    "Mortal Kombat",
                    7.6
                )
            )
            add(
                ListData(
                    399566,
                    "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                    "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                    "2021-03-24",
                    "Godzilla vs. Kong",
                    8.1
                )
            )
            add(
                ListData(
                    578701,
                    "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
                    "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
                    "2021-05-05",
                    "Those Who Wish Me Dead",
                    7.0
                )
            )
            add(
                ListData(
                    808023,
                    "A lonesome stranger with nerves of steel must track down and kill a rogue hitman to satisfy an outstanding debt. But the only information he's been given is a time and location where to find his quarry. No name. No description. Nothing.",
                    "/vXHzO26mJaOt4VO7ZFiM6No5ScT.jpg",
                    "2021-04-30",
                    "The Virtuoso",
                    6.2
                )
            )
            add(
                ListData(
                    615457,
                    "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                    "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                    "2021-03-26",
                    "Nobody",
                    8.5
                )
            )
            add(
                ListData(
                    635302,
                    "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                    "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                    "2020-10-16",
                    "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                    8.4
                )
            )

        }
        return MovieResponse(
            listMovie,
            API.MESSAGE_SUCCESS
        )
    }

    fun generateListTvShowResponse(): TvResponse {
        val listTvShow = ArrayList<TvResultList>()
        listTvShow.apply {
            add(
                TvResultList(
                    60735,
                    "The Flash",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    7.7
                )
            )
            add(
                TvResultList(
                    71712,
                    "The Good Doctor",
                    "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                    "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                    8.6
                )
            )
            add(
                TvResultList(
                    1416,
                    "Grey's Anatomy",
                    "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                    "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                    8.2
                )
            )
            add(
                TvResultList(
                    95057,
                    "Superman & Lois",
                    "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                    "/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
                    8.3
                )
            )
            add(
                TvResultList(
                    80240,
                    "The Queen of Flow",
                    "After spending seventeen years in prison unfairly, a talented songwriter seeks revenge on the men who sank her and killed her family.",
                    "/fuVuDYrs8sxvEolnYr0wCSvtyTi.jpg",
                    8.0
                )
            )
            add(
                TvResultList(
                    62286,
                    "Fear the Walking Dead",
                    "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                    "/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                    7.6
                )
            )
            add(
                TvResultList(
                    69478,
                    "The Handmaid's Tale",
                    "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
                    "/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
                    8.2
                )
            )
            add(
                TvResultList(
                    105971,
                    "The Bad Batch",
                    "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                    "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                    8.8
                )
            )
            add(
                TvResultList(
                    65334,
                    "Miraculous: Tales of Ladybug & Cat Noir",
                    "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
                    "/qxbW47zmgFyBVmZSIqD9NA1DEjT.jpg",
                    7.9
                )
            )
            add(
                TvResultList(
                    65820,
                    "Van Helsing",
                    "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
                    "/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
                    6.9
                )
            )
        }
        return TvResponse(
            listTvShow,
            API.MESSAGE_SUCCESS
        )
    }

    fun generateMovieDetailData(): MovieDetailResponse {
        return MovieDetailResponse(
            false,
            "",
            null,
            0,
            arrayListOf(),
            "",
            578701,
            "tt3215824",
            "",
            "Those Who Wish Me Dead",
            "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
            null,
            "",
            arrayListOf(),
            arrayListOf(),
            "",
            0,
            0,
            arrayListOf(),
            "",
            "Nature finds a way.",
            "",
            false,
            null,
            0,
            API.MESSAGE_SUCCESS
        )
    }

    fun generateMovieCredit(): DataCreditResponse {
        val castList = ArrayList<Cast>()
        val crewList = ArrayList<Crew>()
        castList.add(
            Cast(
                false,
                3,
                "Hannah Faber",
                "5c4fbf2cc3a368478c95cd01",
                1,
                11701,
                "Acting",
                "Angelina Jolie",
                0,
                "Angelina Jolie",
                26.586,
                "/uLdam4e9CagaM8zqGls9c6MDbWo.jpg"
            )
        )
        crewList.add(
            Crew(
                false,
                "606cad18025764002b8d0d67",
                "Writing",
                0,
                1997,
                "Screenplay",
                "Writing",
                "Charles Leavitt",
                "Charles Leavitt",
                0.643,
                "/j3Dq725qq7RPOQ01EZpAMWzygLU.jpg"
            )
        )
        return DataCreditResponse(
            castList,
            crewList,
            578701,
            API.MESSAGE_SUCCESS
        )
    }

    fun generateMovieRecommendations(): DataRecommendationsResponse {
        val resultList = ArrayList<Result>()
        resultList.add(
            Result(
                false,
                "/gUttUEqsrvaMlK5oL5TSQ54iE96.jpg",
                arrayListOf(
                    80,
                    9648,
                    53
                ),
                520663,
                "movie",
                "en",
                "The Woman in the Window",
                "",
                "An agoraphobic woman living alone in New York begins spying on her new neighbors only to witness a disturbing act of violence.",
                243.127,
                "/wcrjc1uwQaqoqtqi67Su4VCOYo0.jpg",
                "2021-05-14",
                "The Woman in the Window",
                false,
                6.224,
                759
            )
        )
        return DataRecommendationsResponse(
            1,
            resultList,
            1,
            10,
            API.MESSAGE_SUCCESS
        )
    }

    fun generateDetailTvResponse(): TvDetailResponse {
        val listGenres = ArrayList<Genre>()
        listGenres.apply {
            add(
                Genre(
                    18,
                    "Drama"
                )
            )
            add(
                Genre
                    (
                    10765,
                    "Sci-Fi & Fantasy"
                )
            )
        }

        return TvDetailResponse(
            "",
            arrayListOf(),
            arrayListOf(),
            "2014-10-07",
            listGenres,
            "",
            60735,
            true,
            arrayListOf(),
            "",
            null,
            "The Flash",
            arrayListOf(),
            null,
            0,
            0,
            arrayListOf(),
            "en",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            1028.647,
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            arrayListOf(),
            "",
            "",
            "Scripted",
            7.7,
            0,
            API.MESSAGE_FAIL
        )
    }

    fun generateTvShowCredit(): DataCreditResponse {
        val castList = ArrayList<Cast>()
        val crewList = ArrayList<Crew>()
        castList.add(
            Cast(
                false,
                null,
                "Barry Allen / The Flash",
                "53752474c3a3681efb000156",
                2,
                122616,
                "Acting",
                "Grant Gustin",
                0,
                "Grant Gustin",
                13.715,
                "/hXUu8SRjYifQAcA1anxME4vyi2U.jpg"
            )
        )
        crewList.add(
            Crew(
                false,
                "603f9782c4ad59002a83722e",
                "Production",
                0,
                1747677,
                "Producer",
                "Production",
                "Geoff Garrett",
                "Geoff Garrett",
                0.6,
                null
            )
        )
        return DataCreditResponse(
            castList,
            crewList,
            60735,
            API.MESSAGE_SUCCESS
        )
    }


    fun generateTvShowRecommendation(): DataRecommendationsResponse {
        val resultList = ArrayList<Result>()
        resultList.add(
            Result(
                false,
                "/vNnLAKmoczRlNarxyGrrw0KSOeX.jpg",
                arrayListOf(
                    80,
                    18,
                    9648,
                    10759
                ),
                1412,
                "tv",
                "en",
                "",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                204.144,
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "",
                "",
                false,
                6.641,
                4556
            )
        )
        return DataRecommendationsResponse(
            1,
            resultList,
            1,
            10,
            API.MESSAGE_SUCCESS
        )
    }
}