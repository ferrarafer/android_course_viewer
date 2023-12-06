package com.example.androidcourseviewer

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidcourseviewer.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.l1inc.paceofplaylibrary.PaceOfPlay
import com.l1inc.viewer.Course3DRenderer
import com.l1inc.viewer.Course3DViewer

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var context : Context
    private lateinit var course3DViewer: Course3DViewer

    init {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        course3DViewer = Course3DViewer(context)
        val parData: MutableMap<String, Array<Int>> = mutableMapOf(
            "key1" to arrayOf(1, 2, 3),
            "key2" to arrayOf(4, 5, 6),
            "key3" to arrayOf(7, 8, 9)
        )
        val vectorDataJsonMap: MutableMap<String, String> = mutableMapOf()
        val paceOfPlayBuilder = PaceOfPlay.Builder(
            course3DViewer.viewer.courseID,
            ""
        )
        val paceOfPlay = paceOfPlayBuilder.create()

        course3DViewer.viewer.init(
            vectorDataJsonMap,
            false,
            true,
            parData,
            null,
            false,
            paceOfPlay
        )
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setCurrentHole(currentHole: Int) {
        course3DViewer.viewer.setCurrentHole(
            currentHole,
            Course3DRenderer.DEFAULT_OVERALL_MODE,
            true,
            0
        )
    }

    override fun onDestroy() {
        course3DViewer.getViewer().onDestroy()
        super.onDestroy()
    }

}