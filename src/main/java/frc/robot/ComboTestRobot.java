// Copyright (c) FIRST Team 3606 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.tools.CommandRobotBase;

/** Test the intake */
public class ComboTestRobot extends CommandRobotBase
{
    private final PowerDistribution power_dist = new PowerDistribution();
    private final Combo combo = new Combo();
   
    public ComboTestRobot()
    {
        
       
        RobotOI.joystick.y().whileTrue(combo.runIntake());
        RobotOI.joystick.a().whileTrue(combo.runClear());
        RobotOI.joystick.b().whileTrue(combo.runShooter());
    }

    @Override
    public void teleopPeriodic()
    {
        
        
        // intake.open(RobotOI.joystick.a().getAsBoolean());
    }

    @Override
    public void autonomousPeriodic()
    {
        // intake.open(true);
    }
}
