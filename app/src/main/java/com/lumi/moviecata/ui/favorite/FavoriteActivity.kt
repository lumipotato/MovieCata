package com.lumi.moviecata.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lumi.moviecata.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val favoritePagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.favViewPager.adapter = favoritePagerAdapter
        activityFavoriteBinding.favTabs.setupWithViewPager(activityFavoriteBinding.favViewPager)

        supportActionBar?.elevation = 0f
    }
}