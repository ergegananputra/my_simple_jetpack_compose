package com.adielboanerge.interntest.third_screen.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegresContactResponse (
    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("per_page")
    var perPage: Int? = null,

    @SerializedName("total")
    var total: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null,
    
    @SerializedName("data")
    var data: List<RegresContact>? = null

)


