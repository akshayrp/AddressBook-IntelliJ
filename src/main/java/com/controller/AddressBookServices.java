package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.model.Person;

import java.io.File;
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
   public boolean newAddressBook(String bookName)
   {
      File fileName = new File(bookName + ".json");
      try
      {
         if (fileName.length() == 0)
         {
            throw new AddressBookExceptions
                  (addressBookExceptions.exceptionType.NULL_FILENAME, "FileName Cannot Be Empty");
         }

         if (fileName.createNewFile()) ;
         return true;
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (AddressBookExceptions bookExceptions)
      {
         System.out.println(bookExceptions.getMessage());
         return false;
      }
      return false;
   }

   @Override
   public void addData(File filename) throws IOException
   {

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
