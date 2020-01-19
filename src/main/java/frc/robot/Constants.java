package frc.robot;

public final class Constants {
    public static final int DRIVETRAIN_LEFT_MOTOR_ONE = 1,    //PDP #0 //TalonFX
                            DRIVETRAIN_LEFT_MOTOR_TWO = 2,    //PDP #1
                            DRIVETRAIN_LEFT_MOTOR_THREE = 3,  //PDP #2
                            DRIVETRAIN_RIGHT_MOTOR_ONE = 4,   //PDP #15
                            DRIVETRAIN_RIGHT_MOTOR_TWO = 5,   //PDP #14
                            DRIVETRAIN_RIGHT_MOTOR_THREE = 6, //PDP #13

                            DRIVETRAIN_LEFT_ENCODER = 1,      //CANCoders
                            DRIVETRAIN_RIGHT_ENCODER = 2,

                            PIGEON_IMU = 1;                   //PIGEON IMU

    
    public static final int DRIVETRAIN_SHIFTING_SOLENOID_FORWARD = 0, //PCM
                            DRIVETRAIN_SHIFTING_SOLENOID_REVERSE = 1;

    public static int pressueSensor = 0; //Analog In

    public static int compressor = 0; //Compressor
}
