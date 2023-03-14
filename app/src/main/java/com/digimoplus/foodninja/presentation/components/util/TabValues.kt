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
            iconId = R.drawable.ic_home,
            tabBackgroundColor = AppTheme.colors.primary,
            tabHeight = 44.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 40f,
        ),
        TabValue(
            title = "Profile",
            iconId = R.drawable.ic_profile,
            tabBackgroundColor = AppTheme.colors.primary,
            tabHeight = 44.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 40f,
        ),
        TabValue(
            title = "",
            iconId = R.drawable.ic_basket,
            tabBackgroundColor = AppTheme.colors.primary,
            tabHeight = 44.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 40f,
            badgeValue = basketBadge,
            badgeBackgroundColor = Color(0xFFFF4B4B)
        ),
        TabValue(
            title = "Chat",
            iconId = R.drawable.ic_chat,
            tabBackgroundColor = AppTheme.colors.primary,
            tabHeight = 44.dp,
            titleColor = AppTheme.colors.titleText,
            tabCorner = 40f,
            badgeValue = chatBadge,
            badgeBackgroundColor = Color(0xFFFF4B4B)
        )
    )
}