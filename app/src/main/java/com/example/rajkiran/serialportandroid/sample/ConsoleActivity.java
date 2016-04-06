/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.example.rajkiran.serialportandroid.sample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.example.rajkiran.serialportandroid.R;

import java.io.IOException;
import java.util.logging.Logger;

public class ConsoleActivity extends SerialPortActivity {

	EditText mReception;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.console);

//		setTitle("Loopback test");
		mReception = (EditText) findViewById(R.id.EditTextReception);

		EditText Emission = (EditText) findViewById(R.id.EditTextEmission);
		Emission.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

				if (actionId == EditorInfo.IME_ACTION_DONE) {
					//some_button.performClick();
					Log.d("imeOptionDone",v.getText().toString());
					CharSequence t = v.getText();
					char[] text = new char[t.length()];
					for (int i = 0; i < t.length(); i++) {
						text[i] = t.charAt(i);
					}
					try {
						mOutputStream.write(new String(text).getBytes());
						mOutputStream.write('\n');
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		});

		/*Emission.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				CharSequence t = s.toString();
				char[] text = new char[t.length()];
				for (int i=0; i<t.length(); i++) {
					text[i] = t.charAt(i);
				}
				try {
					mOutputStream.write(new String(text).getBytes());
					mOutputStream.write('\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

				//handled = true;*//**//*
			}
		});*/
		/*Emission.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				int i;
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					//sendMessage();
					*//*CharSequence t = v.getText();
					char[] text = new char[t.length()];
					for (i=0; i<t.length(); i++) {
						text[i] = t.charAt(i);
					}
					try {
						mOutputStream.write(new String(text).getBytes());
						mOutputStream.write('\n');
					} catch (IOException e) {
						e.printStackTrace();
					}
					handled = true;*//*
				}

				return handled;
			}
		});
	}*/

	}
	@Override
	protected void onDataReceived(final byte[] buffer, final int size) {
		runOnUiThread(new Runnable() {
			public void run() {
				if (mReception != null) {
					mReception.append(new String(buffer, 0, size));
				}
			}
		});
	}
}
