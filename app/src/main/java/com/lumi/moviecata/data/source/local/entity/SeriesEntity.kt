package com.lumi.moviecata.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seriesentities")
class SeriesEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var tvId: String,

        @ColumnInfo(name = "name")
        var title: String,

        @ColumnInfo(name = "overview")
        var description: String,

        @ColumnInfo(name = "poster_path")
        var imagePath: String
)