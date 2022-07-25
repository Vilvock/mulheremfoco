package br.com.app5m.mulheremfoco.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.databinding.ActivityBriefiengBinding
import kotlinx.android.synthetic.main.activity_briefieng.*

class BriefiengActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityBriefiengBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBriefiengBinding.inflate(layoutInflater)
        setContentView(binding.root)





        navController = findNavController(R.id.briefing_nav_host)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.introContainerFrag
        )
            .build()
        setSupportActionBar(binding.briefiengToolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)

        visibilityNavElements(navController)
    }


    private fun visibilityNavElements(navController: NavController) {


        navController.addOnDestinationChangedListener { _, destination, _ ->
                 when (destination.id) {
                     R.id.introContainerFrag ->{
                         briefieng_appbar.visibility = View.GONE
                         supportActionBar?.setDisplayShowTitleEnabled(false)
     //                         supportActionBar?.setTitle("Favoritos")
                     }

                     R.id.yourHeightFragment ->{
                         briefieng_appbar.visibility = View.VISIBLE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("")
                     }
                     R.id.yourWeightFragment ->{
                         briefieng_appbar.visibility = View.VISIBLE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("")
                     }
                     R.id.yourMeasurementsFragment ->{
                         briefieng_appbar.visibility = View.VISIBLE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("")
                     }
                     R.id.yourAgeFragment ->{
                         briefieng_appbar.visibility = View.VISIBLE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("")
                     }
                     R.id.yourLevelFragment ->{
                         briefieng_appbar.visibility = View.GONE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("R")
                     }

                     else ->{
                         briefieng_appbar.visibility = View.GONE

                     }
                 }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = navController
        return when (navController.currentDestination?.id) {
          /*     R.id.yourMeasurementsFragment -> {
//                   navController.navigateUp()
                   true
               }*/
            else -> navController.navigateUp()
        }
        //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    /*override fun onBackPressed() {++++++++++++++++++++++
        when { //If drawer layout is open close that on back pressed
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }++++++++++++++++++++++++++++++
    }*/
    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }


}