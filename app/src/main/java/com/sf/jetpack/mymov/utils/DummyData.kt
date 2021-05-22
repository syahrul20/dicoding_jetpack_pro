package com.sf.jetpack.mymov.utils

import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.data.TvShowsData

object DummyData {

    fun generateMovieListData(): List<Movie> {
        val movies = ArrayList<Movie>()
        movies.apply {
            add(
                Movie(
                    1,
                    "A Star Is Born",
                    "@drawable/poster_a_start_is_born",
                    "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                    "2018",
                    "nSbzyEJ8X9E",
                    "Bradley Cooper"
                )
            )
            add(
                Movie(
                    2,
                    "Alita: Battle Angel",
                    "@drawable/poster_alita",
                    "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                    "2019",
                    "w7pYhpJaJW8",
                    "Robert Rodriguez"
                )
            )
            add(
                Movie(
                    3,
                    "Bohemian Rhapsody",
                    "@drawable/poster_bohemian",
                    "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                    "2018",
                    "mP0VHJYFOAU",
                    "Bryan Singer"
                )
            )
            add(
                Movie(
                    4,
                    "How to Train Your Dragon: The Hidden World",
                    "@drawable/poster_how_to_train",
                    "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                    "2019",
                    null,
                    "Dean DeBlois"
                )
            )
            add(
                Movie(
                    5,
                    "Avengers: Infinity War",
                    "@drawable/poster_infinity_war",
                    "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                    "2018",
                    "QwievZ1Tx-8",
                    "Anthony Russo"
                )
            )
            add(
                Movie(
                    6,
                    "Mortal Engines",
                    "@drawable/poster_mortal_engines",
                    "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                    "2018",
                    "dqYyKztOZOw",
                    "Christian Rivers"
                )
            )
            add(
                Movie(
                    7,
                    "Overlord ",
                    "@drawable/poster_overlord",
                    "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                    "2018",
                    "USPd0vX2sdc",
                    "Julius Avery"
                )
            )
            add(
                Movie(
                    8,
                    "Ralph Breaks the Internet",
                    "@drawable/poster_ralph",
                    "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                    "2018",
                    "T73h5bmD8Dc",
                    "Phil Johnston, Rich Moore"
                )
            )
            add(
                Movie(
                    9,
                    "Robin Hood ",
                    "@drawable/poster_robin_hood",
                    "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                    "2018",
                    "tJfDBSWYqU8",
                    "Otto Bathurst"
                )
            )
            add(
                Movie(
                    10,
                    "T-34 ",
                    "@drawable/poster_t34",
                    "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                    "2018",
                    "D1UDvi5xL9w",
                    "Alexey Sidorov"
                )
            )
        }
        return movies
    }

    fun generateTvShowListData(): List<TvShowsData> {
        val tvShows = ArrayList<TvShowsData>()
        tvShows.apply {
            add(
                TvShowsData(
                    1,
                    "Arrow",
                    "@drawable/poster_arrow",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    "2012",
                    "hTv13EjlLNg",
                    "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg"
                )
            )
            add(
                TvShowsData(
                    2,
                    "Doom Patrol",
                    "@drawable/poster_doom_patrol",
                    "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                    "2019",
                    "6tTM9nbRk5A",
                    "Jeremy Carver"
                )
            )
            add(
                TvShowsData(
                    3,
                    "Family Guy",
                    "@drawable/poster_family_guy",
                    "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                    "1999",
                    "vIKlVgw_b90",
                    "Seth MacFarlane"
                )
            )
            add(
                TvShowsData(
                    4,
                    "The Flash",
                    "@drawable/poster_flash",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    "2014",
                    "Yj0l7iGKh8g",
                    "Greg Berlanti, Geoff Johns, Andrew Kreisberg"
                )
            )
            add(
                TvShowsData(
                    5,
                    "Gotham",
                    "@drawable/poster_gotham",
                    "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                    "2014",
                    null,
                    "Bruno Heller"
                )
            )
            add(
                TvShowsData(
                    6,
                    "Hanna",
                    "@drawable/poster_hanna",
                    "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                    "2019",
                    "82gItcSb8mI",
                    "David Farr"
                )
            )
            add(
                TvShowsData(
                    7,
                    "Marvel's Iron Fist",
                    "@drawable/poster_iron_fist",
                    "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                    "2017",
                    "f9OKL5no-S0",
                    "Scott Buck"
                )
            )
            add(
                TvShowsData(
                    8,
                    "NCIS",
                    "@drawable/poster_ncis",
                    "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                    "2003",
                    "fRfpzhmQGFY",
                    "Donald P.Bellisario, Don McGill"
                )
            )
            add(
                TvShowsData(
                    9,
                    "Riverdale",
                    "@drawable/poster_riverdale",
                    "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                    "2017",
                    "9XmFTADupMc",
                    "Roberto Aguirre-Sacasa"
                )
            )
            add(
                TvShowsData(
                    10,
                    "Supergirl",
                    "@drawable/poster_supergirl",
                    "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                    "2015",
                    "Mh8MYFadTmQ",
                    "Greg Berlanti, Ali Adler, Andrew Kreisberg"
                )
            )
        }
        return tvShows
    }
}