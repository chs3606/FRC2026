// Copyright (c) FIRST Team 3606 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.FuelHandler.States;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;


/** Intake */
public class Combo extends SubsystemBase
{

  enum States {
    Idle,
    Idling,
    Intake,
    Shooter,
    Clear

  }
 
  private static double[][] voltages = {
    {0, -4.5}, // Intake
    {0, 9}, // Shooter
    {10, 10}, // Clear; Purge everything
    {0, 0} // Ensures the motors are stopping/stopped
  };
  private States state = States.Idle;
  SparkMax[] motors = new SparkMax[2];
  @SuppressWarnings("removal")
public Combo()
  {
   
    motors[0] = MotorHelper.createSparkMax(4, false, false, 0, true);
    motors[1] = MotorHelper.createSparkMax(7, false, false, 0, true);
   
  }

  public void changeState(States newState){
    if(state == States.Idle || state != newState){
      state = newState;
    } else{
      state = States.Idling;
    }
  }

  // public Command toggleCombo(){
  //     return new InstantCommand(() ->
  //       {
  //           if (state == States.Moving)
  //               state = States.Idle;
  //           else
  //               state = States.Moving;
  //       });
  // }

  public Command runIntake(){
    
    return new InstantCommand(() ->
    {
      changeState(States.Intake);
    });
  }

  public Command runClear(){
    return new InstantCommand(() ->
    {
      changeState(States.Clear);
    });
  }

  public Command runShooter(){
    return new InstantCommand(() -> {
      changeState(States.Shooter);
    });
  }
 
   
   private void spin(int dimensionalIndex){
    int index = 0; 
    SmartDashboard.putNumber("Combo State", dimensionalIndex + 1);
    for (SparkMax motor : motors){
   
    double volt = voltages[dimensionalIndex][index];

    
    
    if(Robot.isSimulation()){
      System.out.println("Setting voltage " + volt );
    }else{
     motor.setVoltage(volt);
    }
    SmartDashboard.putNumber("Spinner " + index + " Setpoint Voltage",volt);
     
     index++;
    }

      
  }


  public void teleopInit(){
    SmartDashboard.putNumber("Combo State", -1);
    state = States.Idle;
  }
  @Override
  public void periodic(){
   
     if(state == States.Intake){
      spin(0);
     } else if(state == States.Shooter){
      spin(1);
     } else if(state == States.Clear){
      spin(2);
     }else if(state == States.Idling){
      if(Robot.isSimulation()){
        System.out.println("Idling...");
      }
      spin(3);
      SmartDashboard.putNumber("Combo State", -1);
      state = States.Idle;
     }
  }
    
}