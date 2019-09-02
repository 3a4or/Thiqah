package com.example.mohamedashour.weatherapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Movie (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "rate") val rate: String?,
    @ColumnInfo(name = "desc") val desc: String?,
    @ColumnInfo(name = "image") val image: String?
    //@ColumnInfo(name = "id") val id: String?

)