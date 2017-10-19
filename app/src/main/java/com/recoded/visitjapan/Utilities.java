package com.recoded.visitjapan;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by wisam on Oct 18 17.
 */

public class Utilities {


    public static ArrayList<Image> parseGalleryXML(Context c) throws XmlPullParserException, IOException {

        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream in_s = c.getAssets().open("gallery.xml");
        parser.setInput(in_s, null);

        ArrayList<Image> gallery = new ArrayList<>();
        int eventType = parser.getEventType();
        Image currentImg = null;
        String tagName;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {

                case XmlPullParser.START_TAG:
                    tagName = parser.getName();

                    if (tagName.equalsIgnoreCase("image")) {
                        int resId = c.getResources().getIdentifier("j_" + parser.getAttributeValue(0), "drawable", c.getPackageName());
                        gallery.add(new Image(parser.getAttributeValue(0), resId, parser.nextText()));
                    }
                    break;
            }
            eventType = parser.next();
        }

        return gallery;
    }

    public static ArrayList<Location> parseLocationXML(Context c) throws XmlPullParserException, IOException {

        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();

        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream in_s = c.getAssets().open("locations.xml");
        parser.setInput(in_s, null);

        ArrayList<Location> locations = new ArrayList<>();
        int eventType = parser.getEventType();
        Location currentLocation = null;
        String tagName;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    tagName = parser.getName();

                    if (tagName.equalsIgnoreCase("entry")) {
                        currentLocation = new Location(parser.getAttributeValue(1), parser.getAttributeValue(0));
                    } else if (currentLocation != null) {
                        if (tagName.equalsIgnoreCase("description")) {
                            currentLocation.setDescription(parser.nextText());
                        } else if (tagName.equalsIgnoreCase("tel")) {
                            currentLocation.setTelephoneNo(parser.nextText());
                        } else if (tagName.equalsIgnoreCase("url")) {
                            currentLocation.setUrl(parser.nextText());
                        } else if (tagName.equalsIgnoreCase("address")) {
                            currentLocation.setAddress(parser.nextText());
                        } else if (tagName.equalsIgnoreCase("latitude")) {
                            currentLocation.setLatitude(parser.nextText());
                        } else if (tagName.equalsIgnoreCase("longitude")) {
                            currentLocation.setLongitude(parser.nextText());
                        } else if (tagName.equalsIgnoreCase("image")) {
                            currentLocation.setImgResouce(c.getResources().getIdentifier("j_" + parser.nextText(), "drawable", c.getPackageName()));
                        } else if (tagName.equalsIgnoreCase("item")) {
                            currentLocation.addImgToGallery(Integer.parseInt(parser.nextText()));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    tagName = parser.getName();
                    if (tagName.equalsIgnoreCase("entry") && currentLocation != null) {
                        locations.add(currentLocation);
                        currentLocation = null;
                    }
            }
            eventType = parser.next();
        }
        return locations;
    }
}
