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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Clear button */
    public void clearMessage(View view) {
        EditText editMessage = (EditText) findViewById(R.id.edit_message);
        editMessage.setText("");
        TextView digitCountText = (TextView) findViewById(R.id.text_digit_count);
        digitCountText.setText(getString(R.string.text_unknown));
        TextView issuerText = (TextView) findViewById(R.id.text_issuer);
        issuerText.setText(getString(R.string.text_unknown));
        TextView checksumText = (TextView) findViewById(R.id.text_checksum);
        checksumText.setText(getString(R.string.text_unknown));
        TextView resultText = (TextView) findViewById(R.id.text_result);
        resultText.setText(getString(R.string.text_unknown));
    }

    /** Called when the user clicks the Check button */
    public void checkMessage(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String cardNumber = editText.getText().toString();

        CardNumber numberChecker = new CardNumber(cardNumber);
        //numberChecker.idThis(cardNumber);

        // fill onscreen labels
        TextView digitCountText = (TextView) findViewById(R.id.text_digit_count);
        //digitCount.setText(cardNumber);
        digitCountText.setText(Integer.toString(numberChecker.getLength()));

        TextView issuerText = (TextView) findViewById(R.id.text_issuer);
        issuerText.setText(numberChecker.getIssuer());

        TextView checksumText = (TextView) findViewById(R.id.text_checksum);
        switch (numberChecker.getChecksum()) {
            case Fail:
                checksumText.setText(getString(R.string.text_result_fail));
                break;
            case Pass:
                checksumText.setText(getString(R.string.text_result_pass));
                break;
            case None:
            default:
                checksumText.setText(getString(R.string.text_result_none));
                break;
        }

        TextView resultText = (TextView) findViewById(R.id.text_result);
        switch (numberChecker.getResult()) {
            case Fail:
                resultText.setText(getString(R.string.text_result_fail));
                break;
            case Pass:
                resultText.setText(getString(R.string.text_result_pass));
                break;
            case None:
            default:
                resultText.setText(getString(R.string.text_result_none));
                break;
        }
    }
}


/**
 * Editor Action Listener
 * https://developer.android.com/training/keyboard-input/style.html
 */