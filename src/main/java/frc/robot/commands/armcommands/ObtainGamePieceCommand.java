package frc.robot.commands.armcommands;

import frc.robot.controllers.FlightJoystick;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.staticsubsystems.LimeLight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ObtainGamePieceCommand extends CommandBase {
    private final ArmSubsystem arm;
    private final FlightJoystick joystick;

    public ObtainGamePieceCommand(ArmSubsystem arm, FlightJoystick joystick) {
        this.arm = arm;
        this.joystick = joystick;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(arm);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (joystick.getRawButtonWrapper(1)) { //move arm toward game piece
            float errorX = LimeLight.getXAdjustment();
            float errorY = LimeLight.getYAdjustment();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
