package com.l24o.siberianpro.common

import com.l24o.siberianpro.BuildConfig

inline fun inDebugMode(block: (() -> Unit)) {
    if (BuildConfig.DEBUG) {
        block.invoke()
    }
}
