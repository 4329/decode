package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import java.util.Map;

public abstract class ThreePointCloseAuto extends PedroAuto {

  @Override
  public void initialize() {
    super.initialize();

    Map<String, PathChain> paths = pathFactory.getClosePaths();

    SequentialCommandGroup autoCommandGroup = new SequentialCommandGroup(
        new FollowPathCommand(follower, paths.get(PathFactory.CLOSE_OBELISK_READ), true, .5),
        new WaitCommand(2000),
        new FollowPathCommand(follower, paths.get(PathFactory.CLOSE_SHOOTY), true),
        new WaitCommand(2000),
        new FollowPathCommand(follower, paths.get(PathFactory.CLOSE_OFF_LINE), true)
    );
    schedule(autoCommandGroup);
  }
}
