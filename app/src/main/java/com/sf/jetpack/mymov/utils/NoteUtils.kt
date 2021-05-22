package com.sf.jetpack.mymov.utils


/*
   =========================================
    | NOTES UNIT & INSTRUMENTATION TESTING |
   ========================================

   ------------
   UNIT TESTING
   ------------
   #MovieViewModelTest:
   - Memastikan data tidak null
   - Memastikan banyak data sesuai dengan yang diharapkan

   #TvShowViewModelTest:
   - Memastikan data tidak null
   - Memastikan banyak data sesuai dengan yang diharapkan

   #DetailViewModelTest:
   - Memastikan data tidak null
   - Memastikan data yang tampil sesuai dengan yang diharapkan


   ------------------------
   INSTRUMENTATION TESTING
   ------------------------
   #MENAMPILKAN LIST DATA DARI MOVIE
    - memastikan rv_movie dalam keadaan tampil
    - scroll rv_movie ke posisi data terakhir

   #MENAMPILKAN DATA DETAIL MOVIE
    - memberikan action ke item pertama dari rv_movie
    - memastikan komponen TextView untuk judul film dalam keadaan tampil
    - memastikan komponen TextView untuk judul film sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk deskripsi film sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk tahun publish film sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk direktur/sutradara film sesuai dengan yang sudah diharapkan
    - memastikan komponen WebView untuk video trailer dalam keadaan tampil
    - memastikan komponen TextView yang menandakan "link trailer tidak tersedia" dalam keadaan tidak tampil

    #MEMERIKSA KONDISI KETIKA LINK TRAILER MOVIE TIDAK ADA
    - memberikan action ke item ke empat dari rv_movie
    - memastikan komponen TextView untuk judul film dalam keadaan tampil
    - memastikan komponen TextView untuk judul film sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk deskripsi film sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk tahun publish film sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk direktur/sutradara film sesuai dengan yang sudah diharapkan
    - memastikan komponen WebView untuk video trailer dalam keadaan tidak tampil
    - memastikan komponen TextView yang menandakan "link trailer tidak tersedia" sudah dalam keadaan tampil


   #MENAMPILKAN LIST DATA DARI TV SHOW
    - menekan tablayout dengan teks "TV Show"
    - memastikan rv_tv_show dalam keadaan tampil
    - scroll rv_tv_show ke posisi data terakhir

   #MENAMPILKAN DETAIL TV SHOW
    - menekan tablayout dengan teks "TV Show"
    - memberikan action ke item pertama dari rv_movie
    - memastikan komponen TextView untuk judul tv show dalam keadaan tampil
    - memastikan komponen TextView untuk judul tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk deskripsi tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk tahun publish tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk creator/sutradara tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen WebView untuk video trailer dalam keadaan tampil
    - memastikan komponen TextView yang menandakan "link trailer tidak tersedia" dalam keadaan tidak tampil

     #MEMERIKSA KETIKA KONDISI LINK TRAILER TV SHOW TIDAK ADA
    - menekan tablayout dengan teks "TV Show"
    - memberikan action ke item ke lima dari rv_tv_show
    - memastikan komponen TextView untuk judul tv show dalam keadaan tampil
    - memastikan komponen TextView untuk judul tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk deskripsi tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk tahun publish tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen TextView untuk creator/sutradara tv show sesuai dengan yang sudah diharapkan
    - memastikan komponen WebView untuk video trailer dalam keadaan tidak tampil
    - memastikan komponen TextView yang menandakan "link trailer tidak tersedia" sudah dalam keadaan tampil

 */