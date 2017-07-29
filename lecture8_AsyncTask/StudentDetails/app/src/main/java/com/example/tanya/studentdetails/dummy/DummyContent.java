package com.example.tanya.studentdetails.dummy;

import com.example.tanya.studentdetails.R;

import java.util.ArrayList;

public class DummyContent {

    public static class DummyItem {
        public final String id;
        public final int content;
        public final String details;

        public DummyItem(String id, int content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;

        }
    }
    // public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static ArrayList<DummyItem> getStudents() {
        ArrayList<DummyItem> students = new ArrayList<>(10);

        students.add(new DummyItem("MEENU", R.drawable.meenu, "9854825846"));
        students.add(new DummyItem("SHIVANI", R.drawable.shivani, " 9955594914" ));
        students.add(new DummyItem("SHREYA", R.drawable.shreya, "9999582510"));
        students.add(new DummyItem("AKSHAY",  R.drawable.akshay, "9994778562"));
        students.add(new DummyItem("PARUL", R.drawable.parul, "9998756715"));
        students.add(new DummyItem("PRIYA", R.drawable.priya, "9997845612"));
        students.add(new DummyItem("VANSHIKA",  R.drawable.vanshika, " 9999925867"));
        students.add(new DummyItem("KARAN", R.drawable.karan, "9999845278"));
        students.add(new DummyItem("AARTI", R.drawable.aarti, "9875684718"));
        students.add(new DummyItem("RAVEENA", R.drawable.raveena, "9785114759"));

        return students;
    }


}