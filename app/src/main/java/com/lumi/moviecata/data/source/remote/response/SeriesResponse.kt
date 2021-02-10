package com.lumi.moviecata.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SeriesResponse(

	@field:SerializedName("results")
	val results: List<SeriesItem>? = null
)