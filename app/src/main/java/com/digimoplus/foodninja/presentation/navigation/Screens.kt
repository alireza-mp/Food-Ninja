package com.digimoplus.foodninja.presentation.navigation

sealed class Screens(val route: String) {

    object Splash : Screens("splash")
    object Introduction : Screens("introduction")
    object Login : Screens("login")
    object Register : Screens("register")
    object Main : Screens("main")
    object ForgetPassword : Screens("forget_password")
    object Payment : Screens("payment/{user}")
    object UploadProfile : Screens("upload_Profile/{user}")
    object ChooseLocation : Screens("choose_location")
    object Success : Screens("success")
    object Basket : Screens("basket")
    object ChatDetail : Screens("chat_detail")

    object RestaurantDetail : Screens("restaurant_detail/{id}") {
        fun createIdRoute(id: Int) = "restaurant_detail/$id"
    }

    object MenuDetail : Screens("menu_detail/{id}") {
        fun createIdRoute(id: Int) = "menu_detail/$id"
    }

    object CompleteRegister : Screens("complete_register?name={name}") {
        fun createRoute(name: String) = "complete_register?name=$name"
    }
}