package org.usfirst.frc.team832.robot.util.logger;

import org.usfirst.frc.team832.robot.util.logger.FastDataLoggerCollections;

import com.ctre.*;

public class CANTalonLogger {
    public static void startCANTalonPositionLogger(String name,
            final CANTalon talon) {
        IFastDataLogger iFastDataLogger;
        iFastDataLogger = new FastDataLoggerCollections();
        iFastDataLogger.setInterval(0.001);
        iFastDataLogger.setMaxLength(2.000);
        iFastDataLogger.setFilename(name);

        iFastDataLogger.addMetadata("P", talon.getP());
        iFastDataLogger.addMetadata("I", talon.getI());
        iFastDataLogger.addMetadata("D", talon.getD());
        iFastDataLogger.addMetadata("F", talon.getF());

        iFastDataLogger.setDataProvider(new IDataLoggerDataProvider() {
            @Override
            public String[] fetchNames() {
                return new String[] { //
                        "error", //
                        "outputCurrent", //
                        "outputVoltage", //
                };
            }

            @Override
            public Object[] fetchData() {
                return new Object[] { //
                        talon.getError(), //
                        talon.getOutputCurrent(), //
                        talon.getOutputVoltage(), //
                };
            }
        });
        iFastDataLogger.start();
    }
}
