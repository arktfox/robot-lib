package io.arct.ftclib.eventloop

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import io.arct.ftclib.robot.FtcRobot
import io.arct.ftclib.robot.Telemetry
import io.arct.robotlib.eventloop.ProgramLoop

abstract class OperationMode : ProgramLoop {
    private val sdk: OpMode = current!!

    val time: Double
        get() = sdk.time

    val log: Telemetry = Telemetry(sdk.telemetry)
    override val robot: FtcRobot = FtcRobot(this, sdk)

    override fun exit(): Nothing {
        sdk.requestOpModeStop()
        while (true);
    }

    open fun initLoop() {}
    open fun start() {}

    internal companion object {
        var current: OpMode? = null
    }

    @Retention(AnnotationRetention.SOURCE)
    @Target(AnnotationTarget.CLASS)
    @MustBeDocumented
    annotation class Bind(val type: Type, val name: String = "", val group: String = "")

    enum class Type {
        Autonomous,
        Operated,
        Disabled
    }
}
