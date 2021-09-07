package com.example.abumax

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.abumax.databinding.ActivityMainBinding
import com.example.abumax.util.BaseDialog
import com.example.abumax.util.showToastMessage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var isFirst = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#5CC4F3")
        }
        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                Log.d("ItemSelected", "nav_home")
                binding.drawerLayout.closeDrawer(GravityCompat.START, true)
            }
            R.id.nav_gallery -> {
                Log.d("ItemSelected", "nav_galery")
               // binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                showToastMessage("In progress")
            }
            R.id.nav_slideshow -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START, true)
                Log.d("ItemSelected", "exit")
                val dialog = BaseDialog(
                    "Если вы выйдете из программы,\n  все ваши данные будут потеряны \n хотите выйти из этого приложения?",
                    "Нет",
                    "Да"
                )
                dialog.setOnDoneListener {
                    finish()
                }

                dialog.setOnCancelListener {
                    dialog.dismiss()
                }
                dialog.show(this.supportFragmentManager, "show")
            }
        }
        return true
    }

    override fun onBackPressed() {
        MainScope().launch {
            isFirst = true
            showToastMessage("")
            delay(2000)
            isFirst = false
        }
        if (isFirst) finish()
        super.onBackPressed()
    }

}