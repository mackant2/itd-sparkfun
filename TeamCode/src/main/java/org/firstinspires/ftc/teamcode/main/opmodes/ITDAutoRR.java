package org.firstinspires.ftc.teamcode.main.opmodes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.main.components.Arm;
import org.firstinspires.ftc.teamcode.main.components.Intake;
import org.firstinspires.ftc.teamcode.main.utils.ParsedHardwareMap;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name = "[NOT DONE] Auto With RR", group = "official")
public class ITDAutoRR extends LinearOpMode {
    public static Boolean cycleSamples = false;

    final double width = 12.3;
    final double length = 15.75;
    final double specimenSampleY = -12;
    final Vector2d[] specimenSamplePositions = {new Vector2d(45, specimenSampleY), new Vector2d(55, specimenSampleY), new Vector2d(61, specimenSampleY)};
    final Vector2d specimenHangPos = new Vector2d(8, -34);
    final Vector2d specimenPickupPos = new Vector2d(36, -56);

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(width / 2, -72 + length / 2, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        ParsedHardwareMap parsedHardwareMap = new ParsedHardwareMap(hardwareMap);
        parsedHardwareMap.flipDown.setPosition(Intake.FlipdownPosition.in);

        waitForStart();

        if (isStopRequested()) return;

        TrajectoryActionBuilder specimenTAB = drive.actionBuilder(initialPose)
                .lineToY(-34)
                .setReversed(true)
                .splineToLinearHeading(new Pose2d(34, -30, 0), Math.PI / 2)
                .setTangent(Math.toRadians(90))
                .lineToY(-12)
                .splineToConstantHeading(new Vector2d(specimenSamplePositions[0].x, -48), Math.toRadians(-110))
                .splineToConstantHeading(specimenSamplePositions[0], 0)
                .splineToConstantHeading(new Vector2d(specimenSamplePositions[1].x, -48), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(specimenSamplePositions[2].x, -12), 0)
                .setTangent(Math.PI / 2)
                .lineToY(-48)
                .strafeToLinearHeading(specimenPickupPos, Math.toRadians(-90)) //Cycle specimens
                .strafeToLinearHeading(specimenHangPos, Math.toRadians(90))
                .strafeToLinearHeading(specimenPickupPos, Math.toRadians(-90))
                .strafeToLinearHeading(specimenHangPos, Math.toRadians(90))
                .strafeToLinearHeading(specimenPickupPos, Math.toRadians(-90))
                .strafeToLinearHeading(specimenHangPos, Math.toRadians(90))
                .strafeToLinearHeading(specimenPickupPos, Math.toRadians(-90))
                .strafeToLinearHeading(specimenHangPos, Math.toRadians(90));

        TrajectoryActionBuilder oneEighty = drive.actionBuilder(initialPose)
                        .turn(Math.toRadians(180));



        Actions.runBlocking(new SequentialAction(specimenTAB.build()));
    }
}