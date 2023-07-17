package com.duhan.satelliteinfo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Matcher


fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
    checkNotNull(itemMatcher)
    return object : BoundedMatcher<View?, RecyclerView?>(RecyclerView::class.java) {
        override fun describeTo(description: org.hamcrest.Description?) {
            description?.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item?.findViewHolderForAdapterPosition(position)
                ?: // has no item on such position
                return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }
}
