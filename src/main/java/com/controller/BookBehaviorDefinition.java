package com.controller;

import com.Exceptions.AddressBookExceptions;

import java.io.FileNotFoundException;

public interface BookBehaviorDefinition
{
   public boolean newAddressBook(String bookName) throws AddressBookExceptions;
   public boolean addData(String bookName,String fName,String lName,String Add,String City,String State,int Zip,int Phone) throws AddressBookExceptions, FileNotFoundException;
   public boolean openFile(String bookName) throws FileNotFoundException, AddressBookExceptions;
   public void saveAs(String bookName);
   public void ReadFromFile();
}
