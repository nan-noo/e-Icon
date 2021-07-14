/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true // 앱의 레이아웃 상태를 추적

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // nav_host_fragment(FragmentContainerView의 ID임) 참조를 가져와서
        // navController 속성에 할당합니다.
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // navController를 전달
        // LetterListFragment의 메뉴 옵션과 같은 작업 모음(앱 바) 버튼이 표시됩니다.
        setupActionBarWithNavController(navController)

        /** Fragment 사용을 안 할 경우 */
//        recyclerView = binding.recyclerView
//        // Sets the LinearLayoutManager of the recyclerview
//        chooseLayout()
    }

    // XML에서 defaultNavHost를 true로 설정하는 것과 함께 이 메서드를 사용하면
    // 위로 버튼을 처리할 수 있습니다. 그러나 Activity이 구현을 제공해야 합니다.
    override fun onSupportNavigateUp(): Boolean {
        // navigateUp()이 false를 반환하는 경우에만 super.onSupportNavigateUp()을 호출
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    

    /** Fragment 사용을 안 할 경우 */
//    // 메뉴 옵션을 표시
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.layout_menu, menu)
//
//        val layoutButton = menu?.findItem(R.id.action_switch_layout)
//        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
//        setIcon(layoutButton)
//
//        return true
//    }
//
//    // 버튼이 선택될 때 chooseLayout() 호출
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_switch_layout -> {
//                // Sets isLinearLayoutManager (a Boolean) to the opposite value
//                isLinearLayoutManager = !isLinearLayoutManager
//                // Sets layout and icon
//                chooseLayout()
//                setIcon(item)
//
//                return true
//            }
//            //  Otherwise, do nothing and use the core event handling
//
//            // when clauses require that all possible paths be accounted for explicitly,
//            //  for instance both the true and false cases if the value is a Boolean,
//            //  or an else to catch all unhandled cases.
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    private fun chooseLayout() {
//        if (isLinearLayoutManager) {
//            recyclerView.layoutManager = LinearLayoutManager(this)
//        } else {
//            recyclerView.layoutManager = GridLayoutManager(this, 4)
//        }
//        recyclerView.adapter = LetterAdapter()
//    }
//
//    private fun setIcon(menuItem: MenuItem?) {
//        if (menuItem == null)
//            return
//
//        // Set the drawable for the menu icon based on which LayoutManager is currently in use
//
//        // An if-clause can be used on the right side of an assignment if all paths return a value.
//        // The following code is equivalent to
//        // if (isLinearLayoutManager)
//        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
//        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
//        menuItem.icon =
//            if (isLinearLayoutManager)
//                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
//            else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
//    }
}
