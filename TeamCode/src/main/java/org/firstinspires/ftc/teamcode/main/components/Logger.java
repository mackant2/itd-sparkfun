package org.firstinspires.ftc.teamcode.main.components;

import android.os.Environment;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Logger {
    private FileWriter writer;
    private Telemetry telemetry;
    private Telemetry.Item recentLogItem;
    long startTime;

    public void Initialize(Telemetry _telemetry) {
        startTime = System.currentTimeMillis();
        telemetry = _telemetry;
        recentLogItem = telemetry.addData("Recent Log", "");

        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/runtime_log.txt");
            writer = new FileWriter(file,false);
        }
        catch (IOException e) {
            telemetry.addData("File Creation Error", e.getMessage());
            telemetry.update();
        }
    }

    public void Log(String data) {
        recentLogItem.setValue(data);
        try {
            long msSinceStart = System.currentTimeMillis() - startTime;
            double secondsSinceStart = (double) msSinceStart / 1000.0;
            writer.write(data + " [" + new DecimalFormat("#0.000").format(secondsSinceStart) + "]");
            writer.write(System.lineSeparator());
        } catch (IOException e) {
           telemetry.addData("File Creation Error", e.getMessage());
        }
        telemetry.update();
    }
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}