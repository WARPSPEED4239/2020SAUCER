package frc.robot.commands.autonomous;

public class SendableChoosers {
    public static enum StartingPosition {
		OpponetTrenchPara, OpponetTrenchPerp, LeftPara, LeftPerp, CenterPara, CenterPerp, RightPara, RightPerp, TrenchPara, TrenchPerp
    }
    
    public static enum TargetTask {
        DriveForward, DriveForwardNoSensors
    }
}