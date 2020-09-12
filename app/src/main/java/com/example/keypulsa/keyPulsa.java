package com.example.keypulsa;


import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;


import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;


import java.util.Arrays;

import static com.example.keypulsa.MainActivity.SHARED_PREFS;
import static com.example.keypulsa.MainActivity.TEXT;

public class keyPulsa extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard,nominal,pln;
    String[] nomor1 = { "0815", "0816","0855","0856","0857","0858","0895","0896","0897","0898","0899"};
    String[] nomor2 = { "0832", "0833", "0838", "0831", "0859", "0877", "0878", "0817","0818","0819"};
    String[] nomor3 = { "0881", "0882", "0883", "0884","0885", "0886", "0887", "0888","0889" };
    String[] berapaan = { "5.", "10.", "15.", "20.", "25.", "50.", "60.", "100.", "PLN" };
    String[] nomor4 = {"0811", "0812", "0813", "0814","0821","0822","0823","0851","0852","0853"};

    @Override
    public void onCreate() {
        super.onCreate();

    }



    @Override
    public View onCreateInputView() {

        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.num);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;

    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {


    }



    @Override
    public void onKey (int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        CharSequence selectedText = ic.getSelectedText(0);

        switch (primaryCode) {
            case 1:
                ExtractedText extractedText = ic.getExtractedText(new ExtractedTextRequest(), 0);
                if (extractedText == null || extractedText.text == null) return;
                int index = extractedText.text.length();
                ic.setSelection(index, index);
                SharedPreferences result = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                String st = result.getString(TEXT, "");
                ic.commitText("." + st, 0);
                break;
            case 2:
                ExtractedText extractedText1 = ic.getExtractedText(new ExtractedTextRequest(), 0);
                if (extractedText1 == null || extractedText1.text == null) return;
                int index1 = extractedText1.text.length();
                ic.setSelection(index1, index1);
                SharedPreferences result1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                String st1 = result1.getString(TEXT, "");
                ic.commitText("." + st1 + ".2", 0);
                break;
            case 3:
                if (TextUtils.isEmpty(selectedText)) {
                    ic.deleteSurroundingText(1, 0);
                } else {
                    ic.commitText("", 1);
                }
                break;
            case 4:
                kv.setKeyboard(keyboard);
                break;
            case 5:
                pln = new Keyboard(this, R.xml.pln);
                kv.setKeyboard(pln);
                ic.setSelection(0, 0);
                break;
            case 6:
                ic.setSelection(0,2);
                String if1 = String.valueOf(ic.getSelectedText(0));

                ic.setSelection(0,3);
                String if2 = String.valueOf(ic.getSelectedText(0));

                ic.setSelection(0,4);
                String if3 = String.valueOf(ic.getSelectedText(0));

                if (Arrays.asList(berapaan).contains(if1)){
                    ic.setSelection(2,6);
                }else if (Arrays.asList(berapaan).contains(if2)){
                    ic.setSelection(3,7);
                }else if (Arrays.asList(berapaan).contains(if3)){
                    ic.setSelection(4,8);
                }

                if (Arrays.asList(nomor1).contains(String.valueOf(ic.getSelectedText(0)))) {
                    nominal = new Keyboard(this, R.xml.nominal_1);
                    kv.setKeyboard(nominal);
                }else
                    if (Arrays.asList(nomor2).contains(String.valueOf(ic.getSelectedText(0)))) {
                    nominal = new Keyboard(this, R.xml.nominal_2);
                    kv.setKeyboard(nominal);
                }else
                    if (Arrays.asList(nomor3).contains(String.valueOf(ic.getSelectedText(0)))) {
                    nominal = new Keyboard(this, R.xml.nominal_3);
                    kv.setKeyboard(nominal);
                }else
                    if (Arrays.asList(nomor4).contains(String.valueOf(ic.getSelectedText(0)))) {
                        nominal = new Keyboard(this, R.xml.nominal_4);
                        kv.setKeyboard(nominal);
                    }
                ic.setSelection(0,0);
                break;


            default:

            char code = (char) primaryCode;
            ic.commitText(String.valueOf(code), 0);
        }


    }


    @Override
    public void onText(CharSequence text) {
        InputConnection ic = getCurrentInputConnection();
        ic.commitText(text, 0);

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }


}
