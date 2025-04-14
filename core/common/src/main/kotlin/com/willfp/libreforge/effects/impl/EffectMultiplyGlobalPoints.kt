package com.willfp.libreforge.effects.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.*
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData

object EffectMultiplyGlobalPoints : Effect<NoCompileData>("multiply_global_points") {
    override val isPermanent = false

    override val arguments = arguments {
        require("type", "You must specify the type of points!")
        require("multiplier", "You must specify the multiplier!")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val type = config.getString("type")

        globalPoints[type] *= config.getDoubleFromExpression("multiplier", data)
        Debuger.debug("EffectMultiplyGlobalPoints")
        return true
    }
}
