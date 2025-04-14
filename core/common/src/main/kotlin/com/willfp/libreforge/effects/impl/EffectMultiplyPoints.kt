package com.willfp.libreforge.effects.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.*
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter

object EffectMultiplyPoints : Effect<NoCompileData>("multiply_points") {
    override val parameters = setOf(
        TriggerParameter.PLAYER
    )

    override val arguments = arguments {
        require("type", "You must specify the type of points!")
        require("multiplier", "You must specify the multiplier!")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val player = data.player ?: return false
        val type = config.getString("type")

        player.points[type] *= config.getDoubleFromExpression("multiplier", data)
        Debuger.debug("EffectMultiplyPoints")
        return true
    }
}
