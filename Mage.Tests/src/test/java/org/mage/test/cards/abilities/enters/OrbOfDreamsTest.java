 package org.mage.test.cards.abilities.enters;

import mage.Constants;
import mage.game.permanent.Permanent;
import org.junit.Assert;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 *
 * @author LevelX2
 */

/**
 * 614.12. Some replacement effects modify how a permanent enters the battlefield. (See rules 614.1c-d.)
 * Such effects may come from the permanent itself if they affect only that permanent (as opposed to a
 * general subset of permanents that includes it). They may also come from other sources. To determine
 * which replacement effects apply and how they apply, check the characteristics of the permanent as it
 * would exist on the battlefield, taking into account replacement effects that have already modified
 * how it enters the battlefield (see rule 616.1), continuous effects generated by the resolution of
 * spells or abilities that changed the permanent's characteristics on the stack (see rule 400.7a), and
 * continuous effects from the permanent's own static abilities, but ignoring continuous effects from
 * any other source that would affect it. #
 *
 * Example: Orb of Dreams is an artifact that says "Permanents enter the battlefield tapped." It won't
 * affect itself, so Orb of Dreams enters the battlefield untapped.
 *
 */
public class OrbOfDreamsTest extends CardTestPlayerBase {

    @Test
    public void testOrbNotTappingItself() {
        addCard(Constants.Zone.BATTLEFIELD, playerA, "Forest", 5);
        addCard(Constants.Zone.HAND, playerA, "Orb of Dreams");
        addCard(Constants.Zone.HAND, playerA, "Razortip Whip");

        castSpell(1, Constants.PhaseStep.PRECOMBAT_MAIN, playerA, "Orb of Dreams");
        castSpell(1, Constants.PhaseStep.POSTCOMBAT_MAIN, playerA, "Razortip Whip");

        setStopAt(1, Constants.PhaseStep.END_TURN);
        execute();

        assertLife(playerA, 20);
        assertLife(playerB, 20);

        assertPermanentCount(playerA, "Orb of Dreams", 1);
        assertPermanentCount(playerA, "Razortip Whip", 1);

        Permanent orbOfDreams = getPermanent("Orb of Dreams", playerA.getId());
        Assert.assertFalse("Orb has tapped itself, but may not", orbOfDreams.isTapped());
        Permanent razortipWhip = getPermanent("Razortip Whip", playerA.getId());
        Assert.assertTrue("Razortip Whip must be tapped from Orb of Dreams but isn't", razortipWhip.isTapped());
    }

}