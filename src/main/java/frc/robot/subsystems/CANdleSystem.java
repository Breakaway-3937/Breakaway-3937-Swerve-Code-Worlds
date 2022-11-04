// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import com.ctre.phoenix.led.*;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import java.lang.Math;


public class CANdleSystem extends SubsystemBase {
    private final CANdle candle = new CANdle(Constants.CANDLE_ID, "CANivore");
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    private boolean flag = false;
    private int count = 8;
    private int count2 = 1;
    private int count3 = 8;
    private int count4 = 8;
    //private final int ledCount = 300;
    //private XboxController xboxController;

    //private Animation toAnimate = null;

    public enum AnimationTypes {
        ColorFlow,
        Fire,
        Larson,
        Rainbow,
        RgbFade,
        SingleFade,
        Strobe,
        Twinkle,
        TwinkleOff,
        SetAll
    }
    //private AnimationTypes currentAnimation;

    public CANdleSystem(XboxController xboxController) {
        //this.xboxController = xboxController;
        //changeAnimation(AnimationTypes.SetAll);
        CANdleConfiguration configAll = new CANdleConfiguration();
        configAll.statusLedOffWhenActive = true;
        configAll.disableWhenLOS = false;
        configAll.stripType = LEDStripType.GRB;
        configAll.brightnessScalar = 0.1;
        configAll.vBatOutputMode = VBatOutputMode.Modulated;
        candle.configAllSettings(configAll, 100);
        timer.start();
        timer2.start();
        candle.setLEDs(0, 0, 0, 0, 0, 400);
    }

    /*public void incrementAnimation() {
        switch(currentAnimation) {
            case ColorFlow: changeAnimation(AnimationTypes.Fire); break;
            case Fire: changeAnimation(AnimationTypes.Larson); break;
            case Larson: changeAnimation(AnimationTypes.Rainbow); break;
            case Rainbow: changeAnimation(AnimationTypes.RgbFade); break;
            case RgbFade: changeAnimation(AnimationTypes.SingleFade); break;
            case SingleFade: changeAnimation(AnimationTypes.Strobe); break;
            case Strobe: changeAnimation(AnimationTypes.Twinkle); break;
            case Twinkle: changeAnimation(AnimationTypes.TwinkleOff); break;
            case TwinkleOff: changeAnimation(AnimationTypes.ColorFlow); break;
            case SetAll: changeAnimation(AnimationTypes.ColorFlow); break;
        }
    }
    public void decrementAnimation() {
        switch(currentAnimation) {
            case ColorFlow: changeAnimation(AnimationTypes.TwinkleOff); break;
            case Fire: changeAnimation(AnimationTypes.ColorFlow); break;
            case Larson: changeAnimation(AnimationTypes.Fire); break;
            case Rainbow: changeAnimation(AnimationTypes.Larson); break;
            case RgbFade: changeAnimation(AnimationTypes.Rainbow); break;
            case SingleFade: changeAnimation(AnimationTypes.RgbFade); break;
            case Strobe: changeAnimation(AnimationTypes.SingleFade); break;
            case Twinkle: changeAnimation(AnimationTypes.Strobe); break;
            case TwinkleOff: changeAnimation(AnimationTypes.Twinkle); break;
            case SetAll: changeAnimation(AnimationTypes.ColorFlow); break;
        }
    }
    /*public void setColors() {
        changeAnimation(AnimationTypes.SetAll);
    }*/

    /* Wrappers so we can access the CANdle from the subsystem */
    public double getVbat() { return candle.getBusVoltage(); }
    public double get5V() { return candle.get5VRailVoltage(); }
    public double getCurrent() { return candle.getCurrent(); }
    public double getTemperature() { return candle.getTemperature(); }
    public void configBrightness(double percent) { candle.configBrightnessScalar(percent, 0); }
    public void configLos(boolean disableWhenLos) { candle.configLOSBehavior(disableWhenLos, 0); }
    public void configLedType(LEDStripType type) { candle.configLEDType(type, 0); }
    public void configStatusLedBehavior(boolean offWhenActive) { candle.configStatusLedState(offWhenActive, 0); }

    /*public void changeAnimation(AnimationTypes toChange) {
        currentAnimation = toChange;
        
        switch(toChange)
        {
            case ColorFlow:
                toAnimate = new ColorFlowAnimation(128, 20, 70, 0, 0.7, ledCount, Direction.Forward);
                break;
            case Fire:
                toAnimate = new FireAnimation(0.5, 0.7, ledCount, 0.7, 0.5);
                break;
            case Larson:
                toAnimate = new LarsonAnimation(0, 255, 46, 0, 1, ledCount, BounceMode.Front, 3);
                break;
            case Rainbow:
                toAnimate = new RainbowAnimation(1, 0.1, ledCount);
                break;
            case RgbFade:
                toAnimate = new RgbFadeAnimation(0.7, 0.4, ledCount);
                break;
            case SingleFade:
                toAnimate = new SingleFadeAnimation(50, 2, 200, 0, 0.5, ledCount);
                break;
            case Strobe:
                toAnimate = new StrobeAnimation(240, 10, 180, 0, 98.0 / 256.0, ledCount);
                break;
            case Twinkle:
                toAnimate = new TwinkleAnimation(30, 70, 60, 0, 0.4, ledCount, TwinklePercent.Percent6);
                break;
            case TwinkleOff:
                toAnimate = new TwinkleOffAnimation(70, 90, 175, 0, 0.8, ledCount, TwinkleOffPercent.Percent100);
                break;
            case SetAll:
                toAnimate = null;
                break;
        }
        System.out.println("Changed to " + currentAnimation.toString());
    }*/

    public void chase(int num){
        candle.setLEDs(255, 0, 0, 0, num, 2);
        candle.setLEDs(0, 0, 0, 0, num - 8, 2);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        /*if(timer.get() > 0.25 && !flag){
            candle.setLEDs(0, 0, 0, 0, 0, 350);
            for(int i = 8; i < 308; i += 2){
                candle.setLEDs(255, 0, 0, 0, i, 1);
            }
            timer.reset();
            flag = true;
        }
        else if(timer.get() > 0.25 && flag){
            candle.setLEDs(0, 0, 0, 0, 0, 350);
            for(int i = 9; i < 308; i += 2){
                candle.setLEDs(0, 0, 255, 0, i, 1);
            }
            timer.reset();
            flag = false;
        }*/
        /*if(timer.get() > 0.05){
            chase(count);
            count++;
            if(count > 309){
                count = 8;
            }
        }*/
        /*if(timer.get() > 0.05 && (int)(Math.random() * 10) + 1 > 5){
            candle.setLEDs(0, 255, 0, 0, 10, 5);
            candle.setLEDs(0, 0, 0, 0, 0, 500);
            timer.reset();
        }*/


        if(Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) > 50 && Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) < 145){

        }
        else if(Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) < 50 || (Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) < 360 && Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) > 325)){
            
        }
        else if(Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) > 230 && Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) < 320){
            
        }
        else if(Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) > 145 && Robot.m_robotContainer.s_DriveTrain.mod0Cancoder.getDouble(0.0) < 230){
            
        }
        //white = 234,221,202
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
