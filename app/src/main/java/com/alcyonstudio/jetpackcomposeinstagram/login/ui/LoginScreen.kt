package com.alcyonstudio.jetpackcomposeinstagram.login.ui

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        val isLoading:Boolean by loginViewModel.isLoading.observeAsState(initial = false)

        if(isLoading){
            Box(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        else{
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center),loginViewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }

    }

}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close, contentDescription = "Close App", modifier = modifier.clickable { activity.finish() } )
}

