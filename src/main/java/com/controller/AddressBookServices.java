package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.model.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookServices implements BookBehaviorDefinition
{
   AddressBookExceptions addressBookExceptions = new AddressBookExceptions();
   ObjectFactory ObjectDependency = new ObjectFactory();
   Scanner input = new Scanner(System.in);
   List<Person> personList = new ArrayList<Person>();

   @Override
   public boolean newAddressBook(String bookName) throws AddressBookExceptions
   {
      File fileName = new File(bookName + ".json");
      try
      {
         if (fileName.length() == 0)
         {
            throw new AddressBookExceptions
                  (addressBookExceptions.exceptionType.NULL_FILENAME, "FileName Cannot Be Empty");
         }

         if(fileName.createNewFile())
            return true;
         else
            throw new AddressBookExceptions
                  (addressBookExceptions.exceptionType.IO_EXCEPTION, "File Cannot be created");
      }
      catch (AddressBookExceptions bookExceptions)
      {
         System.out.println(bookExceptions.getMessage());
         return false;
      }
      catch (IOException e)
      {
         System.out.println(addressBookExceptions.getMessage());
      }
      return false;
   }

   @Override
   public boolean addData(File filename) throws AddressBookExceptions
   {
      System.out.println("Enter Data");
      System.out.println("Enter FirstName");
      ObjectDependency.personData.setFirstName(input.next());
      System.out.println("Enter LastName");
      ObjectDependency.personData.setLastName(input.next());
      System.out.println("Enter Address");
      ObjectDependency.personData.setAddress(input.next());
      System.out.println("Enter City");
      ObjectDependency.personData.setCity(input.next());
      System.out.println("Enter State");
      ObjectDependency.personData.setState(input.next());
      System.out.println("Enter Zip Code");
      ObjectDependency.personData.setZip(input.nextInt());
      System.out.println("Enter Phone Number");
      ObjectDependency.personData.setZip(input.nextInt());

      personList.add(ObjectDependency.personData);
      ObjectDependency.bookData.setPersonsList(personList);

      try
      {
         FileWriter writer = new FileWriter(filename);
         writer.write(ObjectDependency.gson.toJson(ObjectDependency.bookData));
         writer.close();
         return true;
      }
      catch (IOException e)
      {
         throw new AddressBookExceptions
               (addressBookExceptions.exceptionType.IO_EXCEPTION, "File Cannot be created");
      }

   }

   @Override
   public void openFile(File FileName)
   {

   }

   @Override
   public void saveAs(File FileName)
   {

   }

   @Override
   public void ReadFromFile(File FileName)
   {

   }
}
