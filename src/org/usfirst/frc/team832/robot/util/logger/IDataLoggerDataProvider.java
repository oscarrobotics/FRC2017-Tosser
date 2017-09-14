package org.usfirst.frc.team832.robot.util.logger;

public interface IDataLoggerDataProvider {
    public String[] fetchNames();

    public Object[] fetchData();
}
