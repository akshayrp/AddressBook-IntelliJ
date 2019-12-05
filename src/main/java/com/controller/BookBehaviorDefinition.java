package com.controller;

import java.io.File;
import java.io.IOException;

public interface BookBehaviorDefinition
{
   public boolean newAddressBook(String bookName);
   public void addData(File filename) throws IOException;
   public void  openFile(File FileName);
   public void saveAs(File FileName);
   public void ReadFromFile(File FileName);
}
