package cubex.mahesh.pnrstatus

import cubex.mahesh.pnrstatus.beans.PNRStatusBean
import retrofit2.Call
import retrofit2.http.GET

interface PNRStatusAPI {
    @GET("v2/pnr-status/pnr/4247362564/apikey/7opzpsj744/")
    fun getStatusInfo() : Call<PNRStatusBean>
}