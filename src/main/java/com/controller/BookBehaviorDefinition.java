package com.controller;

import com.Exceptions.AddressBookExceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface BookBehaviorDefinition
{
   boolean newAddressBook(String bookName) throws AddressBookExceptions;

   boolean addData(String bookName, String fName, String lName, String Add, String City, String State, int Zip, int Phone) throws AddressBookExceptions, FileNotFoundException;

   boolean openFile(String bookName) throws FileNotFoundException, AddressBookExceptions;

   boolean saveAs(String oldName, String newName);

   void ReadFromFile();

   String editData(String bookName, int mobileNumber, String fieldName, String newValue) throws IOException, AddressBookExceptions;

   boolean deleteData(String bookName, int mobileNumber) throws AddressBookExceptions, IOException;

   boolean sortData(String bookName,String fieldName) throws InvocationTargetException, IllegalAccessException, AddressBookExceptions, IOException;
}
