// Copyright (c) FIRST Team 3606 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.demo;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class DemoMechanismPokeCommand extends InstantCommand
{
    public DemoMechanismPokeCommand(DemoMechanism mechanism, boolean poke)
    {
        super(() -> mechanism.poke(poke));
    }
}
