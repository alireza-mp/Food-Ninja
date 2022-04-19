package com.digimoplus.foodninja.presentation.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay


@ExperimentalPagerApi
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.splash_background),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                    Image(
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = ""
                    )
                }

                LaunchedEffect(Unit) {
                    delay(1500)
                    findNavController().navigate(R.id.action_splashFragment_to_introductionFragment)
                }
            }
        }
    }
}


