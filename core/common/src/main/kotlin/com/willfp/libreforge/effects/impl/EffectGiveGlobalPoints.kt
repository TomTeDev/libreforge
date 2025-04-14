package com.willfp.libreforge.effects.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.*
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.triggers.TriggerData

object EffectGiveGlobalPoints : Effect<NoCompileData>("give_global_points") {
    override val isPermanent = false

    override val arguments = arguments {
        require("type", "You must specify the type of points!")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        globalPoints[config.getString("type")] += config.getDoubleFromExpression("amount", data)
        Debuger.debug("EffectGiveGlobalPoints")
        return true
    }
}
