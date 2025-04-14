package com.willfp.libreforge

import org.bukkit.Bukkit

object Debuger {
    fun debug(message: String) {
        Bukkit.getLogger().warning("[Debug] $message")
    }
}