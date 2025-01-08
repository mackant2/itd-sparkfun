package org.firstinspires.ftc.teamcode.main.utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.main.components.Arm;
import org.firstinspires.ftc.teamcode.main.components.Drivetrain;
import org.firstinspires.ftc.teamcode.main.components.Intake;
import org.firstinspires.ftc.teamcode.main.components.Logger;
import org.firstinspires.ftc.teamcode.main.components.TransferPlate;

public class Robot {
    public Arm arm;
    public Drivetrain drivetrain;
    public Intake intake;
    public Logger logger;
    public TransferPlate transferPlate;
    public LinearOpMode opMode;
    public ParsedHardwareMap parsedHardwareMap;

    void Initialize() {
        //initialize four bar
        arm.Initialize();
        //flip intake up and bring in
        intake.Initialize();
    }

    public Robot(LinearOpMode opMode, ParsedHardwareMap parsedHardwareMap) {
        this.parsedHardwareMap = parsedHardwareMap;
        this.opMode = opMode;
        arm = new Arm(this);
        drivetrain = new Drivetrain(this);
        logger = new Logger();
        logger.Initialize(opMode.telemetry);
        intake = new Intake(parsedHardwareMap, this);
        transferPlate = new TransferPlate(this);

        Initialize();
    }

    public void Update() {
        arm.Update();
        drivetrain.Update();
        intake.Update();
        transferPlate.Update();
    }
}
