package _8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

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

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public void addSchedule(Date date) {
        this.schedule.add(date);
    }

    public void setSchedule(List<Date> schedule) {
        this.schedule = schedule;
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

        int numberOfLecturers = inDataStream.readInt();
        for (int i = 0; i < numberOfLecturers; i++) {
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

    public static void saveText(String filename, Lecture data) throws IOException {
        Path file = Paths.get(filename);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\tnumber: " + data.getNumber() + ",\n");
        sb.append("\ttitle: " + data.getTitle() + ",\n");
        sb.append("\tshortTitle: " + data.getShortTitle() + ",\n");
        sb.append("\tsemester: " + data.getSemester() + ",\n");

        sb.append("\tlecturers: [\n");
        List<Lecturer> lecturers = data.getLecturers();
        StringJoiner sjLect = new StringJoiner(",\n");
        for (Lecturer l : lecturers) {
            sjLect.add("\t\t{\n\t\t firstname: " + l.getFirstName() + ",\n\t\t" + " lastname: " + l.getLastName() + "\n\t\t}");
        }
        sb.append(sjLect.toString()).append("\n\t],\n");

        sb.append("\tschedule: [\n");
        List<Date> schedule = data.getSchedule();
        StringJoiner sjSched = new StringJoiner(",\n");
        for (Date s : schedule) {
            List<String> topics = s.getTopics();
            StringJoiner sjTopics = new StringJoiner(",");
            for (String t : topics) {
                sjTopics.add("\n\t\t  " + t);
            }
            sjSched.add("\t\t{\n\t\t time: " + s.getYear() + "-" + s.getMonth() + "-" + s.getDay() + " " + s.getHour() + ", " + "\n\t\t " + "lectureHall: " + s.getLectureHall() + ",\n\t\t topics: [" + sjTopics + "\n\t\t ]\n\t\t}");
        }
        sb.append(sjSched.toString()).append("\n\t]");
        sb.append("\n}");

        lines.add(sb.toString());
        Files.write(file, lines);
    }

    public static Lecture loadText(String filename) throws IOException {
        Path file = Paths.get(filename);
        List<String> data = Files.readAllLines(file);

        String number = null;
        String title = null;
        String shortTitle = null;
        String semester = null;
        List<Lecturer> lecturers = new ArrayList<>();
        List<Date> schedule = new ArrayList<>();

        boolean lecturersFlag = false;
        boolean scheduleFlag = false;
        boolean topicsFlag = false;

        int numberOfLecturers = 0;
        int numberOfDates = 0;

        for (String l : data) {
            if (l.contains("number:")) {
                number = splitIdentifier(l, "number: ");
            } else if (l.contains("title:")) {
                title = splitIdentifier(l, "title: ");
            } else if (l.contains("shortTitle:")) {
                shortTitle = splitIdentifier(l, "shortTitle: ");
            } else if (l.contains("semester:")) {
                semester = splitIdentifier(l, "semester: ");
            } else if (l.contains("lecturers:")) {
                lecturersFlag = true;
            } else if (l.contains("schedule:")) {
                scheduleFlag = true;
            } else if (lecturersFlag) {
                if (l.contains("{")) {
                    numberOfLecturers++;
                    lecturers.add(new Lecturer());
                } else if (l.contains("firstname: ")) {
                    String firstnameLine = splitIdentifier(l, "firstname: ");
                    lecturers.get(numberOfLecturers-1).setFirstName(firstnameLine);
                } else if (l.contains("lastname: ")) {
                    String lastnameLine = splitIdentifier(l, "lastname: ");
                    lecturers.get(numberOfLecturers-1).setLastName(lastnameLine);
                } else if (l.contains("]")) {
                    lecturersFlag = false;
                }
            } else if (scheduleFlag) {
                if (l.contains("{")) {
                    numberOfDates++;
                    schedule.add(new Date());
                } else if (l.contains("time: ")) {
                    String timeLine = splitIdentifier(l, "time: ");
                    String[] time = timeLine.split("-");
                    schedule.get(numberOfDates-1).setYear(Integer.parseInt(time[0]));
                    schedule.get(numberOfDates-1).setMonth(Integer.parseInt(time[1]));
                    String[] time2 = time[2].split(" ");
                    schedule.get(numberOfDates-1).setDay(Integer.parseInt(time2[0]));
                    schedule.get(numberOfDates-1).setHour(Integer.parseInt(time2[1]));
                } else if (l.contains("lectureHall: ")) {
                    String lectureHallLine = splitIdentifier(l, "lectureHall: ");
                    schedule.get(numberOfDates-1).setLectureHall(lectureHallLine);
                } else if (l.contains("topics: ")) {
                    topicsFlag = true;
                } else if (l.contains("]") && topicsFlag) {
                    topicsFlag = false;
                } else if (topicsFlag) {
                    String[] topicLine = l.split(",");
                    schedule.get(numberOfDates-1).addTopics(topicLine[0].trim());
                } else if (l.contains("]")) {
                    scheduleFlag = false;
                }
            }
        }

        Lecture result = new Lecture(number, title, shortTitle, semester);
        result.setSchedule(schedule);
        result.setLecturers(lecturers);
        return result;
    }

    private static String splitIdentifier(String s, String regex) {
        String[] result = s.split(regex);
        result = result[1].split(",");
        return result[0];
    }
}
