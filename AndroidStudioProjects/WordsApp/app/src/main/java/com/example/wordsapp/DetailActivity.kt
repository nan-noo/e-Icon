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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityDetailBinding

/** Fragment 사용할 경우 필요 없는 파일*/
class DetailActivity : AppCompatActivity() {
    //  컴패니언 객체라는 클래스의 특정 인스턴스 없이 상수를 구분하여 사용할 수 있습니다.
    //  컴패니언 객체는 다른 객체(예: 클래스의 인스턴스)와 비슷합니다.
    //  그러나 프로그램 기간에 컴패니언 객체의 인스턴스는 하나만 존재하므로 싱글톤 패턴이라고도 합니다.
    companion object {
        const val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Fragment 사용을 안 할 경우 */
//        // Retrieve the LETTER from the Intent extras
//        // 객체가 있거나 null일 수 있습니다.
//        // 앱이 속성에 액세스하거나 null 객체에서 함수를 호출하려고 하면 다운됩니다.
//        // 이 값에 안전하게 액세스하려면 이름 뒤에 ?를 입력합니다.
//        // intent가 null이면 앱은 extras 속성 액세스를 시도조차 하지 않으며
//        // extras가 null이면 코드에서 getString()을 호출하려고 시도조차 하지 않습니다.
//        // intent.extras.getString returns String? (String or null)
//        // so toString() guarantees that the value will be a String
//        val letterId = intent?.extras?.getString(LETTER).toString()
//
//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = WordAdapter(letterId, this)
//
//        // Adds a [DividerItemDecoration] between items
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )
//
//        title = getString(R.string.detail_prefix) + " " + letterId
    }
}