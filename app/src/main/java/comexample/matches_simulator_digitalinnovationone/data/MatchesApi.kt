package comexample.matches_simulator_digitalinnovationone.data

import comexample.matches_simulator_digitalinnovationone.domain.Match
import retrofit2.Call
import retrofit2.http.GET

interface MatchesApi {

    @GET("matches.json")
    fun getMatches(): Call<List<Match>>


}
