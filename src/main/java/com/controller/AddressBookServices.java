package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.model.AddressBook;
import com.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookServices implements BookBehaviorDefinition
{

   ObjectFactory ObjectDependency = new ObjectFactory();
   List<Person> personList = new ArrayList<Person>();
   BufferedReader br = null;


   @Override
   public boolean newAddressBook(String bookName) throws AddressBookExceptions
   {
      File fileName = new File(bookName + ".json");
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
      File fileName = new File(bookName + ".json");
      if (fileName.exists())
      {
         if(fileName.length() !=0)
         {
            br = new BufferedReader(new FileReader(fileName));
            ObjectDependency.bookData = ObjectDependency.gson.fromJson(br, AddressBook.class);
            personList.addAll(ObjectDependency.bookData.getPersonsList());
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

         personList.add(ObjectDependency.personData);
         ObjectDependency.bookData.setPersonsList(personList);

         try
         {
            FileWriter writer = new FileWriter(fileName);
            writer.write(ObjectDependency.gson.toJson(ObjectDependency.bookData));
            writer.close();
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
      File fileName = new File(bookName + ".json");
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
            personList.addAll(ObjectDependency.bookData.getPersonsList());

            for (int i = 0; i < personList.size(); i++)
            {
               System.out.println(ObjectDependency.bookData.getPersonsList().get(i).getFirstName());
            }
            return true;
         }
      }
      return false;
   }

   @Override
   public void saveAs(String bookName)
   {

   }

   @Override
   public void ReadFromFile(String bookName)
   {

   }
}
