package org.firstinspires.ftc.teamcode.util;

import android.util.Log;

import com.seattlesolvers.solverslib.command.CommandScheduler;

public class LoggingUtil {
    public static void enableCommandLogging() {
        final String label = "command";
        CommandScheduler.getInstance().onCommandInitialize(command -> {
            Log.i(label, String.format("init-[%s] *** %s", command.getName(), command.toString()));}
        );
        CommandScheduler.getInstance().onCommandExecute(command -> {
            Log.i(label, String.format("execute-[%s] *** %s", command.getName(), command.toString()));
        });
        CommandScheduler.getInstance().onCommandFinish(command -> {
            Log.i(label, String.format("end(false)-[%s] *** %s", command.getName(), command.toString()));
        });
        CommandScheduler.getInstance().onCommandInterrupt(command -> {
            Log.i(label, String.format("end(true)-[%s] *** %s", command.getName(), command.toString()));
        });
    }
}
