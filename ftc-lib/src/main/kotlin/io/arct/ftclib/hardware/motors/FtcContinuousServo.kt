package io.arct.ftclib.hardware.motors

import com.qualcomm.robotcore.hardware.CRServo
import io.arct.ftclib.eventloop.OperationMode
import io.arct.ftclib.hardware.controllers.FtcServoController
import io.arct.robotlib.hardware.controllers.ServoController
import io.arct.robotlib.hardware.motors.ContinuousServo

open class FtcContinuousServo<T : CRServo> internal constructor(sdk: T, opMode: OperationMode) : FtcBasicMotor<T>(sdk, opMode), ContinuousServo {
    override val controller: ServoController = FtcServoController(sdk.controller, opMode)

    override val port: Int
        get() = sdk.portNumber

    override var power: Double
        get() = sdk.power
        set(v) {
            sdk.power = v
        }

    override fun move(power: Double): ContinuousServo {
        sdk.controller.setServoPosition(port, sdk.controller.getServoPosition(port) + power)

        return this
    }
}