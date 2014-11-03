package Cards;

import Actions.AttackHero;
import Actions.AttackMinion;
import Actions.PlayVanilla;
import autohearthstone.Action;
import autohearthstone.Card;
import autohearthstone.Minion;

import java.util.ArrayList;

/**
 * Created by Bash on 3-11-2014.
 */
public class ChillwindYeti extends Minion {


    public ChillwindYeti()
    {
        super.cost = 4;
        super.power = 4;
        super.toughness = 5;
        super.damage = 0;
        super.taunt = false;
        super.stealth = false;
        super.silenced = false;
        super.frozen = false;
        super.attacked = false;
        super.summoningSick = true;
    }

    public ArrayList<Action> generateActionsOnBoard()
    {
        ArrayList<Action> result = new ArrayList<Action>();
        //attackignoringtaunts
        if (!attacked && !summoningSick)
        {
            for (Card card : super.ap.getOpponent().getBoard())
            {
                result.add(new AttackMinion(this, (Minion)card));
            }
            result.add(new AttackHero(this, super.ap.getOpponent()));
        }

        return result;
    }

    public ArrayList<Action> generateActionsInHand()
    {
        ArrayList<Action> result = new ArrayList<Action>();

        if (ap.getFullManaCrystal() >= cost)
        {
            result.add(new PlayVanilla(this));
        }

        return result;

    }
}
