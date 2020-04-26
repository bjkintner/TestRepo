/* 
 * Issues - I can't account for case when using io.studentExists when checking map keys
 */
package com.sg.exercisestudentquizscores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author bryantkintner
 */
public class App {
    public static void main(String[] args) {
        
        ///Default students are Adam, Bill, Charlie, Dave
        //Instantiate userIO
        UserIO io = new UserIOimpl();
        
        //Declare HashMap and ArrayList to be used
        HashMap<String, ArrayList> teacherKey = new HashMap<>();

        
        //Load default students
        ArrayList<Integer> tuaScores = new ArrayList<>();
        tuaScores.add(90);
        tuaScores.add(100);
        tuaScores.add(90);
        teacherKey.put("Tua", tuaScores);
        
        ArrayList<Integer> smittyScores = new ArrayList<>();
        smittyScores.add(80);
        smittyScores.add(60);
        smittyScores.add(80);
        teacherKey.put("Smitty", smittyScores);
        
        ArrayList<Integer> jerryScores = new ArrayList<>();
        jerryScores.add(100);
        jerryScores.add(100);
        jerryScores.add(100);
        teacherKey.put("Jerry", jerryScores);
        
        ArrayList<Integer> henryScores = new ArrayList<>();
        henryScores.add(20);
        henryScores.add(60);
        henryScores.add(40);
        teacherKey.put("Henry", henryScores);
        
        //Declare collection of map values
        Collection<ArrayList> allScores = teacherKey.values();
        
        boolean repeat = true;
        
        while (repeat) {
        io.print("What would you like to do?");
        io.print("1. Add a new student");
        io.print("2. Remove an existing student");
        io.print("3. View all students");
        io.print("4. View student scores");
        io.print("5. View class scores");
        io.print("6. View student average");
        io.print("7. View class average");
        io.print("8. View highest scores");
        io.print("9. View lowest scores");
        io.print("10. Exit");
        
        int menuChoice = io.readInt("Please make your selection now", 1, 10);
        
        
        switch (menuChoice) {
                
                case 1: //Add a new student
                    String newStudentName = io.readString("Enter the student's name");
                    if (!io.studentExists(teacherKey, newStudentName)) {
                    int score1 = io.readInt("What is their first score? (1-10)", 1, 10);
                    score1 = score1 * 10;
                    int score2 = io.readInt("What is their second score? (1-10)", 1, 10);
                    score2 = score2 * 10;
                    int score3 = io.readInt("What is their third score? (1-10)", 1, 10);
                    score3 = score3 * 10;
                    ArrayList<Integer> newScores = new ArrayList<>();
                    newScores.add(score1);
                    newScores.add(score2);
                    newScores.add(score3);
                    teacherKey.put(newStudentName, newScores);
                    io.print(newStudentName + " has been added");
                    } else {
                        io.print("Sorry, " + newStudentName + " is already a student");
                    }
                    break;
                
                case 2: //Remove an existing student
                    boolean studentExists = false;
                    while (!studentExists) {
                        String toDelete = io.readString("Which student would you "
                                + "like to delete? Type 'Cancel' to return to the main menu.");
                        if (teacherKey.containsKey(toDelete)) {
                            studentExists = true;
                            teacherKey.remove(toDelete);
                            io.print(toDelete + " has been removed");
                        } else if (toDelete.equalsIgnoreCase("cancel")) {
                            studentExists = true;
                        } else {
                            io.print(toDelete + " is not a current student.");
                        }
                    }    
                    break;
                
                case 3: //View all students
                    Set<String> keys = teacherKey.keySet();
                    System.out.println("--- Students ---");
                        for (String k : keys) {
                        io.print(k);
                        }
                    break;
                    
                case 4: //View student scores
                    studentExists = false;
                    while (!studentExists) {
                    String viewStudent = io.readString("Whose scores would you "
                    + "like to see? Type 'Cancel' to return to the main menu.");
                    
                    if (teacherKey.containsKey(viewStudent)) {
                        ArrayList currentStu = teacherKey.get(viewStudent);
                        io.printArray(currentStu);
                        studentExists = true;
                    } else if (viewStudent.equalsIgnoreCase("cancel")) {
                        studentExists = true;
                    } else {
                        io.print(viewStudent + " is not a current student.");
                    }
                    }
                    break;
                    
                case 5: //View class scores
                    for (ArrayList list : allScores) {
                        io.printArray(list);
                        }
                    break;
                    
                case 6: //View student average
                    studentExists = false;
                    while (!studentExists) {
                    String viewStudent = io.readString("Whose average would you like to "
                    + "see? Type 'Cancel' to return to the main menu.");
                        if (io.studentExists(teacherKey, viewStudent)){
                            studentExists = true;
                            ArrayList<Integer> toAverage = teacherKey.get(viewStudent);
                            int sum = 0;
                                for (int currentScore : toAverage) {
                                sum += currentScore;
                                }
                        double average = sum / toAverage.size();
                        io.print("The average is " + average);
                        } else if (viewStudent.equalsIgnoreCase("cancel")){
                            studentExists = true;
                        } else {
                            io.print(viewStudent + " is not a current student");
                        }
                    }
                    break;
                    
                case 7: //View class average
                    Collection<ArrayList> classAverage = teacherKey.values();
                    int sum = 0;
                    for (ArrayList<Integer> currentScores : classAverage) {
                        for (int score : currentScores) {
                            sum += score;
                        }
                    }
                    int average = sum / (classAverage.size() * 3);
                    io.print("The class average is " + average);
                    
                    
                    break;
                    
                case 8: //View highest scores
                    int max = 0;
                    ArrayList<String> smartyPants = new ArrayList<String>();
                    for (String currentStudent : teacherKey.keySet()) {
                        ArrayList<Integer> currentStudentScores = teacherKey.get(currentStudent);
                        for (int currentScore : currentStudentScores) {
                            if (currentScore > max) {
                                max = currentScore;
                                smartyPants.clear();
                                smartyPants.add(currentStudent);
                            } else if (currentScore == max) {
                                if (!smartyPants.contains(currentStudent)) {
                                smartyPants.add(currentStudent);
                                }
                            }
                        }
                    }
                    io.print("--- Highest scores ---");
                    for (String highStudent : smartyPants) {
                        io.print(highStudent + " scored " + max);
                    }      
                    
                    break;
                case 9: //View lowest scores  
                    int min = 1000; //How can I set this to i.e. the first number in the target range?
                    ArrayList<String>lowStudents = new ArrayList<String>();
                    for (String currentStudent : teacherKey.keySet()) {
                        ArrayList<Integer> currentStudentScores = teacherKey.get(currentStudent);
                        for (int currentScore : currentStudentScores) {
                            if (currentScore < min) {
                                min = currentScore;
                                lowStudents.clear();
                                lowStudents.add(currentStudent);
                            } else if (currentScore == min) {
                                if (!lowStudents.contains(currentStudent)) {
                                lowStudents.add(currentStudent);
                                }
                            }
                        }
                    }
                    io.print("--- Lowest scores ---");
                    for (String lowStudent : lowStudents) {
                        io.print(lowStudent + " scored " + min);
                    }      
                    break;
                    
                case 10: //Exit
                    repeat = false;
                    break;
        }        
    }
    }
    
}
