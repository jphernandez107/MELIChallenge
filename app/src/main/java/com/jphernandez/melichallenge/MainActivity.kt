package com.jphernandez.melichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.jphernandez.melichallenge.productList.ProductListFragment
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ProductListFragment>(R.id.fragment_container_view)
        }
    }

    fun setLoading(loading: Boolean) {
        findViewById<CircleProgressBar>(R.id.loading)?.visibility = if(loading) View.VISIBLE else View.GONE
    }
}