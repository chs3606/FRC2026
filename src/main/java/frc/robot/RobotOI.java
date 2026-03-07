// Copyright (c) FIRST Team 3606 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.swervelib.SwerveOI;
import edu.wpi.first.wpilibj2.command.button.Trigger;
/** FRC2026 robot operator interface */
public class RobotOI extends SwerveOI
{
    public static final CommandXboxController joystick = new CommandXboxController(0);
    public static boolean intake(){
        return joystick.a().getAsBoolean();
    }

    public static boolean shooter(){
        return joystick.b().getAsBoolean();
    }
}
