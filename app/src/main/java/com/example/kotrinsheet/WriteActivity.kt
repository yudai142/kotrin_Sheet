package com.example.kotrinsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class WriteActivity : AppCompatActivity() {

    lateinit var writeProgressLayout:RelativeLayout
    lateinit var writeProgressBar:ProgressBar
    lateinit var edtBookName:EditText
    lateinit var edtBookAuthor:EditText
    lateinit var edtBookPrice:EditText
    lateinit var ratingBar: RatingBar
    lateinit var btnSaveToDrive:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        writeProgressLayout=findViewById(R.id.writeProgressLayout)
        writeProgressBar=findViewById(R.id.writeProgressBar)
        edtBookName=findViewById(R.id.edtBookName)
        edtBookAuthor=findViewById(R.id.edtBookAuthor)
        edtBookPrice=findViewById(R.id.edtBookPrice)
        ratingBar=findViewById(R.id.ratingBar)
        btnSaveToDrive=findViewById(R.id.btnSaveToDrive)

        writeProgressLayout.visibility= View.GONE
        writeProgressBar.visibility=View.GONE

        btnSaveToDrive.setOnClickListener {
            if(edtBookName.text.toString().isEmpty() or edtBookAuthor.text.toString().isEmpty() or
                    edtBookPrice.text.toString().isEmpty() or ratingBar.rating.toString().isEmpty()){
                Toast.makeText(this@writeActivity,"Enter All Data",Toast.LENGTH_SHORT).show()
            }else{

                val url="https://script.google.com/macros/s/AKfycbz9E6NzCQ2J95f-h6baQNnlTZ4nsQ2U1mBIxAPuHo1mwxevFVWmfmS70q_LqIvkxvG_bA/exec"
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
                        params["bookAuthor"]=edtBookAuthor.text.toString()
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