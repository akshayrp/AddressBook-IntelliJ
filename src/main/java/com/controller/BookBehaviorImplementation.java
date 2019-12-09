package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.model.AddressBook;
import com.model.Person;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;


public class BookBehaviorImplementation implements BookBehaviorDefinition
{

   ObjectFactory objectDependency = new ObjectFactory();
   BufferedReader br = null;
   private boolean addData;

   @Override
   public boolean newAddressBook(String bookName)
   {
      File fileName = new File(objectDependency.filePath + "/" + bookName + ".json");
      try
      {
         if (bookName == null || bookName.length() == 0)
         {
            throw new AddressBookExceptions
                  (objectDependency.addressBookExceptions.exceptionType.NULL_FILENAME, "File Name cannot be null");
         }

         if (fileName.createNewFile())
            return true;
         else
            throw new AddressBookExceptions
                  (objectDependency.addressBookExceptions.exceptionType.IO_EXCEPTION, "File Cannot be created");
      }
      catch (AddressBookExceptions bookExceptions)
      {
         System.out.println(bookExceptions.getMessage());
         return false;
      }
      catch (IOException e)
      {
         System.out.println(objectDependency.addressBookExceptions.getMessage());
         return false;
      }
   }

   @Override
   public boolean addData(String bookName, String fName, String lName, String Add,
                          String City, String State, int Zip, int Phone) throws AddressBookExceptions
   {
      File fileName = new File(objectDependency.filePath + "/" + bookName + ".json");
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
                     (objectDependency.addressBookExceptions.exceptionType.FILE_NOT_FOUND, "File Not Present");
            }
            objectDependency.bookData = objectDependency.gson.fromJson(br, AddressBook.class);
            objectDependency.personList.addAll(objectDependency.bookData.getPersonsList());
         }

         objectDependency.personData.setFirstName(fName);
         objectDependency.personData.setLastName(lName);
         objectDependency.personData.setAddress(Add);
         objectDependency.personData.setCity(City);
         objectDependency.personData.setState(State);
         objectDependency.personData.setZip(Zip);
         objectDependency.personData.setPhoneNumber(Phone);

         objectDependency.personList.add(objectDependency.personData);
         objectDependency.bookData.setPersonsList(objectDependency.personList);
         writeIntoJson(fileName);
         return true;
      }
      if (fileName.length() > 0)
         return true;
      return false;
   }

   @Override
   public boolean openFile(String bookName) throws AddressBookExceptions
   {
      File fileName = new File(objectDependency.filePath + "/" + bookName + ".json");
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
                     (objectDependency.addressBookExceptions.exceptionType.FILE_NOT_FOUND, "File not Found");
            } finally
            {
               System.out.println(objectDependency.addressBookExceptions.getMessage());
            }
            objectDependency.bookData = objectDependency.gson.fromJson(br, AddressBook.class);
            objectDependency.personList.addAll(objectDependency.bookData.getPersonsList());
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
         File fileName = new File(objectDependency.filePath + "/" + oldName + ".json");
         if (fileName.exists())
         {
            File renameFile = new File(objectDependency.filePath + "/" + newName + ".json");
            fileName.renameTo(renameFile);
            return true;
         }
      }
      return false;
   }

   @Override
   public void ReadFromFile()
   {
      for (int i = 0; i < objectDependency.personList.size(); i++)
      {
         System.out.println("FirstName: " + objectDependency.bookData.getPersonsList().get(i).getFirstName());
         System.out.println("LastName: " + objectDependency.bookData.getPersonsList().get(i).getLastName());
         System.out.println("Address: " + objectDependency.bookData.getPersonsList().get(i).getAddress());
         System.out.println("City: " + objectDependency.bookData.getPersonsList().get(i).getCity());
         System.out.println("State: " + objectDependency.bookData.getPersonsList().get(i).getState());
         System.out.println("Zip: " + objectDependency.bookData.getPersonsList().get(i).getZip());
         System.out.println("PhoneNumber: " + objectDependency.bookData.getPersonsList().get(i).getPhoneNumber());
         System.out.println("-----------------------");
      }
   }

   @Override
   public String editData(String bookName, int mobileNumber, String fieldName, String newValue)
         throws AddressBookExceptions
   {
      openFile(bookName);
      File fileName = new File(objectDependency.filePath + "/" + bookName + ".json");
      for (int i = 0; i < objectDependency.personList.size(); i++)
      {
         if (mobileNumber == objectDependency.personList.get(i).getPhoneNumber())
         {
            switch (fieldName)
            {
               case "FirstName":
                  objectDependency.bookData.getPersonsList().get(i).setFirstName(newValue);
                  objectDependency.personList.add(objectDependency.personData);
                  objectDependency.bookData.setPersonsList(objectDependency.personList);
                  writeIntoJson(fileName);
                  return objectDependency.bookData.getPersonsList().get(i).getFirstName();
               case "LastName":
                  objectDependency.bookData.getPersonsList().get(i).setLastName(newValue);
                  objectDependency.personList.add(objectDependency.personData);
                  objectDependency.bookData.setPersonsList(objectDependency.personList);
                  writeIntoJson(fileName);
                  return objectDependency.bookData.getPersonsList().get(i).getLastName();
               case "City":
                  objectDependency.bookData.getPersonsList().get(i).setCity(newValue);
                  objectDependency.personList.add(objectDependency.personData);
                  objectDependency.bookData.setPersonsList(objectDependency.personList);
                  writeIntoJson(fileName);
                  return objectDependency.bookData.getPersonsList().get(i).getCity();
               case "State":
                  objectDependency.bookData.getPersonsList().get(i).setState(newValue);
                  objectDependency.personList.add(objectDependency.personData);
                  objectDependency.bookData.setPersonsList(objectDependency.personList);
                  writeIntoJson(fileName);
                  return objectDependency.bookData.getPersonsList().get(i).getState();
               case "Zip":
                  objectDependency.bookData.getPersonsList().get(i).setZip(Integer.parseInt(newValue));
                  objectDependency.personList.add(objectDependency.personData);
                  objectDependency.bookData.setPersonsList(objectDependency.personList);
                  writeIntoJson(fileName);
                  return String.valueOf(objectDependency.bookData.getPersonsList().get(i).getZip());
               case "MobileNumber":
                  objectDependency.bookData.getPersonsList().get(i).setPhoneNumber(Integer.parseInt(newValue));
                  objectDependency.personList.add(objectDependency.personData);
                  objectDependency.bookData.setPersonsList(objectDependency.personList);
                  writeIntoJson(fileName);
                  return String.valueOf(objectDependency.bookData.getPersonsList().get(i).getPhoneNumber());
            }
         }
      }
      return "";
   }

   @Override
   public boolean deleteData(String bookName, int mobileNumber) throws AddressBookExceptions
   {
      openFile(bookName);
      File fileName = new File(objectDependency.filePath + "/" + bookName + ".json");
      for (int i = 0; i < objectDependency.personList.size(); i++)
      {
         if (mobileNumber == objectDependency.personList.get(i).getPhoneNumber())
         {
            objectDependency.bookData.getPersonsList().remove(i);
            FileWriter writer = null;
            try
            {
               writer = new FileWriter(fileName);
               writer.write(objectDependency.gson.toJson(objectDependency.bookData));
            }
            catch (IOException e)
            {
               System.out.println(objectDependency.addressBookExceptions.getMessage());
            }

            return true;
         }
      }
      return false;
   }

   @Override
   public boolean sortData(String bookName, String fieldName) throws AddressBookExceptions
   {
      openFile(bookName);
      File fileName = new File(objectDependency.filePath + "/" + bookName + ".json");
      Collections.sort(objectDependency.personList, new Comparator<Person>()
      {
         @Override
         public int compare(Person o1, Person o2)
         {
            Field fieldType = null;
            try
            {

               fieldType = Person.class.getDeclaredField(fieldName);

               fieldType.setAccessible(true);
               Comparable stateCensusFieldValue1 = null;
               stateCensusFieldValue1 = (Comparable) fieldType.get(o1);
               Comparable stateCensusFieldValue2 = null;
               stateCensusFieldValue2 = (Comparable) fieldType.get(o2);
               stateCensusFieldValue1.compareTo(stateCensusFieldValue2);
            }
            catch (SecurityException e)
            {
               e.printStackTrace();
            }
            catch (IllegalArgumentException e)
            {
               e.printStackTrace();
            }
            catch (NoSuchFieldException e)
            {
               e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
               e.printStackTrace();
            }

            return 0;
         }
      });
      objectDependency.bookData.setPersonsList(objectDependency.personList);
      writeIntoJson(fileName);
      return true;

   }

   public void writeIntoJson(File fileName) throws AddressBookExceptions
   {
      FileWriter writer = null;
      try
      {
         writer = new FileWriter(fileName);
         writer.write(objectDependency.gson.toJson(objectDependency.bookData));
         writer.close();
      }
      catch (IOException e)
      {
         throw new AddressBookExceptions
               (objectDependency.addressBookExceptions.exceptionType.IO_EXCEPTION, "File Cannot be created");
      }

   }
}
