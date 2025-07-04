package com.nldining.app.network

import com.nldining.app.models.NominatimResponse
import com.nldining.app.models.Restaurant
import com.nldining.app.models.Review
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("Restaurants/filter")
    suspend fun getRestaurants(
        @Query("plaats") plaats: String? = null,
        @Query("categorie") categorie: String? = null,
        @Query("tags") tags: String? = null,
        @Query("minScoreOverall") minScoreOverall: Double? = null,
        @Query("minScoreFood") minScoreFood: Double? = null,
        @Query("minScoreService") minScoreService: Double? = null,
        @Query("minScoreAmbiance") minScoreAmbiance: Double? = null
    ): List<Restaurant>

    @POST("Restaurants/{id}/review")
    fun submitReview(
        @Path("id") restaurantId: Int,
        @Body review: Review
    ): Call<Void>

    @Headers("User-Agent: ButtonAdres/1.0 (test@mail.com)")
    @GET("search")
    suspend fun searchLocation(
        @Query("q") location: String,
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 1
    ): List<NominatimResponse>
}