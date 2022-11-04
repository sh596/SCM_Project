package com.example.dudeulimproject

import android.app.Activity
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dudeulimproject.view.main.adapter.MainViewPagerAdapter

object MainBindingAdapter {


    @RequiresApi(Build.VERSION_CODES.M)
    @BindingAdapter(
        value = ["position", "selectPosition"],
        requireAll = false
    )
    @JvmStatic
    fun selectItem(
        view: ImageView,
        position: Int,
        selectPosition: Int,
    ) {
        if (position == selectPosition) {
            view.setColorFilter(view.context.getColor(R.color.blue_700))
        } else {
            view.setColorFilter(view.context.getColor(R.color.black))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @BindingAdapter(value = ["position", "selectPosition"], requireAll = false)
    @JvmStatic
    fun selectItem(view: TextView, position: Int, selectPosition: Int) {
        if (position == selectPosition) {
            view.setTextColor(view.context.getColor(R.color.blue_700))
            view.typeface = ResourcesCompat.getFont(view.context, R.font.font_bold)
        } else {
            view.setTextColor(view.context.getColor(R.color.gray_700))
            view.typeface = ResourcesCompat.getFont(view.context, R.font.font_bold)
        }
    }


    @BindingAdapter("currentItem")
    @JvmStatic
    fun setAdapter(view: ViewPager2, position: Int) {
        view.currentItem = position
    }

    @BindingAdapter("setAdapter")
    @JvmStatic
    fun setAdapter(view: ViewPager2, activity: Activity) {
        view.adapter = MainViewPagerAdapter(activity as FragmentActivity)
    }

    @BindingAdapter("setPagerCallback")
    @JvmStatic
    fun setPagerChangeFunction(view: ViewPager2, function : (Int) -> Unit ) {
        view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                function(position)
            }
        })
    }
}