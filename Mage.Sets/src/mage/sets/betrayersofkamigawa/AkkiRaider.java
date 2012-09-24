/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.betrayersofkamigawa;

import java.util.UUID;
import mage.Constants;

import mage.Constants.CardType;
import mage.Constants.Duration;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continious.BoostSourceEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.mageobject.CardTypePredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;

/**
 *
 * @author Loki
 */
public class AkkiRaider extends CardImpl<AkkiRaider> {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("land");
    static {
        filter.add(new CardTypePredicate(CardType.LAND));
    }

    public AkkiRaider(UUID ownerId) {
        super(ownerId, 92, "Akki Raider", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{1}{R}");
        this.expansionSetCode = "BOK";
        this.subtype.add("Goblin");
        this.subtype.add("Warrior");
        this.color.setRed(true);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);
        // Whenever a land is put into a graveyard from the battlefield, Akki Raider gets +1/+0 until end of turn.
        this.addAbility(new AkkiRaiderTriggeredAbility(new BoostSourceEffect(1,0,Duration.EndOfTurn)));
    }

    public AkkiRaider(final AkkiRaider card) {
        super(card);
    }

    @Override
    public AkkiRaider copy() {
        return new AkkiRaider(this);
    }

private class AkkiRaiderTriggeredAbility extends TriggeredAbilityImpl<AkkiRaiderTriggeredAbility> {

    public AkkiRaiderTriggeredAbility(Effect effect) {
        super(Constants.Zone.BATTLEFIELD, effect, false);
    }

    public AkkiRaiderTriggeredAbility(final AkkiRaiderTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.ZONE_CHANGE 
                && ((ZoneChangeEvent)event).getToZone() == Constants.Zone.GRAVEYARD
                && ((ZoneChangeEvent)event).getFromZone() == Constants.Zone.BATTLEFIELD) {
            Permanent permanent = game.getPermanent(event.getTargetId());
            if (permanent == null) {
                permanent = (Permanent) game.getLastKnownInformation(event.getTargetId(), Zone.BATTLEFIELD);
            }
            if (permanent != null && permanent.getCardType().contains(CardType.LAND)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a land is put into a graveyard from the battlefield, " + super.getRule();
    }

    @Override
    public AkkiRaiderTriggeredAbility copy() {
        return new AkkiRaiderTriggeredAbility(this);
    }

}
}
