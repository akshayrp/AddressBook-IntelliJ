package com.controller;

import java.io.File;

public interface BookBehaviorDefinition
{
   public void addData(File filename);
   public void  openFile(File FileName);
   public void saveAs(File FileName);
   public void ReadFromFile(File FileName);
}
