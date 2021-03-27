package com.lumi.moviecata.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seriesdetails")
data class SeriesDetailEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var tvId: Int,

        @ColumnInfo(name = "name")
        var title: String,

        @ColumnInfo(name = "overview")
        var description: String,

        @ColumnInfo(name = "poster_path")
        var imagePath: String

        )