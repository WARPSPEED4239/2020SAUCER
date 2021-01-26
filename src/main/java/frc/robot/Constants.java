package frc.robot;

public final class Constants {
    public static final int SHOOTER_MOTOR_TOP = 1,            //PDP #X //TalonSRX ENCODER Redline
                            FEEDER_WHEELS_MOTOR = 2,          //PDP #4 ENCODER 775Pro
                            PANNEL_SPINNER_MOTOR = 3,         //PDP #X Bag
                            TURRET_MOTOR = 4,                 //PDP #X Bag
                            SHOOTER_MOTOR_BOTTOM = 5,         //PDP #X Redline
                            HOPPER_MOTOR = 6,                 //PDP #X ENCODER 775Pro
                            ELEVATOR_MOTOR = 7,               //PDP #X 775Pro
                            INTAKE_MOTORS = 8,                //PDP #X Bags
                            CLIMBER_MOTOR = 9,                //PDP #X 775Pro

                            DRIVETRAIN_LEFT_MOTOR_ONE = 10,    //PDP #0 //TalonFX
                            DRIVETRAIN_LEFT_MOTOR_TWO = 11,    //PDP #1
                            DRIVETRAIN_LEFT_MOTOR_THREE = 12,  //PDP #2
                            DRIVETRAIN_RIGHT_MOTOR_ONE = 13,   //PDP #15
                            DRIVETRAIN_RIGHT_MOTOR_TWO = 14,   //PDP #14
                            DRIVETRAIN_RIGHT_MOTOR_THREE = 15; //PDP #13

    public static final int ANGLE_ADJUST_SERVO = 0,           //PWM
    
                            TURRET_LEFT_LIMIT = 0,            //DIO
                            TURRET_RIGHT_LIMIT = 1,

                            PRESSURE_SENSOR = 0,              //Analog In

                            PIGEON_IMU = 1;                   //Pigeon IMU
    
    public static final int DRIVETRAIN_SHIFTING_SOLENOID_FORWARD = 5, //PCM
                            DRIVETRAIN_SHIFTING_SOLENOID_REVERSE = 4,
                            INTAKE_SOLENOID_FORWARD = 7,
                            INTAKE_SOLENOID_REVERSE = 6,
                            PANNEL_SPINNER_SOLENOID_FORWARD = 0,
                            PANNEL_SPINNER_SOLENOID_REVERSE = 1,
                            RAMP_SOLENOID_FORWARD = 2,
                            RAMP_SOLENOID_REVERSE = 3,

                            COMPRESSOR = 0;                           //Compressor

    public static final int TIMEOUT_MS = 30,
                            COUNTS_PER_REVOLUTION_ENCODER = 4096;
                           
    public static final double EPSILON = 0.0001,
                               FEEDER_WHEELS_RPM = 3000.0,
                               HOPPER_RPM = 40.0,
                               SHOOTER_RPM = 4250.0;
}
