package com.sf.jetpack.mymov.utils


object Extra {
    const val ID = "id"
    const val TYPE = "type"
    const val DATA = "data"
}

enum class TYPE {
    MOVIE,
    TV_SHOW
}

object API{
    const val MESSAGE_FAIL = "Gagal mengambil data"
    const val MESSAGE_SUCCESS = "Sukses mengambil data"
}