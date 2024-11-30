package com.abi.flagchallenge.data

import com.google.gson.annotations.SerializedName

data class Countries(

    @SerializedName("country_name")
    val countryName: String?,

    val id: Int?,
)
