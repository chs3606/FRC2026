// Copyright (c) FIRST Team 3606 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.tools.CommandRobotBase;

/** Test the intake */
public class IntakeTestRobot extends CommandRobotBase
{
    private final PowerDistribution power_dist = new PowerDistribution();
    private final Intake intake = new Intake();
    private final double intakeVoltages[] = {0, 4.5}; // voltages for intaking to hopper, 0 = Motor 4, 1 = Motor 7 (?)
    private final double shooterVoltages[] = {4.5, -7.5}; // voltages for shooting
    public IntakeTestRobot()
    {
        
    SmartDashboard.putNumber("Spinner 1 Voltage", 3);
    SmartDashboard.putNumber("Spinner 2 Voltage", 3);
        power_dist.clearStickyFaults();
        SmartDashboard.putData("Power", power_dist);
    }

    @Override
    public void teleopPeriodic()
    {
        boolean isIntake = RobotOI.intake();
        boolean isShooter = RobotOI.shooter();
        if(isIntake){
            intake.spin(intakeVoltages);
        }else if(isShooter){
            intake.spin(shooterVoltages);
        }
        
        // intake.open(RobotOI.joystick.a().getAsBoolean());
    }

    @Override
    public void autonomousPeriodic()
    {
        // intake.open(true);
    }
}
