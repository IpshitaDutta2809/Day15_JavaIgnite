class Classroom {
    private boolean attendanceStarted = false;

    public synchronized void waitForAttendance() {
        while (!attendanceStarted) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " : Student Waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()
                + " : Student Marked Present");
    }

    public synchronized void startAttendance() {
        attendanceStarted = true;

        System.out.println("Teacher Started Attendance");

        notifyAll();
    }
}

class TeacherThread extends Thread {
    Classroom classroom;

    TeacherThread(Classroom classroom) {
        this.classroom = classroom;
    }

    public void run() {
        classroom.startAttendance();
    }
}

class StudentThread extends Thread {
    Classroom classroom;

    StudentThread(Classroom classroom, String name) {
        super(name);
        this.classroom = classroom;
    }

    public void run() {
        classroom.waitForAttendance();
    }
}

public class Classroom_attendance_system {
    public static void main(String[] args) {

        Classroom classroom = new Classroom();

        StudentThread s1 = new StudentThread(classroom, "Student-1");
        StudentThread s2 = new StudentThread(classroom, "Student-2");
        StudentThread s3 = new StudentThread(classroom, "Student-3");

        TeacherThread teacher = new TeacherThread(classroom);

        s1.start();
        s2.start();
        s3.start();

        try {
            Thread.sleep(3000); // wait for a few seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teacher.start();
    }
}
