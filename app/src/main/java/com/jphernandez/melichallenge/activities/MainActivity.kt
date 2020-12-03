package com.jphernandez.melichallenge.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.jphernandez.melichallenge.R
import com.jphernandez.melichallenge.productList.ProductListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ProductListFragment>(R.id.fragment_container_view)
        }
    }
}