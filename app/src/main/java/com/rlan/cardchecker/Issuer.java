/**
 Copyright 2016 Rick Lan

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.rlan.cardchecker;

/**
 * Card Issuer characteristic
 * See table in Issuer identification number (IIN) section
 *
 * https://en.wikipedia.org/wiki/Payment_card_number
 */

public enum Issuer {
    Unknown("Unknown",                            false, new int[]{0},                       false),
    AmericanExpress("American Express",           true,  new int[]{15},                      true),
    Bankcard("Bankcard",                          false, new int[]{16},                      true),
    UnionPay("China UnionPay",                    true,  new int[]{16,17,18,19},             true),
    CarteBlanche("Diners Club Carte Blanche",     true,  new int[]{14},                      true),
    EnRoute("Diners Club enRoute",                false, new int[]{15},                      false),
    International("Diners Club International",    true,  new int[]{14},                      true),
    USACanada("Diners Club USA & Canada",         true,  new int[]{16},                      true),
    Discover("Discover Card",                     true,  new int[]{16,19},                   true),
    InterPayment("InterPayment",                  true,  new int[]{16,17,18,19},             true),
    InstaPayment("InstaPayment",                  true,  new int[]{16},                      true),
    JCB("JCB",                                    true,  new int[]{16},                      true),
    Laser("Laser",                                false, new int[]{16,17,18,19},             true),
    Maestro("Maestro",                            true,  new int[]{12,13,14,15,16,17,18,19}, true),
    Dankort("Dankort",                            true,  new int[]{16},                      true),
    MIR("MIR",                                    true,  new int[]{16},                      true),
    MasterCard("MasterCard",                      true,  new int[]{16},                      true),
    Solo("Solo",                                  false, new int[]{16,18,19},                true),
    Switch("Switch",                              false, new int[]{16,18,19},                true),
    Visa("Visa",                                  true,  new int[]{13,16,19},                true),
    UATP("UATP",                                  true,  new int[]{15},                      true),
    Verve("Verve",                                true,  new int[]{16,19},                   true),
    CardGuard("CARDGUARD EAD BG ILS",             true,  new int[]{16},                      true);


    private final String name;
    private final boolean active;
    private final int[] lengths;
    private final boolean hasValidation;

    Issuer(String name, boolean active, int[] lengths, boolean hasValidation) {
        this.name = name;
        this.active = active;
        this.lengths = lengths;
        this.hasValidation = hasValidation;
    }

    String getName() { return name; }
    boolean isActive() { return active; }
    boolean lengthExists(int length) {
        boolean detected = false;
        for (int item : lengths) {
            if (item == length)
                detected = true;
        }
        return detected;
    }
    boolean hasValidation() { return hasValidation; }

}
