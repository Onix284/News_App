package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.newsapp.MyViewModel.MyViewModel
import com.example.newsapp.Network.RetrofitClient
import com.example.newsapp.Screens.HomeUI
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MyViewModel()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeUI(
                viewModel
            )
        }


    }
}
