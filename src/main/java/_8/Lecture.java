package _8;

import _8.io.SonReader;
import _8.io.SonWriter;

import java.io.*;
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

    public Lecture() {}

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
            sjLect.add("\t\t{\n\t\t firstName: " + l.getFirstName() + ",\n\t\t" + " lastName: " + l.getLastName() + "\n\t\t}");
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
            sjSched.add("\t\t{\n\t\t time: " + s.getYear() + "-" + s.getMonth() + "-" + s.getDay() + " " + s.getHour() + ":00, " + "\n\t\t " + "lectureHall: " + s.getLectureHall() + ",\n\t\t topics: [" + sjTopics + "\n\t\t ]\n\t\t}");
        }
        sb.append(sjSched.toString()).append("\n\t]");
        sb.append("\n}");

        lines.add(sb.toString());
        Files.write(file, lines);
    }

		/*
		 * Bitte um Feedback, untere lösung (loadText2)
		 * macht weniger Annahmen.
		 *
		 * Zeilenumbrüche zwischen name-value Paaren.
		 * Whitespace weglassen macht probleme. Ist kompatibel mit
		 * dem outout von saveText.
		 * Nur eine Lecture pro Datei.
		 *
		 *
		 *
		 */
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
                } else if (l.contains("firstName: ")) {
                    String firstNameLine = splitIdentifier(l, "firstName: ");
                    lecturers.get(numberOfLecturers-1).setFirstName(firstNameLine);
                } else if (l.contains("lastName: ")) {
                    String lastNameLine = splitIdentifier(l, "lastName: ");
                    lecturers.get(numberOfLecturers-1).setLastName(lastNameLine);
                } else if (l.contains("]")) {
                    lecturersFlag = false;
                }
            } else if (scheduleFlag) {
                if (l.contains("{")) {
                    numberOfDates++;
                    schedule.add(new Date());
                } else if (l.contains("time: ")) {
                    String timeLine = splitIdentifier(l, "time: ");
					Date currentDate = schedule.get(numberOfDates - 1);
					applyDate(currentDate, timeLine);
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

	/*
	 * parse a date of the form 'YYYY-MM-DD h:00'
	 * changes scheduleEntry even if an exception is thrown.
	 */
	private static void applyDate(Date scheduleEntry, String date) {
		try {
			String[] split = date.split(" ", 2);
			String[] dateSplit = split[0].split("-");
			String[] timeSplit = split[1].split(":");
			scheduleEntry.setYear(Integer.parseInt(dateSplit[0]));
			scheduleEntry.setMonth(Integer.parseInt(dateSplit[1]));
			scheduleEntry.setDay(Integer.parseInt(dateSplit[2]));
			scheduleEntry.setHour(Integer.parseInt(timeSplit[0]));
		} catch(IndexOutOfBoundsException | NumberFormatException e) {
			throw new IllegalArgumentException("Malformed date: " + date);
		}
	}

    /*
     * SON - Simple/String Object Notation
     * (selbst ausgedacht, inspiriert durch JSON)
     *
     * SON hat 3 elementare Datentypen: String, Array und Object
     * Strings sind Zeichenketten die nicht weiter gekenzeichnet werden.
     * Sie sollten keine der Kontrollzeichen {[]},: enthalten.
     * Ein String endet mit einem : wenn es ein name ist oder mit
     * einem , wenn es ein value ist. Sie haben keinen
     * whitespace am Anfang und Ende. Zwischen den daten kann
     * beliebig viel (unregelmäßiger) whitespace, also Leerzeichen,
     * Tabulatoren und Zeilenumbrüche sein.
     *
     * Arrays sind Listen aus beliebigen SON Datenstrukturen,
     * getrennt durch , und eingeschlossen in []. Beispiel:
     * [1, 2, a, hello, [inner, array]]
     *
     * Objects enthalten eine Zuordnung von name zu value. Der name
     * ist dabei ein string, und value eine beliebige SON Datenstruktur.
     * Name und value sind durch : getrennt und name-value Paare
     * sind durch , getrennt. Ein Object ist in {} eingeschlossen. Beispiel:
     * {hello: world, a:[1, {foo:bar}]}
     *
     */

	public static void saveText2(String filename, Lecture data) throws IOException {
		Path file = Paths.get(filename);
		try(SonWriter out = new SonWriter(Files.newBufferedWriter(file))) {
			Lecture.saveSon(out, data);
		}
	}

	public static void saveSon(SonWriter out, Lecture lecture) throws IOException {
		out.beginObject();
		out.name("number").value(lecture.getNumber());
		out.name("title").value(lecture.getTitle());
		out.name("shortTitle").value(lecture.getShortTitle());
		out.name("semester").value(lecture.getSemester());

		out.name("lecturers").beginArray();
		for(Lecturer lecturer : lecture.getLecturers()) {
			out.beginObject();
			out.name("firstName").value(lecturer.getFirstName());
			out.name("lastName").value(lecturer.getLastName());
			out.endObject();
		}
		out.endArray();

		out.name("schedule").beginArray();
		for(Date date : lecture.getSchedule()) {
			out.beginObject();
			out.name("time").value(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " " + date.getHour() + ":00");
			out.name("lectureHall").value(date.getLectureHall());

			out.name("topics").beginArray();
			for(String topic : date.getTopics()) {
				out.value(topic);
			}
			out.endArray();

			out.endObject();
		}
		out.endArray();

		out.endObject();
	}

    public static Lecture loadText2(String filename) throws IOException {
        Path file = Paths.get(filename);
        try(SonReader in = new SonReader(Files.newBufferedReader(file))) {
            return loadSon(in);
        }
    }

    public static Lecture loadSon(SonReader in) throws IOException {
        in.beginObject();
        Lecture lecture = new Lecture();
        while(in.hasNext()) {
            switch(in.nextName()) {
                case "number":
                    lecture.setNumber(in.nextValue());
                    break;
                case "title":
                    lecture.setTitle(in.nextValue());
                    break;
                case "shortTitle":
                    lecture.setShortTitle(in.nextValue());
                    break;
                case "semester":
                    lecture.setSemester(in.nextValue());
                    break;
                case "lecturers":
                    in.beginArray();
                    while(in.hasNext()) {
                        in.beginObject();
                        Lecturer lecturer = new Lecturer();
                        while(in.hasNext()) {
                            switch(in.nextName()) {
                                case "firstName":
                                    lecturer.setFirstName(in.nextValue());
                                    break;
                                case "lastName":
                                    lecturer.setLastName(in.nextValue());
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        lecture.addLecturers(lecturer);
                    }
                    in.endArray();
                    break;
                case "schedule":
                    in.beginArray();
                    while(in.hasNext()) {
                        in.beginObject();
                        Date scheduleEntry = new Date();
                        while(in.hasNext()) {
                            switch(in.nextName()) {
                                case "time":
                                    applyDate(scheduleEntry, in.nextValue());
                                    break;
                                case "lectureHall":
                                    scheduleEntry.setLectureHall(in.nextValue());
                                    break;
                                case "topics":
                                    in.beginArray();
                                    while(in.hasNext()) {
                                        scheduleEntry.addTopics(in.nextValue());
                                    }
                                    in.endArray();
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        lecture.addSchedule(scheduleEntry);
                    }
                    in.endArray();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return lecture;
    }
}
