package com.mauricioburgos.pokewise.core.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which returns information about the network connection state.
 */
//TODO refactor this class DEPRECATION
@Suppress("DEPRECATION")
@Singleton
class NetworkHandler
@Inject constructor( @ApplicationContext private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}

