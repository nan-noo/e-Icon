package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 대상의 라벨을 기반으로 앱 바에 제목이 표시되고, 최상위 대상에 있지 않은 경우 항상 위로 버튼이 표시됨
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean { // 뒤로 버튼
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}