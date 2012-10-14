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
package mage.sets.returntoravnica;

import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DestroyAllEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.keyword.OverloadAbility;
import mage.cards.CardImpl;
import mage.filter.common.FilterArtifactPermanent;
import mage.filter.predicate.permanent.ControllerPredicate;
import mage.target.common.TargetArtifactPermanent;


/**
 *
 * @author LevelX2
 */
public class Vandalblast extends CardImpl<Vandalblast> {

    private static final FilterArtifactPermanent filter = new FilterArtifactPermanent("artifact you don't control");

    static {
        filter.add(new ControllerPredicate(Constants.TargetController.NOT_YOU));
    }

    public Vandalblast(UUID ownerId) {
        super(ownerId, 111, "Vandalblast", Rarity.UNCOMMON, new CardType[]{CardType.SORCERY}, "{R}");
        this.expansionSetCode = "RTR";

        this.color.setRed(true);

        // Destroy target artifact you don't control.
        this.getSpellAbility().addTarget(new TargetArtifactPermanent(filter));
        this.getSpellAbility().addEffect(new DestroyTargetEffect());

        // Overload {4}{R} (You may cast this spell for its overload cost. If you do, change its text by replacing all instances of "target" with "each.")
        this.addAbility(new OverloadAbility(this, new DestroyAllEffect(filter), new ManaCostsImpl("{4}{R}")));

    }

    public Vandalblast(final Vandalblast card) {
        super(card);
    }

    @Override
    public Vandalblast copy() {
        return new Vandalblast(this);
    }
}