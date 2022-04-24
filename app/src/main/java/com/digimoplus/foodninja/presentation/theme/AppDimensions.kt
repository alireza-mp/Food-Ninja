package com.digimoplus.foodninja.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.log

data class AppDimensions(
    val grid_0_25: Dp = 2.dp,
    val grid_0_5: Dp = 4.dp,
    val grid_1: Dp = 8.dp,
    val grid_1_5: Dp = 12.dp,
    val grid_2: Dp = 16.dp,
    val grid_2_5: Dp = 20.dp,
    val grid_3: Dp = 24.dp,
    val grid_3_5: Dp = 28.dp,
    val grid_4: Dp = 32.dp,
    val grid_4_5: Dp = 36.dp,
    val grid_5: Dp = 40.dp,
    val grid_5_5: Dp = 44.dp,
    val grid_6: Dp = 48.dp,
    val grid_6_5: Dp = 52.dp,
    val grid_7: Dp = 56.dp,
    val grid_7_5: Dp = 60.dp,
    val grid_8: Dp = 64.dp,
    val plot_115:Dp = 115.dp,
    val plot_125:Dp = 125.dp,
    val plot_80:Dp = 80.dp,
    val logo_size: Dp = 225.dp,
    val title_page_one: Dp = 250.dp,
    val description_page_one: Dp = 260.dp,
    val img_donate_1: Dp = 135.dp,
    val img_donate_2: Dp = 150.dp,
    val img_donate_3: Dp = 70.dp,
    val img_donate_4: Dp = 150.dp,
    val title_page_two :Dp =320.dp,
    val description_page_two :Dp =250.dp,
    val img_berger:Dp=150.dp,
    )

val smallDimensions = AppDimensions(
    grid_0_25 = 1.3f.dp,
    grid_0_5 = 2.6.dp,
    grid_1 = 5.2.dp,
    grid_1_5 = 7.8.dp,
    grid_2 = 10.4.dp,
    grid_2_5 = 13.dp,
    grid_3 = 15.6.dp,
    grid_3_5 = 18.20.dp,
    grid_4 = 20.8.dp,
    grid_4_5 = 23.5.dp,
    grid_5 = 26.dp,
    grid_5_5 = 28.6.dp,
    grid_6 = 31.2.dp,
    grid_6_5 = 33.8.dp,
    grid_7 = 36.4.dp,
    grid_7_5 = 39.dp,
    grid_8 = 41.6.dp,
    logo_size = 146.dp,
    plot_80 = 52.dp,
    plot_115 = 74.dp,
    plot_125 = 81.dp,
    title_page_one = 162.dp,
    title_page_two = 208.dp,
    description_page_one =169.dp ,
    description_page_two = 162.dp,
    img_berger = 97.dp,
    img_donate_1 = 87.dp,
    img_donate_2 = 97.dp,
    img_donate_3 = 45.dp,
    img_donate_4 = 97.dp
)

val sw360Dimensions = AppDimensions(
    grid_0_25 = 2.dp,
    grid_0_5 = 4.dp,
    grid_1 = 8.dp,
    grid_1_5 = 12.dp,
    grid_2 = 16.dp,
    grid_2_5 = 20.dp,
    grid_3 = 24.dp,
    grid_3_5 = 28.dp,
    grid_4 = 32.dp,
    grid_4_5 = 36.dp,
    grid_5 = 40.dp,
    grid_5_5 = 44.dp,
    grid_6 = 48.dp,
    grid_6_5 = 52.dp,
    grid_7 = 56.dp,
    grid_7_5 = 60.dp,
    grid_8 = 64.dp,
    logo_size = 225.dp,
    plot_80 = 80.dp,
    plot_115 = 115.dp,
    plot_125 = 125.dp,
    title_page_one = 250.dp,
    title_page_two = 320.dp,
    description_page_one =260.dp ,
    description_page_two = 250.dp,
    img_berger = 150.dp,
    img_donate_1 = 135.dp,
    img_donate_2 = 150.dp,
    img_donate_3 = 70.dp,
    img_donate_4 = 150.dp
)

val mediumDimensions = AppDimensions(
    grid_0_25 = 1.65.dp,
    grid_0_5 = 3.30.dp,
    grid_1 = 6.60.dp,
    grid_1_5 = 10.dp,
    grid_2 = 13.20.dp,
    grid_2_5 = 16.5.dp,
    grid_3 = 20.dp,
    grid_3_5 = 23.dp,
    grid_4 = 26.5.dp,
    grid_4_5 = 30.dp,
    grid_5 = 33.dp,
    grid_5_5 = 36.5.dp,
    grid_6 = 39.5.dp,
    grid_6_5 = 43.dp,
    grid_7 = 46.5.dp,
    grid_7_5 = 50.dp,
    grid_8 = 53.30.dp,
    logo_size = 185.dp,
    plot_80 = 66.dp,
    plot_115 = 94.dp,
    plot_125 = 103.dp,
    title_page_one = 206.dp,
    title_page_two = 264.dp,
    description_page_one =214.dp ,
    description_page_two = 206.dp,
    img_berger = 123.dp,
    img_donate_1 = 111.dp,
    img_donate_2 = 123.dp,
    img_donate_3 = 57.dp,
    img_donate_4 = 111.dp
)
internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }