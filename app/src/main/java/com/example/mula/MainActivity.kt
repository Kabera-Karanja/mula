package com.example.mula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })

        // Find the button by its ID
        val buttonStartActivity: Button = findViewById(R.id.btn_login)

        // Set a click listener for the button
        buttonStartActivity.setOnClickListener {
            // Start the new activity when the button is clicked
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // Find the button by its ID
        val buttonRegisterActivity: Button = findViewById(R.id.btn_register)

        // Set a click listener for the button
        buttonRegisterActivity.setOnClickListener {
            // Start the new activity when the button is clicked
            val intent2 = Intent(this, Register::class.java)
            startActivity(intent2)
        }

    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)

    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 3000)
    }

    private val runnable = Runnable{
        viewPager2.currentItem= viewPager2.currentItem+1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r= 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)

    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.slide)
        imageList.add(R.drawable.slide_1)
        imageList.add(R.drawable.slide_2)

        adapter = ImageAdapter(imageList, viewPager2)


        viewPager2.adapter= adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

}