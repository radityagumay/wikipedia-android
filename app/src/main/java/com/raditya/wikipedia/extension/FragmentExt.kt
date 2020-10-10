package com.raditya.wikipedia.extension

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.raditya.wikipedia.R.anim

fun Fragment.goTo(
    @IdRes resId: Int,
    args: Bundle? = null,
    @Nullable navOptions: NavOptions? = null
) {
    val nav = navOptions {
        anim {
            enter = anim.slide_in_right
            exit = anim.slide_out_left
            popEnter = anim.slide_in_left
            popExit = anim.slide_out_right
        }
    }
    findNavController().navigate(resId, args, navOptions ?: nav)
}

fun Fragment.goTo(
    navDirections: NavDirections,
    @Nullable navOptions: NavOptions? = null
) {
    val nav = navOptions {
        anim {
            enter = anim.slide_in_right
            exit = anim.slide_out_left
            popEnter = anim.slide_in_left
            popExit = anim.slide_out_right
        }
    }
    findNavController().navigate(navDirections, navOptions ?: nav)
}
