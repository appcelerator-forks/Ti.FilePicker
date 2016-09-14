/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.filepicker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiBlob;
import org.appcelerator.titanium.util.TiActivityResultHandler;
import org.appcelerator.titanium.util.TiActivitySupport;
import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

@Kroll.module(name = "Tifilepicker", id = "ti.filepicker")
public class TifilepickerModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "TiFilePicker 📲 📲";
	private static final int RC = 42;
	private String[] mimeTypes = { "*/*" };
	private KrollFunction successCallback;
	private static Context ctx;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public TifilepickerModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		ctx = TiApplication.getInstance().getApplicationContext();
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
	public void pick(KrollDict opts) {
		getAllFiles(opts);
	}

	@Kroll.method
	public void getFileSelectDialog(KrollDict opts) {
		getAllFiles(opts);
	}

	@Kroll.method
	public void getAllFiles(KrollDict opts) {
		readOptions(opts);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		// iterating thrue all mimetypes:
		for (String mimeType : mimeTypes) {
			intent.setType(mimeType);
		}
		TiActivitySupport activitySupport = (TiActivitySupport) TiApplication
				.getInstance().getCurrentActivity();
		// this is the trick:
		activitySupport.launchActivityForResult(intent, RC,
				new TiActivityResultHandler() {
					public void onError(Activity arg0, int arg1, Exception arg2) {

					}

					public void onResult(Activity dummy, int requestCode,
							int resultCode, Intent data) {
						if (requestCode == RC) {
							Uri uri = data.getData();
							// write to console
							Log.d(LCAT, uri.toString());

							try {
								ContentResolver cR = ctx.getContentResolver();
								MimeTypeMap mime = MimeTypeMap.getSingleton();

								InputStream inStream = TiApplication
										.getInstance().getApplicationContext()
										.getContentResolver()
										.openInputStream(uri);
								byte[] bytes;
								try {
									bytes = IOUtils.toByteArray(inStream);
									TiBlob tiBlob = TiBlob.blobFromData(bytes,
											mime.getExtensionFromMimeType(cR
													.getType(uri)));
									KrollDict dict = new KrollDict();
									dict.put("result", tiBlob);
									successCallback
											.call(getKrollObject(), dict);
								} catch (IOException e) {
									e.printStackTrace();
								}
								// static dont need instance

							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				}

		);

	}
}
