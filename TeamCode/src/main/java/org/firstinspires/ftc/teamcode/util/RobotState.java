package org.firstinspires.ftc.teamcode.util;

public class RobotState {
private SpindexerMode spindexerModes = SpindexerMode.UNKNOWN;
private ArtifactOrder artifactOrder = ArtifactOrder.unknown;
private boolean robotOriented = true;
    public SpindexerMode getSpindexerModes() {
        return spindexerModes;
    }

    public void setSpindexerModes(SpindexerMode spindexerModes) {
        this.spindexerModes = spindexerModes;
    }

    public void wehadID(int tag) {
        if (tag == 21) {
            artifactOrder = ArtifactOrder.GPP;
        }
        else if (tag == 22) {
            artifactOrder = ArtifactOrder.PGP;
        }
        else if (tag == 23) {
            artifactOrder = ArtifactOrder.PPG;
        }
        else {
         artifactOrder = ArtifactOrder.unknown;
        }
    }

    public ArtifactOrder getArtifactOrder() {
        return artifactOrder;
    }

    public void toggleDriveMode() {
        robotOriented = !robotOriented;
    }

    public boolean isRobotOriented() {
        return robotOriented;
    }
}