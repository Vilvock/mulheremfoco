package br.com.app5m.mulheremfoco.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.app5m.mulheremfoco.R
import br.com.app5m.mulheremfoco.databinding.ActivityMainBinding
import egolabsapps.basicodemine.videolayout.VideoLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding


    lateinit var frameLayout: FrameLayout
    lateinit var videoLayout: VideoLayout

    private val viewModel: MainActViewModel by viewModels()

    private lateinit var videoObserver: Observer<Boolean>

    init {
        lifecycleScope.launchWhenStarted {

            viewModel.startVideoLayout(frameLayout, videoLayout, this@MainActivity)
                .invokeOnCompletion {

                    try {
                        viewModel.isPlaying.value = videoLayout.mediaPlayer.isPlaying

                        videoLayout.mediaPlayer.setOnCompletionListener {

                            Handler(Looper.getMainLooper()).postDelayed({

                                videoLayout.alpha = 1f

                                videoLayout.animate().alpha(0f).duration = 500

                                Handler(Looper.getMainLooper()).postDelayed({


                                    frameLayout.visibility = View.INVISIBLE

                                    startLL.visibility = View.VISIBLE
                                }, 500)

                            }, 1000)

                        }

                    } catch (e: Exception) {
                        e
                    }finally {
                   /*     frameLayout.visibility = View.INVISIBLE

                        startLL.visibility = View.VISIBLE*/
                    }

                }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({


            frameLayout.visibility = View.INVISIBLE

            startLL.visibility = View.VISIBLE
        }, 1000)

        videoLayout = findViewById(R.id.videoLayout)

        frameLayout = findViewById(R.id.frameLayout)

        videoObserver = Observer<Boolean> { isPlaing ->
            //colocar aqui o que acontece
        }

        viewModel.isPlaying.observe(this, videoObserver)

        navController = findNavController(R.id.main_nav_host)

        appBarConfiguration = AppBarConfiguration.Builder(
/*            R.id.favoritesFrag,
            R.id.menuFrag, R.id.homeFragment*/
        )
            .build()
        setSupportActionBar(binding.mainToolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)

        visibilityNavElements(navController)
    }


    private fun visibilityNavElements(navController: NavController) {


        navController.addOnDestinationChangedListener { _, destination, _ ->
                 when (destination.id) {
                     R.id.loginFragment ->{
                         main_appbar.visibility = View.GONE
                         supportActionBar?.setDisplayShowTitleEnabled(false)
     //                         supportActionBar?.setTitle("Favoritos")
                     }
                     R.id.siginUpFrag ->{
                         main_appbar.visibility = View.VISIBLE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("Cadastre-se")
                     }
                     R.id.recoverPassFrag2 ->{
                         main_appbar.visibility = View.VISIBLE
                         supportActionBar?.setDisplayShowTitleEnabled(true)
                         supportActionBar?.setTitle("Recuperar Senha")
                     }
                     else ->{
                         main_appbar.visibility = View.GONE

                     }
                 }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = navController
        return when (navController.currentDestination?.id) {
            /*   R.id.homeFragment -> {
                   finish()
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

        videoLayout.onDestroyVideoLayout()
    }

    override fun onPause() {
        super.onPause()
        videoLayout.onPauseVideoLayout()
    }

    override fun onResume() {
        super.onResume()
        if (::videoLayout.isInitialized) videoLayout.onResumeVideoLayout()
    }


}