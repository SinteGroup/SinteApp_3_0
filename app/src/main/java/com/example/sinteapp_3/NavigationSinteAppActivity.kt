package com.example.sinteapp_3

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sinteapp_3.databinding.ActivityNavigationSinteAppBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.lang.NullPointerException

class NavigationSinteAppActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationSinteAppBinding

    //config fájl kiolvasás onCreate közben
    companion object{
        private val domain="http://195.228.220.2:65535/"
        private val tudastar_dokumentum_mappa= domain+"tudastar/dokumentumok/"
        private val tudastar_videok_mappa= domain+"tudastar/videok/"
        private val faliujsagScriptFile=domain+"faliujsag/script/getFiles.php"
        private val faliujsag_mappa=domain+"faliujsag/"
        private val tudastar_videok_link= domain+"tudastar/script/getFiles.php?method=tudastar_videok"
        private val tudastar_dokumentumok_link= domain+"tudastar/script/getFiles.php?method=tudastar_dokumentumok"

        fun getFaliujsagScriptFiles():String{
            return faliujsagScriptFile
        }

        fun getFaliujsagMappa():String{
            return faliujsag_mappa
        }

        fun getTudastar_Dokumentum_mappa(): String{
            return tudastar_dokumentum_mappa
        }

        fun getTudastar_videok_mappa():String{
            return tudastar_videok_mappa
        }

        @Throws(NullPointerException::class)
        fun getTudastarLink(tudastarDokumetumTipus: String?): String? {
            var returnTudastarDokumentumLink: String?=null

            if(tudastarDokumetumTipus.equals("pdf"))
                returnTudastarDokumentumLink= tudastar_dokumentumok_link

            if (tudastarDokumetumTipus.equals("videok"))
                returnTudastarDokumentumLink= tudastar_videok_link

            return returnTudastarDokumentumLink  //kivételt kezelni, mert nem megfelelő típust adott meg
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationSinteAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigationSinteApp.toolbar)

        binding.appBarNavigationSinteApp.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_sinte_app)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
               R.id.nav_Hireink, R.id.nav_tudastar,  R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_sinte_app, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_sinte_app)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}