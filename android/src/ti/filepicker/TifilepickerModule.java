/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.filepicker;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.TiApplication;

import android.content.Intent;

@Kroll.module(name = "Tifilepicker", id = "ti.filepicker")
public class TifilepickerModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "TiFilePicker 📲 📲";
	private String[] mimeTypes = { "*/*" };
	private KrollFunction successCallback;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public TifilepickerModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
	}

	private void readOptions(KrollDict opts) {
		Object cb;
		// importing of mime stuff:
		if (opts.containsKeyAndNotNull("mimeTypes")) {
			mimeTypes = opts.getStringArray("mimeTypes");
		}
		if (opts.containsKeyAndNotNull("onSuccess")) {
			cb = opts.get("onSuccess");
			if (cb instanceof KrollFunction) {
				successCallback = (KrollFunction) cb;
			}
		}
	}

	@Kroll.method
	public void getAllFiles(KrollDict opts) {
		readOptions(opts);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		for (String mimeType : mimeTypes) {
			intent.setType(mimeType);
		}

	}
}
