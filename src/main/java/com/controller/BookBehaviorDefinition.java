package com.controller;

import com.Exceptions.AddressBookExceptions;

import java.io.File;
import java.io.IOException;

public interface BookBehaviorDefinition
{
   public boolean newAddressBook(String bookName) throws AddressBookExceptions;
   public boolean addData(File filename) throws IOException, AddressBookExceptions;
   public void  openFile(File FileName);
   public void saveAs(File FileName);
   public void ReadFromFile(File FileName);
}
