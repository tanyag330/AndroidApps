package com.example.tanya.novels.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Novel " + position, makeDetails(position));

    }

    //http://freenovelonline.com/280406/adventure.html&arubalp=302a74a0-9aca-4417-bd8b-40d7ff7260

    private static String makeDetails(int position) {
       StringBuilder builder = new StringBuilder();
        builder.append("On Stranger Tides : ").append(position);
            builder.append("\n Aboard the Vociferous Carmichael, puppeteer John Chandagnac is sailing toward Jamaica to claim his stolen birthright from an unscrupulous uncle," +
                    " when the vessel is captured . . . by pirates! Offered a choice by Captain Phil Davies to join their seafaring band or die, " +
                    "Chandagnac assumes the name John Shandy and a new life as a brigand. But more than swashbuckling sea battles and fabulous plunder await the novice buccaneer on the roiling Caribbean waters–for treachery and powerful vodun sorcery are coins of the realm in this dark new world." +
                    " And for the love of beautiful, magically imperiled Beth Hurwood, Shandy will set sail on even stranger tides, following the savage," +
                    " ghost-infested pirate king, Blackbeard, and a motley crew of the living and the dead to the cursed nightmare banks of the fabled Fountain of Youth.");
        return builder.toString();


    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
