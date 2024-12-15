package com.example.numnewsapp

import android.os.Parcel
import android.os.Parcelable


data class News(
    var content: String = "",
    var imageUrl: String = "",
    var title: String = "")