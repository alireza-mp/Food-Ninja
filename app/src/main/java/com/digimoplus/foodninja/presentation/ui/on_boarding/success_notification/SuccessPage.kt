package com.digimoplus.foodninja.presentation.ui.on_boarding.success_notification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController

import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.theme.AppTheme

/*class SuccessNotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

            }
        }
    }

}*/

@Composable
fun SuccessPage(
    navController: NavController,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = AppTheme.colors.background),
        contentAlignment = Alignment.Center) {
        Button(onClick = {
            navController.navigate(Screens.Main.route) {
                //remove all last pages from back stack
                popUpTo(0)
            }
        }) {
            Text(text = "Goo")
        }
    }
}