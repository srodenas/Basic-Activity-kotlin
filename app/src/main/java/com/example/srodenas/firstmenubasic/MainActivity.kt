package com.example.srodenas.firstmenubasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.srodenas.firstmenubasic.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la barra de herramientas o de Acción
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController  //nuestro navController, para la navegación
        /*
        AppBarConfiguration, administra el comportamiento del botón de Navegación en la esquina
        superior izquierda. Cambiará conforme el usuario se encuentre en un nivel superior.

        Si No estamos utilizando un Drawer, en el nivel superior el botón de navegación está oculto. Sin embargo
        si tenemos un Navigation Drawer, el botón de navegación estará visible, viéndose un icono lateral de tres líneas
        horizontales. Conforme navegemos, el botón de navegación cambiará a una <-.

        Necesitamos nuestro arbol de navegación, para que sepa cúal es el fragmento superior y así
        controlar la vuelta atrás.
         */
        appBarConfiguration = AppBarConfiguration(navController.graph)

        /*
        Necesitamos integración entre la barra de acción y el controlador de navegación.
        Esto hace que conforme navegemos, cambie el nombre del fragmento en la barra superior y por supuesto
        que nos salga la flecha <- cuando estamos en un nivel inferior.
         */
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Alguna chorrada", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //método que es llamado después de crear la vista del activity.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú; esto agrega elementos a la barra de acción si está presente.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /*
    Para controlar los eventos de los items del toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_home -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            R.id.toolbar_hospedaje -> {
                navController.navigate(R.id.hospedajeFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}