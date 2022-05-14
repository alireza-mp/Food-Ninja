package com.digimoplus.foodninja.presentation.components.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.theme.AppTheme
import ir.digimoplus.bottom_navigation.TabValue

@Composable
fun bottomNavigationTabValues(
    basketBadge: Int,
    chatBadge: Int,
): List<TabValue> {

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
            badgeValue = basketBadge,
            badgeBackgroundColor = Color(0xFFFF4B4B)
        ),
        TabValue(
            title = "Chat",
            R.drawable.ic_chat,
            AppTheme.colors.primary,
            tabHeight = 50.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 25f,
            badgeValue = chatBadge,
            badgeBackgroundColor = Color(0xFFFF4B4B)
        )
    )
}