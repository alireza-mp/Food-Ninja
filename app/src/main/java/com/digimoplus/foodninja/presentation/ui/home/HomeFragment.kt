package com.digimoplus.foodninja.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.base_dispalys.DisplayBackgroundImage
import com.digimoplus.foodninja.presentation.components.base_dispalys.HomeDisplay
import com.digimoplus.foodninja.presentation.components.util.bottomNavigationTabValues
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import com.digimoplus.foodninja.presentation.ui.home.chat.ChatPage
import com.digimoplus.foodninja.presentation.ui.home.profile.ProfilePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import ir.digimoplus.bottom_navigation.CustomBottomNavigation

@ExperimentalPagerApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    HomePage(viewModel = viewModel)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun HomePage(viewModel: HomeViewModel) {

    val pagerState = rememberPagerState(pageCount = 3)

    DisplayBackgroundImage(
        lightBackground = R.drawable.display_background_light,
        darkBackground = R.drawable.display_background_dark,
        paddingValues = PaddingValues(
            start = AppTheme.dimensions.grid_2,
            end = AppTheme.dimensions.grid_2
        )
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
        ) {

            HorizontalPager(
                state = pagerState,
                itemSpacing = 20.dp,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxSize()
            ) { index ->
                when (index) {
                    0 -> HomeDisplay(viewModel = viewModel)
                    1 -> ProfilePage()
                    2 -> ChatPage()
                }
            }

            Card(
                backgroundColor = AppTheme.colors.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(bottom = AppTheme.dimensions.grid_1)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(15.dp),
                elevation = 4.dp,
            ) {
                CustomBottomNavigation(
                    modifier = Modifier,
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    pagerState = pagerState,
                    tabValues = bottomNavigationTabValues(),
                )
            }
        }
    }
}

// Header used into all pages home page
@Composable
fun HomeHeader(viewModel: HomeViewModel) {
    Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_5))

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()) {

        Text(text = "Find Your Favorite Food",
            lineHeight = 40.sp,
            color = AppTheme.colors.titleText,
            modifier = Modifier.width(250.dp),
            style = AppTheme.typography.h4)

        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center) {
            Button(onClick = {

            },
                contentPadding = PaddingValues(horizontal = AppTheme.dimensions.grid_2,
                    vertical = 14.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .defaultMinSize(1.dp, 1.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.surface),
                shape = RoundedCornerShape(20.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_notifiaction),
                    contentDescription = "",
                    tint = AppTheme.colors.primary
                )
            }
        }

    }

    Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround) {

        TextField(
            value = viewModel.search.value,
            modifier = Modifier
                .fillMaxWidth(0.84f)
                .padding(end = AppTheme.dimensions.grid_1),
            shape = RoundedCornerShape(18.dp),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = AppTheme.colors.secondary,
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.secondaryText,
                disabledTextColor = Color.Transparent,
                backgroundColor = AppTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = AppTheme.colors.secondary
            ),
            textStyle = AppTheme.typography.body1,
            singleLine = true,
            placeholder = {
                Text(text = "What do you want to order?",
                    color = AppTheme.colors.secondaryText,
                    modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_0_25),
                    style = AppTheme.typography.body1)
            }, onValueChange = {
                viewModel.search.value = it
            })

        Button(onClick = { /*TODO*/ },
            contentPadding = PaddingValues(horizontal = 16.dp,
                vertical = 14.dp),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.onSecondary),
            shape = RoundedCornerShape(18.dp)) {

            Icon(painter = painterResource(id = R.drawable.ic_filtter_light),
                tint = AppTheme.colors.secondary,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_0_25),
                contentDescription = ""
            )
        }

    }

}
