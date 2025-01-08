package org.firstinspires.ftc.teamcode.main.opmodes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.main.components.Arm;
import org.firstinspires.ftc.teamcode.main.components.Intake;
import org.firstinspires.ftc.teamcode.main.utils.ParsedHardwareMap;

@Autonomous(name = "[OFFICIAL] Auto", group = "official")
public class ITDAuto extends LinearOpMode {
    static Boolean wallAuto = false;
    static Boolean Side;
    DcMotorEx frontLeft, backLeft, backRight, frontRight;


    public void moveStraight (double power, int time) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        ParsedHardwareMap parsedHardwareMap = new ParsedHardwareMap(hardwareMap);
        frontLeft = parsedHardwareMap.frontLeft;
        backLeft = parsedHardwareMap.backLeft;
        backRight = parsedHardwareMap.backRight;
        frontRight = parsedHardwareMap.frontRight;

        parsedHardwareMap.leftFourBar.setPosition(.55);
        parsedHardwareMap.rightFourBar.setPosition(.55);
        parsedHardwareMap.liftLeft.setTargetPosition(0);
        parsedHardwareMap.extender.setTargetPosition(500);
        sleep(500);
        parsedHardwareMap.extender.setTargetPosition(Intake.ExtenderPosition.IN);
        parsedHardwareMap.flipDown.setPosition(0);
        parsedHardwareMap.wrist.setPosition(Arm.WristPosition.Specimen + 1);


        waitForStart();

        parsedHardwareMap.claw.setPosition(Arm.ClawPosition.Closed);

        if(Side == wallAuto) {
            parsedHardwareMap.liftLeft.setTargetPosition(Arm.Height.LOWER_BUCKET);

            parsedHardwareMap.leftFourBar.setPosition(Arm.FourBarPosition.Specimen);
            parsedHardwareMap.rightFourBar.setPosition(Arm.FourBarPosition.Specimen);

            sleep(2000);

            parsedHardwareMap.wrist.setPosition(Arm.WristPosition.SampleDrop);

            moveStraight(.4, 900);

            sleep(1000);

            parsedHardwareMap.claw.setPosition(Arm.ClawPosition.Open);

            sleep(10000);

        }
        else {
            //move to sub
            parsedHardwareMap.liftLeft.setTargetPosition(Arm.Height.UPPER_BAR - 775);

            parsedHardwareMap.leftFourBar.setPosition(Arm.FourBarPosition.Specimen);
            parsedHardwareMap.rightFourBar.setPosition(Arm.FourBarPosition.Specimen);

            sleep(3000);

            moveStraight(.4, 2500);

            //put specimen on rung
            parsedHardwareMap.wrist.setPosition(.5);
            sleep(500);

            //release specimen
            parsedHardwareMap.claw.setPosition(Arm.ClawPosition.Open);
            sleep(500);

            parsedHardwareMap.wrist.setPosition(.85);
            moveStraight(-.25, 450);

            parsedHardwareMap.leftFourBar.setPosition(Arm.FourBarPosition.Specimen - .2);
            parsedHardwareMap.rightFourBar.setPosition(Arm.FourBarPosition.Specimen - .2);
            parsedHardwareMap.wrist.setPosition(.85);
            sleep(500);

            //move to box
            moveStraight(-.4, 300);
            parsedHardwareMap.leftFourBar.setPosition(Arm.FourBarPosition.Transfer);
            parsedHardwareMap.rightFourBar.setPosition(Arm.FourBarPosition.Transfer);

            parsedHardwareMap.frontLeft.setPower(.43);
            parsedHardwareMap.backLeft.setPower(-.4);
            parsedHardwareMap.backRight.setPower(.4);
            parsedHardwareMap.frontRight.setPower(-.43);

            parsedHardwareMap.liftLeft.setTargetPosition(Arm.Height.DOWN);
            parsedHardwareMap.wrist.setPosition(.44);
            sleep(1800);

            moveStraight(.5,1000);

            parsedHardwareMap.frontLeft.setPower(.43);
            parsedHardwareMap.backLeft.setPower(-.4);
            parsedHardwareMap.backRight.setPower(.4);
            parsedHardwareMap.frontRight.setPower(-.43);
            sleep(700);


            moveStraight(-.3, 4000);
        }

    }
}

