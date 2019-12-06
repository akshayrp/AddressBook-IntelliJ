package com.controller;

import com.Exceptions.AddressBookExceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface BookBehaviorDefinition
{
   public boolean newAddressBook(String bookName) throws AddressBookExceptions;
   public boolean addData(String bookName,String fName,String lName,String Add,String City,String State,int Zip,int Phone) throws AddressBookExceptions, FileNotFoundException;
   public boolean openFile(String bookName) throws FileNotFoundException, AddressBookExceptions;
   public boolean saveAs(String oldName, String newName);
   public void ReadFromFile();
   String editData(String bookName,int mobileNumber, String fieldName,String newValue) throws IOException, AddressBookExceptions;
   boolean deleteData(String bookName,String mobileNumber);
   boolean sortData(String bookName,String sortBy);
}
