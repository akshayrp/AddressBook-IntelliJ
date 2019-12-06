package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.model.AddressBook;
import com.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookBehaviorImplementation implements BookBehaviorDefinition
{

   ObjectFactory ObjectDependency = new ObjectFactory();
   BufferedReader br = null;

   @Override
   public boolean newAddressBook(String bookName)
   {
      File fileName = new File(ObjectDependency.filePath+"/"+bookName + ".json");
      try
      {
         if (bookName == null || bookName.length() == 0)
         {
            throw new AddressBookExceptions
                  (ObjectDependency.addressBookExceptions.exceptionType.NULL_FILENAME, "File Name cannot be null");
         }

         if (fileName.createNewFile())
            return true;
         else
            throw new AddressBookExceptions
                  (ObjectDependency.addressBookExceptions.exceptionType.IO_EXCEPTION, "File Cannot be created");
      }
      catch (AddressBookExceptions bookExceptions)
      {
         System.out.println(bookExceptions.getMessage());
         return false;
      }
      catch (IOException e)
      {
         System.out.println(ObjectDependency.addressBookExceptions.getMessage());
         return false;
      }
   }

   @Override
   public boolean addData(String bookName, String fName, String lName, String Add,
                          String City, String State, int Zip, int Phone) throws AddressBookExceptions, FileNotFoundException
   {
      File fileName = new File(ObjectDependency.filePath+"/"+bookName + ".json");
      if (fileName.exists())
      {
         if (fileName.length() != 0)
         {
            br = new BufferedReader(new FileReader(fileName));
            ObjectDependency.bookData = ObjectDependency.gson.fromJson(br, AddressBook.class);
            ObjectDependency.personList.addAll(ObjectDependency.bookData.getPersonsList());
         }
/*         System.out.println("Enter Data");
         System.out.println("Enter FirstName");
         ObjectDependency.personData.setFirstName(input.nextLine());
         System.out.println("Enter LastName");
         ObjectDependency.personData.setLastName(input.nextLine());
         System.out.println("Enter Address");
         ObjectDependency.personData.setAddress(input.nextLine());
         System.out.println("Enter City");
         ObjectDependency.personData.setCity(input.nextLine());
         System.out.println("Enter State");
         ObjectDependency.personData.setState(input.nextLine());
         System.out.println("Enter Zip Code");
         ObjectDependency.personData.setZip(input.nextInt());
         System.out.println("Enter Phone Number");
         ObjectDependency.personData.setZip(input.nextInt());*/


         ObjectDependency.personData.setFirstName(fName);
         ObjectDependency.personData.setLastName(lName);
         ObjectDependency.personData.setAddress(Add);
         ObjectDependency.personData.setCity(City);
         ObjectDependency.personData.setState(State);
         ObjectDependency.personData.setZip(Zip);
         ObjectDependency.personData.setPhoneNumber(Phone);



         try
         {
            writeIntoJson(fileName);
            return true;
         }
         catch (IOException e)
         {
            throw new AddressBookExceptions
                  (ObjectDependency.addressBookExceptions.exceptionType.IO_EXCEPTION, "File Cannot be created");
         }
      }
      if (fileName.length() > 0)
         return true;
      return false;
   }

   @Override
   public boolean openFile(String bookName) throws AddressBookExceptions
   {
      File fileName = new File(ObjectDependency.filePath+"/"+bookName + ".json");
      if (fileName.exists())
      {
         if (fileName.length() != 0)
         {

            try
            {
               br = new BufferedReader(new FileReader(fileName));
            }
            catch (FileNotFoundException e)
            {
               throw new AddressBookExceptions
                     (ObjectDependency.addressBookExceptions.exceptionType.FILE_NOT_FOUND, "File not Found");
            }
            ObjectDependency.bookData = ObjectDependency.gson.fromJson(br, AddressBook.class);
            ObjectDependency.personList.addAll(ObjectDependency.bookData.getPersonsList());
            ReadFromFile();

            return true;
         }
      }
      return false;
   }

   @Override
   public boolean saveAs(String oldName, String newName)
   {
      if (oldName.length() > 0 && newName.length() > 0)
      {
         File fileName = new File(ObjectDependency.filePath+"/"+oldName + ".json");
         if (fileName.exists())
         {
            File renameFile = new File(ObjectDependency.filePath+"/"+newName + ".json");
            fileName.renameTo(renameFile);
            return true;
         }
      }
      return false;
   }

   @Override
   public void ReadFromFile()
   {
      for (int i = 0; i < ObjectDependency.personList.size(); i++)
      {
         System.out.println("FirstName: " + ObjectDependency.bookData.getPersonsList().get(i).getFirstName());
         System.out.println("LastName: " + ObjectDependency.bookData.getPersonsList().get(i).getLastName());
         System.out.println("Address: " + ObjectDependency.bookData.getPersonsList().get(i).getAddress());
         System.out.println("City: " + ObjectDependency.bookData.getPersonsList().get(i).getCity());
         System.out.println("State: " + ObjectDependency.bookData.getPersonsList().get(i).getState());
         System.out.println("Zip: " + ObjectDependency.bookData.getPersonsList().get(i).getZip());
         System.out.println("PhoneNumber: " + ObjectDependency.bookData.getPersonsList().get(i).getPhoneNumber());
         System.out.println("-----------------------");
      }
   }

   @Override
   public String editData(String bookName, int mobileNumber, String fieldName, String newValue) throws AddressBookExceptions, IOException
   {
      openFile(bookName);
      File fileName = new File(ObjectDependency.filePath+"/"+bookName + ".json");
      for (int i = 0; i < ObjectDependency.personList.size(); i++)
      {
         if (mobileNumber == ObjectDependency.personList.get(i).getPhoneNumber())
         {
            switch (fieldName)
            {
               case "FirstName":
                  ObjectDependency.bookData.getPersonsList().get(i).setFirstName(newValue);
                  writeIntoJson(fileName);
                  return ObjectDependency.bookData.getPersonsList().get(i).getFirstName();
               case "LastName":
                  ObjectDependency.bookData.getPersonsList().get(i).setLastName(newValue);
                  writeIntoJson(fileName);
                  return ObjectDependency.bookData.getPersonsList().get(i).getLastName();
               case "City":
                  ObjectDependency.bookData.getPersonsList().get(i).setCity(newValue);
                  writeIntoJson(fileName);
                  return ObjectDependency.bookData.getPersonsList().get(i).getCity();
               case "State":
                  ObjectDependency.bookData.getPersonsList().get(i).setState(newValue);
                  writeIntoJson(fileName);
                  return ObjectDependency.bookData.getPersonsList().get(i).getState();
               case "Zip":
                  ObjectDependency.bookData.getPersonsList().get(i).setZip(Integer.parseInt(newValue));
                  writeIntoJson(fileName);
                  return String.valueOf(ObjectDependency.bookData.getPersonsList().get(i).getZip());
               case "MobileNumber":
                  ObjectDependency.bookData.getPersonsList().get(i).setPhoneNumber(Integer.parseInt(newValue));
                  writeIntoJson(fileName);
                  return String.valueOf(ObjectDependency.bookData.getPersonsList().get(i).getPhoneNumber());
            }
         }
      }
      return "";
   }

   @Override
   public boolean deleteData(String bookName, String mobileNumber)
   {
      return false;
   }

   @Override
   public boolean sortData(String bookName, String sortBy)
   {
      return false;
   }

   public void writeIntoJson(File fileName) throws IOException
   {
      ObjectDependency.personList.add(ObjectDependency.personData);
      ObjectDependency.bookData.setPersonsList(ObjectDependency.personList);
      FileWriter writer = new FileWriter(fileName);
      writer.write(ObjectDependency.gson.toJson(ObjectDependency.bookData));
      writer.close();
   }
}
