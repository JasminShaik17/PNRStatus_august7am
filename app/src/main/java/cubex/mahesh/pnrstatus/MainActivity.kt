package cubex.mahesh.pnrstatus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cubex.mahesh.pnrstatus.beans.PNRStatusBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        b1.setOnClickListener {
            var r = Retrofit.Builder().
                         baseUrl("https://api.railwayapi.com/").
                         addConverterFactory(GsonConverterFactory.create()).
                        build()
            var api = r.create(PNRStatusAPI::class.java)
            var call = api.getStatusInfo()
            call.enqueue(object : Callback<PNRStatusBean> {
                override fun onFailure(call: Call<PNRStatusBean>?, t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<PNRStatusBean>?,
                                        response: Response<PNRStatusBean>?) {
                    var bean = response!!.body()
                    var list = mutableListOf<String>()
                    list.add("DOJ : "+bean!!.doj)
                    list.add("No of Psgrs : "+bean!!.passengers)
                    list.add("Train No : "+bean!!.train.number)
                    list.add("Train Name : "+bean!!.train.name)
                    list.add("From Station : "+bean!!.fromStation.name)
                    list.add("To Station : "+bean!!.toStation.name)
                    var passengers = bean.passengers
                    for(p in passengers!!){
                        list.add("Cur Status : "+p.currentStatus)
                        list.add("Book Status :"+p.bookingStatus)
                    }

                }

            })
        }
    }
}
