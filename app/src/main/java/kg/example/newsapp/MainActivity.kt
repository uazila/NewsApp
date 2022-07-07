package kg.example.newsapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kg.example.newsapp.databinding.ActivityMainBinding
import kg.example.newsapp.ui.notifications.Prefs

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        if (!Prefs(this).isShow()) {
            navController.navigate(R.id.boardFragment)
        }
        navController.addOnDestinationChangedListener{ navController:NavController, navDestination: NavDestination, bundle: Bundle?->
            val fragments= arrayListOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profile)

            if (fragments.contains(navDestination.id)) {
                binding.navView.visibility = View.VISIBLE
            } else{
                binding.navView.visibility=View.GONE
            }

            if (navDestination.id==R.id.boardFragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    }

