package frc.robot;

public final class Constants {
    public static final int DRIVETRAIN_LEFT_MOTOR_ONE = 9,    //PDP #0 //TalonFX
                            DRIVETRAIN_LEFT_MOTOR_TWO = 10,    //PDP #1
                            DRIVETRAIN_LEFT_MOTOR_THREE = 11,  //PDP #2
                            DRIVETRAIN_RIGHT_MOTOR_ONE = 12,   //PDP #15
                            DRIVETRAIN_RIGHT_MOTOR_TWO = 13,   //PDP #14
                            DRIVETRAIN_RIGHT_MOTOR_THREE = 14, //PDP #13
                            
                            ELEVATOR_MOTOR = 2,               //PDP #X //TalonSRX //775Pro
                            FEEDER_WHEELS_MOTOR = 7,          //PDP #4 ENCODER //775Pro
                            HOPPER_MOTOR = 6,                 //PDP #X ENCODER //775Pro
                            INTAKE_MOTORS = 1,                //PDP #X //Bags
                            PANNEL_SPINNER_MOTOR = 3,         //PDP #X ENCODER //Bag
                            SHOOTER_MOTOR_TOP = 5,            //PDP #X ENCODER //Redline
                            SHOOTER_MOTOR_BOTTOM = 1,         //PDP #X //Redline
                            TURRET_MOTOR = 4,                 //PDP #X //Bag

                            CLIMBER_MOTOR = 1;                //PDP #X //SparkMAX //NEO

                                public static final int ANGLE_ADJUST_SERVO = 0,           //PWM
        
                            TURRET_LEFT_LIMIT = 0,            //DIO
                            TURRET_RIGHT_LIMIT = 1,

                            PRESSURE_SENSOR = 0,              //Analog In

                            PIGEON_IMU = 1;                   //Pigeon IMU
    
    public static final int DRIVETRAIN_SHIFTING_SOLENOID_FORWARD = 4, //PCM
                            DRIVETRAIN_SHIFTING_SOLENOID_REVERSE = 5,
                            INTAKE_SOLENOID_FORWARD = 6,
                            INTAKE_SOLENOID_REVERSE = 7,
                            PANNEL_SPINNER_SOLENOID_FORWARD = 0,
                            PANNEL_SPINNER_SOLENOID_REVERSE = 1,
                            RAMP_SOLENOID_FORWARD = 2,
                            RAMP_SOLENOID_REVERSE = 3,

                            COMPRESSOR = 0;                           //Compressor

    public static final int TIMEOUT_MS = 30,
                            COUNTS_PER_REVOLUTION_ENCODER = 4096;
                           
    public static final double EPSILON = 0.0001;
}
