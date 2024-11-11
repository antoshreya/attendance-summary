package com.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static MongoClient mongoClient;
    private static MongoCollection<Document> collection;

   
    public static void connectToMongoDB() {
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // MongoDB URI (local instance)
        collection = mongoClient.getDatabase("attendance_db").getCollection("attendance");
    }

    
    public static void recordAttendance(String name,String studentId, String date, String status) {
        Document record = new Document("studentId","student_name", studentId,student_name)_
                .append("date", date)
                .append("status", status);
        collection.insertOne(record);
        System.out.println("Attendance recorded for student ID: " + studentId);
    }

    
    public static List<Document> getAttendanceByDate(String date) {
        return collection.find(Filters.eq("date", date)).into(new ArrayList<>());
    }

    
    public static void testCase1() {
        System.out.println("Running Test Case 1: Record Attendance for a student...");

        
        recordAttendance("Ava",101", "2024-11-11", "Present");
        recordAttendance("ponnu",102", "2024-11-11", "Absent");
        recordAttendance("dharshini",103", "2024-11-11", "Present");

        
        List<Document> records = getAttendanceByDate("2024-11-11");

        boolean testPassed = false;
        for (Document record : records) {
            if (record.getString("student_name")record.getString("student_name")("studentId").equals("101") && record.getString("status").equals("Present")) {
                testPassed = true;
            }
        }

        if (testPassed) {
            System.out.println("Test Case 1 Passed: Attendance was successfully recorded and saved.");
        } else {
            System.out.println("Test Case 1 Failed: Attendance not found or incorrect.");
        }
    }

    
    public static void testCase2() {
        System.out.println("Running Test Case 2: Retrieve Attendance for a specific date...");

        
        List<Document> records = getAttendanceByDate("2024-11-11");

       
        boolean testPassed = records.size() == 3;

        if (testPassed) {
            System.out.println("Test Case 2 Passed: Retrieved correct number of records.");
            for (Document record : records) {
                System.out.println("Student ID: " + record.getString("studentId") +,"Studentname :"+record.getString("studnet_name")+, Status: " + record.getString("status"));
            }
        } else {
            System.out.println("Test Case 2 Failed: Incorrect number of records or data.");
        }
    }

    
    public static void main(String[] args) {
        
        connectToMongoDB();

        
        testCase1();

        
        testCase2();

        mongoClient.close();
    }
}