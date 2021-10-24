package com.example.recyclerviewapi

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        val names = ArrayList<ActivityDetails>()

        recyclerView.adapter = RVadapter(this, names)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiInterface = APIClient().GetClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("PLEASE Wait")
        progressDialog.show()

        if(apiInterface != null){
            apiInterface.Getname()?.enqueue(object: Callback<ArrayList<ActivityDetails>>{
                override fun onResponse(
                    call: Call<ArrayList<ActivityDetails>>,
                    response: Response<ArrayList<ActivityDetails>>
                ) {
                    progressDialog.dismiss()
                    for(name in response.body()!!){
                        names.add(name)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ArrayList<ActivityDetails>>, t: Throwable) {
                    progressDialog.dismiss()
                }
            })
        }
    }
}