package com.whaletail.uklon.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.whaletail.uklon.test.model.Post
import com.whaletail.uklon.test.mvp.postDetails.PostDetailsActivity
import com.whaletail.uklon.test.mvp.posts.PostsActivity
import com.whaletail.uklon.test.mvp.posts.PostsAdapter
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    companion object {
        const val ITEM_POSITION_TO_CLICK: Int = 0
    }

    @JvmField
    @Rule
    var mActivityRule: IntentsTestRule<PostsActivity> = IntentsTestRule(
            PostsActivity::class.java)

    /**
     * Checks are POST_ID and USER_ID passed to PostDetailsActivity from PostsAdapter on click first element
     */
    @Test
    fun onClick_PostsAdapter_firstElement_intentCheck() {
        val post = getPost(withId(R.id.rv_posts), ITEM_POSITION_TO_CLICK)
        onView(withId(R.id.rv_posts))
                .perform(actionOnItemAtPosition<PostsAdapter.PostHolder>(ITEM_POSITION_TO_CLICK, click()))


        intended(allOf(
                hasComponent(PostDetailsActivity::class.java.name),
                hasExtra(PostDetailsActivity.POST_ID, post?.id),
                hasExtra(PostDetailsActivity.USER_ID, post?.userId)))
    }

    private fun getPost(matcher: Matcher<View>, position: Int): Post? {
        var post: Post? = null
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(RecyclerView::class.java)
            }

            override fun getDescription(): String {
                return "getting post from a PostViewHolder"
            }

            override fun perform(uiController: UiController, view: View) {
                val recyclerView = view as RecyclerView
                post = (recyclerView.adapter as PostsAdapter).posts[position]
            }
        })
        return post
    }

}
