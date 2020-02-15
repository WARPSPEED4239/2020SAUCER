package frc.robot;

public final class Constants {
    public static final int DRIVETRAIN_LEFT_MOTOR_ONE = 1,    //PDP #0 //TalonFX
                            DRIVETRAIN_LEFT_MOTOR_TWO = 2,    //PDP #1
                            DRIVETRAIN_LEFT_MOTOR_THREE = 3,  //PDP #2
                            DRIVETRAIN_RIGHT_MOTOR_ONE = 4,   //PDP #15
                            DRIVETRAIN_RIGHT_MOTOR_TWO = 5,   //PDP #14
                            DRIVETRAIN_RIGHT_MOTOR_THREE = 6, //PDP #13
                            
                            ELEVATOR_MOTOR = 1,               //PDP #X //TalonSRX //775Pro
                            FEEDER_WHEELS_MOTOR = 2,          //PDP #4 ENCODER //775Pro
                            HOPPER_MOTOR = 3,                 //PDP #X ENCODER //775Pro
                            INTAKE_MOTORS = 4,                //PDP #X //Bags
                            PANNEL_SPINNER_MOTOR = 5,         //PDP #X ENCODER //Bag
                            SHOOTER_MOTOR_TOP = 6,            //PDP #X ENCODER //Redline
                            SHOOTER_MOTOR_BOTTOM = 7,         //PDP #X //Redline
                            TURRET_MOTOR = 8,                 //PDP #X ENCODER //Bag

                            CLIMBER_MOTOR = 1;                //PDP #X //SparkMAX //NEO

    public static final int SHOOTER_LEFT_SERVO = 0,           //PWM
                            SHOOTER_RIGHT_SERVO = 1,
        
                            TURRET_LEFT_LIMIT = 0,            //DIO
                            TURRET_RIGHT_LIMIT = 1,

                            PRESSURE_SENSOR = 0,              //Analog In

                            PIGEON_IMU = 1;                   //Pigeon IMU
    
    public static final int DRIVETRAIN_SHIFTING_SOLENOID_FORWARD = 0, //PCM
                            DRIVETRAIN_SHIFTING_SOLENOID_REVERSE = 1,
                            INTAKE_SOLENOID_FORWARD = 2,
                            INTAKE_SOLENOID_REVERSE = 3,
                            PANNEL_SPINNER_SOLENOID_FORWARD = 4,
                            PANNEL_SPINNER_SOLENOID_REVERSE = 5,
                            RAMP_SOLENOID_FORWARD = 6,
                            RAMP_SOLENOID_REVERSE = 7,

                            COMPRESSOR = 0;                           //Compressor

    public static final int TIMEOUT_MS = 30,
                            COUNTS_PER_REVOLUTION_ENCODER = 4096;
                           
    public static final double EPSILON = 0.0001;
}
