package com.willfp.libreforge.effects.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.*
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import org.bukkit.Bukkit

object EffectSetPoints : Effect<NoCompileData>("set_points") {
    override val parameters = setOf(
        TriggerParameter.PLAYER
    )

    override val arguments = arguments {
        require("type", "You must specify the type of points!")
        require("amount", "You must specify the amount of points!")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val player = data.player ?: return false
          Debuger.debug("EffectSetPoints")
        player.points[config.getString("type")] = config.getDoubleFromExpression("amount", data)

        return true
    }
}
