/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  WPI_TalonSRX frontL, rearL, frontR, rearR;
  DifferentialDrive robotBase;
  SpeedControllerGroup left, right;

  @Override
  public void robotInit() 
  {
   
    frontL = new WPI_TalonSRX(3);
    rearL = new WPI_TalonSRX(2);
    //rearL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);

    frontR = new WPI_TalonSRX(0);
    rearR = new WPI_TalonSRX(1);
    //rearR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);

    left = new SpeedControllerGroup(frontL, rearL);
    right = new SpeedControllerGroup(frontR, rearR);

   // robotBase = new DifferentialDrive(left, right);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
   
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
    // robotBase.arcadeDrive(.5,0);
    frontR.set(.25);
    rearR.set(.25);
    frontL.set(-.25);
    rearL.set(-.25);
    System.out.println("FR output current" + frontR.getOutputCurrent()); //"Front Right output voltage: " + frontR.getMotorOutputVoltage() + " output percent: " + frontR.getMotorOutputPercent() + 
    System.out.println("RR output current" + rearR.getOutputCurrent()); //"Rear Right output voltage: " + rearR.getMotorOutputVoltage() + "output percent: " + rearR.getMotorOutputPercent()+ 
    System.out.println("FL output current" + frontL.getOutputCurrent()); //"Front Left output voltage: " + frontL.getMotorOutputVoltage() + " output percent: " + frontL.getMotorOutputPercent()+ 
    System.out.println("RL output current" + rearL.getOutputCurrent()); //"Rear Left output voltage: " + rearL.getMotorOutputVoltage() + " output percent: " + rearL.getMotorOutputPercent()+ 
    double leftAvgOutputCurrent = (frontL.getOutputCurrent() + rearL.getOutputCurrent())/2;
    double rightAvgOutputCurrent = (frontR.getOutputCurrent() + rearR.getOutputCurrent())/2;
    System.out.println("Left Avg Curret " + leftAvgOutputCurrent);
    System.out.println("Right Avg Current " + rightAvgOutputCurrent);
    System.out.println("");
  
  }

  @Override
  public void teleopInit() {}

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
