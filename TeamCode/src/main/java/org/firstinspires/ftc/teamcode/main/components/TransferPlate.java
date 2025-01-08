package org.firstinspires.ftc.teamcode.main.components;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.main.utils.EnhancedColorSensor;
import org.firstinspires.ftc.teamcode.main.utils.Robot;

public class TransferPlate {
    ColorSensor colorSensor;
    DistanceSensor distanceSensor;
    public boolean sampleIsPresent;

    public TransferPlate(Robot robot) {
        colorSensor = robot.parsedHardwareMap.transferSensor;
        distanceSensor = robot.parsedHardwareMap.transferDistanceSensor;
    }

    public void Update() {
        sampleIsPresent = EnhancedColorSensor.CheckSensor(colorSensor, distanceSensor, EnhancedColorSensor.Color.Any);
    }
}
