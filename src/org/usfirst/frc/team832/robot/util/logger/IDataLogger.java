package org.usfirst.frc.team832.robot.util.logger;

import java.io.File;

public interface IDataLogger {
    public void setLoggingDirectory(File loggingDirectory);

    public void setFilename(String filename);

    public void setInterval(double seconds);

    public void setDataProvider(
            IDataLoggerDataProvider iFastLoggerDataProvider);

    public void addMetadata(String s, double d);

    public String start();

}
