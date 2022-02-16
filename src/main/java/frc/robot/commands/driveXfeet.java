// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class driveXfeet extends PIDCommand {
  private final DriveTrain m_DriveTrain;
  /** Creates a new driveXfeet. */
  public driveXfeet(DriveTrain driveTrainIn, double feet) {
    super(
        // The controller that the command will use
        new PIDController(SmartDashboard.getNumber("kP", 0), SmartDashboard.getNumber("kI", o), SmartDashboard.getNumber("kD", 0)),
        // This should return the measurement
        () -> driveTrainIn.GetAverageEncoderPosition(),
        // This should return the setpoint (can also be a constant)
        () -> driveTrainIn.FeetToClicks(feet),
        // This uses the output
        output -> {
          driveTrainIn.drive(output , 0, 0);
        });
        m_DriveTrain = driveTrainIn;
        getController().setTolerance(driveTrainIn.FeetToClicks(.25));
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(m_DriveTrain);
  }

 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
