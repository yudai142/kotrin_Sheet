package com.example.kotrinsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class WriteActivity : AppCompatActivity() {

    lateinit var writeProgressLayout:RelativeLayout
    lateinit var writeProgressBar:ProgressBar
    lateinit var edtBookName:EditText
    lateinit var edtBookAuter:EditText
    lateinit var edtBookPrice:EditText
    lateinit var ratingBar: RatingBar
    lateinit var btnSaveToDrive:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        writeProgressLayout=findViewById(R.id.writeProgressLayout)
        writeProgressBar=findViewById(R.id.writeProgressBar)
        edtBookName=findViewById(R.id.edtBookName)
        edtBookAuter=findViewById(R.id.edtBookAuthor)
        edtBookPrice=findViewById(R.id.edtBookPrice)
        ratingBar=findViewById(R.id.ratingBar)
        btnSaveToDrive=findViewById(R.id.btnSaveToDrive)

        writeProgressLayout.visibility= View.GONE
        writeProgressBar.visibility=View.GONE

        btnSaveToDrive.setOnClickListener {
            if(edtBookName.text.toString().isEmpty() or edtBookAuter.text.toString()isEmpty() or
                    edtBookPrice.text.toString().isEmpty() or ratingBar.rating.toString().isEmpty()){
                Toast.makeText(this@writeActivity,"Enter All Data",Toast.LENGTH_SHORT).show()
            }else{

                val url=""
                val stringRequest=object :StringRequest(Request.Method.POST,url,
                    Response.Listener {
                        Toast.makeText(this@writeActivity,it.toString(),Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener {
                        Toast.makeText(this@writeActivity,it.toString(),Toast.LENTH_SHORT).show()
                    }){
                    override fun getParams(): MutableMap<String, String> {
                        val params=HashMap<StringRequest>()
                        params["bookName"]=edtBookName.text.toString()
                        params["bookAuthor"]=edtBookAuter.text.toString()
                        params["bookPrice"]=edtBookPrice.text.toString()
                        params["bookRating"]=ratingBar.rating.toString()
                        return params
                    }
                }
                val queue= Volley.newRequestQueue(this@writeActivity)
                queue.add(stringRequest)
            }
        }

    }
}