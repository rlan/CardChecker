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



public class CardNumber {

    public enum Result { None, Pass, Fail }

    public CardNumber() {
        number_ = "";
        issuer_ = Issuer.Unknown;
        checksum_ = Result.None;
    }
    public CardNumber(String number) {
        idThis(number);
    }

    public void idThis(String number) {
        number_ = number.replaceAll("\\s+",""); // remove non number chars from string
        issuer_ = idIssuer(number_);
        checksum_ = runChecksum();
    }

    public int getLength() { return number_.length(); }

    public String getIssuer() { return issuer_.getName(); }

    public Result getChecksum() { return checksum_; }

    public Result getResult() {
        if (issuer_.isActive()) {
            return checksum_;
        } else {
            return Result.Fail;
        }
    }

    private Issuer idIssuer(String number) {

        // convert first 1, 2, 3, 4, 5, 6 digits each to integers
        int prefixes[] = {0,0,0,0,0,0};
        for (int numDigits = 1; numDigits <= Math.min(prefixes.length, number.length()); numDigits++) {
            if (number.length() >= numDigits) {
                prefixes[numDigits-1] = Integer.parseInt(number.substring(0, numDigits));
            }
        }

        if (number.length() == 0) {
            return Issuer.Unknown;
        } else {
            switch (prefixes[0]) {
                case 1:
                    return Issuer.UATP;
                case 2:
                    if (prefixes[3]==2014) {
                        return Issuer.EnRoute;
                    } else if (prefixes[3]==2149) {
                        return Issuer.EnRoute;
                    } else if ((prefixes[3]>=2200) && (prefixes[3]<=2204)) {
                        return Issuer.MIR;
                    } else if ((prefixes[3]>=2221) && (prefixes[3]<=2720)) {
                        return Issuer.MasterCard;
                    } else {
                        return Issuer.Unknown;
                    }
                case 3:
                    if (prefixes[1]==34) {
                        return Issuer.AmericanExpress;
                    } else if (prefixes[1]==36) {
                        return Issuer.International;
                    } else if (prefixes[1]==37) {
                        return Issuer.AmericanExpress;
                    } else if (prefixes[1]==38) {
                        return Issuer.International;
                    } else if (prefixes[1]==39) {
                        return Issuer.International;
                    } else if ((prefixes[2]>=300) && (prefixes[2]<=305)) {
                        return Issuer.CarteBlanche;
                    } else if (prefixes[2]==309) {
                        return Issuer.International;
                    } else if ((prefixes[3]>=3528) && (prefixes[3]<=3589)) {
                        return Issuer.JCB;
                    } else {
                        return Issuer.Unknown;
                    }
                case 4:
                    return Issuer.Visa;
                case 5:
                    if (prefixes[1]==50) {
                        return Issuer.Maestro;
                    } else if ((prefixes[1]>=51) && (prefixes[1]<=55)) {
                        return Issuer.MasterCard;
                    } else if ((prefixes[1]>=56) && (prefixes[1]<=59)) {
                        return Issuer.Maestro;
                    } else {
                        return Issuer.Unknown;
                    }
                case 6:
                    return Issuer.Maestro; //60-69
                case 7:
                case 8:
                case 9:
                    return Issuer.Unknown;
                default:
                    return Issuer.Unknown;
            }
        }
    }

    private Result runChecksum() {
        if (issuer_.hasValidation()) {
            if (LuhnAlgorithm.luhnTest(number_)) {
                return Result.Pass;
            } else {
                return Result.Fail;
            }
        } else {
            return Result.None;
        }
    }

    private String number_;
    private Issuer issuer_;
    private Result checksum_;
}
