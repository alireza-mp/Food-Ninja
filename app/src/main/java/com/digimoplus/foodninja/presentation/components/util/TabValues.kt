package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme
import ir.digimoplus.bottom_navigation.TabValue

@Composable
fun bottomNavigationTabValues(): List<TabValue> {

    return listOf(
        TabValue(
            title = "Home",
            R.drawable.ic_home,
            AppTheme.colors.primary,
            tabHeight = 50.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 25f,
        ),
        TabValue(
            title = "Profile",
            R.drawable.ic_profile,
            AppTheme.colors.primary,
            tabHeight = 50.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 25f,
        ),
        TabValue(
            title = "",
            R.drawable.ic_basket,
            AppTheme.colors.primary,
            tabHeight = 50.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 25f,
        ),
        TabValue(
            title = "Chat",
            R.drawable.ic_chat,
            AppTheme.colors.primary,
            tabHeight = 50.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 25f,
        )
    )
}