package Actions;

import autohearthstone.Action;
import autohearthstone.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bash on 3-11-2014.
 */
public class DoNothing extends Action {

    Player player;

    public DoNothing(Player player)
    {
        this.player = player;
    }

    public void Perform()
    {
        player.pass();
    }
}
