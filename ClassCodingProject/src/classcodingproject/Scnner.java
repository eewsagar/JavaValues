/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classcodingproject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Sagar Das
 */
public class Scnner {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter outputfile = new PrintWriter("test.txt");
//replace your System.out.print("your output");
    outputfile.print("your output");
    
        Scanner s  = new Scanner(System.in);
        System.out.println("Please print the value");
        int i  =  s.nextInt();
        System.out.println("value u have enterd" +i);
        outputfile.append("\nvalue u have enterd" +i);
        outputfile.close();
        System.exit(0);
       
        
    }
    
}
