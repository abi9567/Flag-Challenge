package com.abi.flagchallenge.extenstions

import androidx.navigation.NavController

fun NavController.navigateWithPop(route : String, popUpRoute : String) {
    this.navigate(route = route) {
        popUpTo(route = popUpRoute) { inclusive = true }
    }
}