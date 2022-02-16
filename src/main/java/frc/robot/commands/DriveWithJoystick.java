// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoystick extends CommandBase {
  private DriveTrain m_driveTrain;
  private Joystick m_driveStick;

  /** Creates a new DriveWithJoystick. */
  public DriveWithJoystick(DriveTrain driveTrain, Joystick driveStick) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    m_driveTrain = driveTrain;
    m_driveStick = driveStick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("y", -m_driveStick.getY());
    m_driveTrain.drive(-m_driveStick.getY(), m_driveStick.getX(), m_driveStick.getZ());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
