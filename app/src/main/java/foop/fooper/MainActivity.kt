package foop.fooper

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import foop.fooper.databinding.ContentMainBinding

import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ContentMainBinding
    private lateinit var viewMd: ViewModelHomePage
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.content_main)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        mBinding.rvMain!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        mBinding.rvMain.setHasFixedSize(true)
        mBinding.rvMain.setItemAnimator(androidx.recyclerview.widget.DefaultItemAnimator())
        val homeData = Gson().fromJson(loadJSONFromAsset(), HomeData::class.java);
        viewMd = ViewModelProviders.of(this).get(ViewModelHomePage::class.java)
        if (homeData != null && homeData!!.dynamicHomePage != null && homeData!!.dynamicHomePage.size > 0)
            viewMd.listLiveData.value = homeData!!.dynamicHomePage
        viewMd.listLiveData.observe(this, Observer {
            mBinding.rvMain.getAdapter()!!.addAll(getHomePageCards(this, it))
        })

        mBinding.toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.black))
        mBinding.toolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent))
        //Set a listener to know the current visible state of CollapseLayout
        mBinding.appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) {

                    mBinding.toolbarLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, android.R.color.white))
                } else {

                    mBinding.toolbarLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, android.R.color.white))
                }
            }
        })



        mBinding.toolbar.title = "Sensor"
        mBinding.toolbar.setTitleTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.black))
        setSupportActionBar(mBinding.toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        mBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    fun getHomePageCards(context: Context, dynamicHomePage: List<HomeCardEntity>?): List<Card> {

        val cards = ArrayList<Card>()
        if (dynamicHomePage != null && dynamicHomePage.size > 0) {
            for (i in dynamicHomePage.indices) {
                val card = getHomeCard(context, dynamicHomePage[i]) ?: continue
                cards.add(card)
            }
        }

        return cards
    }

    private fun getHomeCard(context: Context, homeCardEntity: HomeCardEntity): Card? {
        try {
            val className = "foop.fooper." + homeCardEntity.className
            val mProvider = Class.forName(className).newInstance() as CardProvider<*> ?: return null

            return Card.Builder(context, homeCardEntity).withProvider(mProvider)
                    .setData(homeCardEntity)
                    .endConfig()
                    .build()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val `is` = this.getAssets().open("home_options.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}
