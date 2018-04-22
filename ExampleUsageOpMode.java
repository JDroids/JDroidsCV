package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.disnodeteam.dogecv.ActivityViewDisplay;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.resources.JewelDetectionOpenCV;
import org.firstinspires.ftc.teamcode.resources.jewelDetectionOpenCV.JDColor;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.teamcode.resources.functions.*;
import static org.firstinspires.ftc.teamcode.resources.hardware.*;

/**
 * Created by dansm on 1/21/2018.
 */

@Autonomous(name="JDroids CV Example")

public class ExampleUsageOpMode extends LinearOpMode{
    @Override

    public void runOpMode() throws InterruptedException{
        ArrayList<JDColor> listOfJewelColors = new ArrayList<JDColor>();

        jewelDetectionOpenCV jewelVision = new jewelDetectionOpenCV();
        // can replace with ActivityViewDisplay.getInstance() for fullscreen
        jewelVision.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), 1);

        // start the vision system
        jewelVision.enable();

        ElapsedTime mRuntime = new ElapsedTime();

        waitForStart();
        mRuntime.reset();


        while(opModeIsActive()){
            telemetry.addData("Jewel On Left", jewelVision.jewelOnLeft);
            telemetry.addData("Time Elapsed", mRuntime.milliseconds());
            telemetry.update();

            if(listOfJewelColors.size() < 5){
                if(jewelVision.jewelOnLeft != constants.JDColor.NONE) {
                    listOfJewelColors.add(jewelVision.jewelOnLeft);
                }
            }
            else{
                break;
            }
        }

        jewelVision.disable();

        int redJewelsFound = 0;
        int blueJewelsFound = 0;

        for(JDColor color : listOfJewelColors){
            if(color == constants.JDColor.RED){
                redJewelsFound++;
            }
            else{
                blueJewelsFound++;
            }
        }
    }
}
