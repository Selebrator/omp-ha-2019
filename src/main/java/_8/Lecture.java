package _8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Lecture {

    private String number = "";
    private String title = "";
    private String shortTitle = "";
    private String semester = "";
    private List<Lecturer> lecturers = new ArrayList<>();
    private List<Date> schedule = new ArrayList<>();

    public Lecture(String number, String title, String shortTitle, String semester) {
        super();
        this.number = number;
        this.title = title;
        this.shortTitle = shortTitle;
        this.semester = semester;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public List<Date> getSchedule() {
        return schedule;
    }

    public void addLecturers(Lecturer lecturer) {
        this.lecturers.add(lecturer);
    }

    public void addSchedule(Date date) {
        this.schedule.add(date);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(number);
        result.append(": ");
        result.append(title);
        result.append(" (");
        result.append(shortTitle);
        result.append("), ");
        result.append(semester);
        result.append("\n\t");
        for (int i = 0; i < lecturers.size(); i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(lecturers.get(i));
        }
        for (Date date : schedule) {
            result.append("\n\t- ");
            result.append(date);
        }
        result.append("\n");
        return result.toString();
    }

    public static Lecture load(String filename) throws IOException {
        Lecture result = null;
        InputStream in = null;
        try {
            in = new FileInputStream(filename);
            result = load(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

    public static Lecture load(InputStream in) throws IOException {
        DataInputStream inDataStream = new DataInputStream(in);
        Lecture result = new Lecture(inDataStream.readUTF(), inDataStream.readUTF(), inDataStream.readUTF(), inDataStream.readUTF());

        int numberOfLectuerers = inDataStream.readInt();
        for (int i = 0; i < numberOfLectuerers; i++) {
            Lecturer lecturer = new Lecturer();
            lecturer.load(inDataStream);
            result.addLecturers(lecturer);
        }

        int numberOfDates = inDataStream.readInt();
        for (int k = 0; k < numberOfDates; k++) {
            Date date = new Date();
            date.load(inDataStream);
            result.addSchedule(date);
        }
        return result;
    }

    //public static void saveText(String filename, Lecture data) throws IOException {
    //TODO: implement this (task 2)
    //}

    //public static Lecture loadText(String filename) throws IOException {
    //TODO: implement this (task 2)
    //}
}
