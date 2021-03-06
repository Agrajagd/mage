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
package mage.sets.theros;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.UntapTargetEffect;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.permanent.AnotherPredicate;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author LevelX2
 */
public class BreachingHippocamp extends CardImpl<BreachingHippocamp> {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("another target creature you control");
    static {
        filter.add(new AnotherPredicate());
    }

    public BreachingHippocamp(UUID ownerId) {
        super(ownerId, 43, "Breaching Hippocamp", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{3}{U}");
        this.expansionSetCode = "THS";
        this.subtype.add("Horse");
        this.subtype.add("Fish");

        this.color.setBlue(true);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Flash
        this.addAbility(FlashAbility.getInstance());
        // When Breaching Hippocamp enters the battlefield, untap another target creature you control.
        Ability ability = new EntersBattlefieldTriggeredAbility(new UntapTargetEffect(), false);
        ability.addTarget(new TargetControlledCreaturePermanent(1,1,filter, false, true));
        this.addAbility(ability);
    }

    public BreachingHippocamp(final BreachingHippocamp card) {
        super(card);
    }

    @Override
    public BreachingHippocamp copy() {
        return new BreachingHippocamp(this);
    }
}
