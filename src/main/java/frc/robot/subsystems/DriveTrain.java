// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private AHRS ahrs = new AHRS(SPI.Port.kMXP);

  private WPI_TalonSRX m_frontLeftMotor = new WPI_TalonSRX(4);
  private WPI_TalonSRX m_frontRightMotor = new WPI_TalonSRX(2);
  private WPI_TalonSRX m_rearLeftMotor = new WPI_TalonSRX(1);
  private WPI_TalonSRX m_rearRightMotor = new WPI_TalonSRX(3);


  private MecanumDrive m_drive;

  private boolean isFieldOrintated = false;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_rearRightMotor.setInverted(true);
    m_frontRightMotor.setInverted(true);

    m_drive = new MecanumDrive(m_frontLeftMotor,m_rearLeftMotor,m_frontRightMotor,m_rearRightMotor);
  }
    //private CANSparkMax m_frontLeftMotor = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    //private CANSparkMax m_frontRightMotor = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    //private CANSparkMax m_rearLeftMotor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    //private CANSparkMax m_rearRightMotor = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);


    

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double ySpeed, double xSpeed,double zRotation){
    if (isFieldOrintated){
      m_drive.driveCartesian(ySpeed, xSpeed, zRotation, ahrs.getYaw());
    } else {
      m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
    }
  }
  public void resetEncoder(){
    ahrs.reset();
  }
  public void changeDrive(){
    if (isFieldOrintated){
      isFieldOrintated = false;
    } else {
      isFieldOrintated = true;
    }
  }
}
