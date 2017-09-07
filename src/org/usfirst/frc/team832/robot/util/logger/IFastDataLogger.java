package org.usfirst.frc.team832.robot.util.logger;

public interface IFastDataLogger extends IDataLogger {
    public void setMaxLength(double seconds);

    public void done();

    public boolean isDone();
}
