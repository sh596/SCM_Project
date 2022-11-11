package com.example.dudeulimproject.view.main

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.dudeulimproject.R
import com.example.dudeulimproject.view.main.adapter.ExploreInterViewAdapter
import com.example.dudeulimproject.view.main.adapter.MainViewPagerAdapter
import com.example.dudeulimproject.data.ExploreInterViewData
import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.view.main.adapter.RequestInterViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MainBindingAdapter {

    @RequiresApi(Build.VERSION_CODES.M)
    @BindingAdapter(
        value = ["position", "selectPosition"],
        requireAll = false
    )
    @JvmStatic
    fun selectBottomNavigationItem(
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
    fun selectBottomNavigationItem(view: TextView, position: Int, selectPosition: Int) {
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

    @BindingAdapter("bindRequestInterViewAdapter")
    @JvmStatic
    fun bindRequestInterViewAdapter(
        view: RecyclerView,
        requestInterViewList: ObservableArrayList<RequestInterViewData>?
    ) {
        val adapter = view.adapter as RequestInterViewAdapter? ?: return
        if (requestInterViewList != null) {
            Log.d(TAG, "setExploreList: $requestInterViewList")
            adapter.submitList(requestInterViewList.toMutableList())
        }
    }

    @BindingAdapter("setPagerCallback")
    @JvmStatic
    fun setPagerChangeFunction(view: ViewPager2, function: (Int) -> Unit) {
        view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                function(position)
            }
        })
    }

    @BindingAdapter("setExploreList")
    @JvmStatic
    fun setExploreList(
        view: RecyclerView,
        exploreInterViewList: ObservableArrayList<ExploreInterViewData>?
    ) {
        val adapter = view.adapter as ExploreInterViewAdapter? ?: return
        if (exploreInterViewList != null) {
            Log.d(TAG, "setExploreList: $exploreInterViewList")
            adapter.submitList(exploreInterViewList.toMutableList())
        }
    }

    @BindingAdapter("setSwipeRefresh")
    @JvmStatic
    fun setSwipeRefresh(
        view: SwipeRefreshLayout, refresh: () -> Unit
    ) {
        view.setOnRefreshListener {
            CoroutineScope(Dispatchers.Main).launch {
                launch {
                    refresh()
                }.join()
                view.isRefreshing = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @BindingAdapter(
        value = ["categoryPosition", "selectCategoryPosition"],
        requireAll = false
    )
    @JvmStatic
    fun selectCategoryItem(
        view: TextView,
        position: Int,
        selectPosition: Int,
    ) {
        if (position == selectPosition) {
            view.setBackgroundColor(view.context.getColor(R.color.blue_700))
            view.setTextColor(view.context.getColor(R.color.white))
        } else {
            view.setBackgroundColor(view.context.getColor(R.color.gray_300))
            view.setTextColor(view.context.getColor(R.color.gray_700))
        }
    }

    @BindingAdapter("bindItemSelectFunction")
    @JvmStatic
    fun bindItemSelectFunction(
        view: Spinner,
        function: (Int) -> Unit,
    ) {
        view.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                function(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }


}
