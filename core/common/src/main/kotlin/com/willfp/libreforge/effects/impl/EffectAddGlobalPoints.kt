package com.willfp.libreforge.effects.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.*
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.effects.Identifiers
import java.util.*

object EffectAddGlobalPoints : Effect<NoCompileData>("add_global_points") {
    override val arguments = arguments {
        require("type", "You must specify the type of points!")
        require("amount", "You must specify the amount of points!")
    }

    private val tracker = mutableMapOf<UUID, AddedPoint>()

    override fun onEnable(
        dispatcher: Dispatcher<*>,
        config: Config,
        identifiers: Identifiers,
        holder: ProvidedHolder,
        compileData: NoCompileData
    ) {
        val point = config.getString("type")
        val amount = config.getDoubleFromExpression("amount", dispatcher.get())

        tracker[identifiers.uuid] = AddedPoint(
            point, amount
        )
        Debuger.debug("EffectAddGlobalPoints enable")
        globalPoints[point] += amount
    }

    override fun onDisable(dispatcher: Dispatcher<*>, identifiers: Identifiers, holder: ProvidedHolder) {
        val addedPoint = tracker[identifiers.uuid] ?: return
        Debuger.debug("EffectAddGlobalDisablePoints")
        globalPoints[addedPoint.point] -= addedPoint.amount
    }

    private data class AddedPoint(
        val point: String, val amount: Double
    )
}
