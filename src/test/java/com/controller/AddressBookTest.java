package com.controller;

import com.Exceptions.AddressBookExceptions;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;


public class AddressBookTest
{
   BookBehaviorDefinition behavior = new BookBehaviorImplementation();


   @Test
   public void givenNewFileName_WhenCorrect_CreatesNewJsonFile() throws AddressBookExceptions
   {
      Assert.assertTrue(behavior.newAddressBook("firstFile"));
   }

   @Test
   public void givenNewFileName_WhenStringNull_throwsException() throws AddressBookExceptions
   {
      Assert.assertFalse(behavior.newAddressBook(null));
   }

   @Test
   public void givenNewFileName_WhenStringEmpty_throwsException() throws AddressBookExceptions
   {
      Assert.assertFalse(behavior.newAddressBook(""));
   }

   @Test
   public void givenFileNameAndData_WhenCorrectAndExist_AddDataIntoFile() throws AddressBookExceptions, FileNotFoundException
   {
      Assert.assertTrue(behavior.addData(
            "firstFile",
            "Akshay",
            "Patwari",
            "Kothrud",
            "Pune",
            "Maharashtra",
            0114520,
            789456123));
   }

   @Test
   public void givenFileNameAndData_WhenDataNull_DoesNotAcceptData() throws AddressBookExceptions, FileNotFoundException
   {
      Assert.assertFalse(behavior.addData("firstFile",null,null,null,null,null,0,0));
   }

   @Test
   public void givenFileName_WhenExist_OpensFileToReadData() throws AddressBookExceptions, FileNotFoundException
   {
      Assert.assertTrue(behavior.openFile("firstFile"));
   }

   @Test
   public void givenFileName_WhenDoNotExist_HandlesException() throws AddressBookExceptions, FileNotFoundException
   {
      Assert.assertFalse(behavior.openFile("abc"));
   }

   @Test
   public void givenOldFileNameAndNewFileName_WhenCorrect_RenameFile()
   {
      Assert.assertTrue(behavior.saveAs("firstFile","newFile"));
   }

   @Test
   public void givenOldFileNameAndNewFileName_WhenDoesNotExist_RenameFile()
   {
      Assert.assertFalse(behavior.saveAs("dsfg","newFile"));
   }

   @Test
   public void givenOldFileNameAndNewFileName_WhenBothOrOneEmpty_DoesNotRenamesFile()
   {
      Assert.assertFalse(behavior.saveAs("",""));
   }

   @Test
   public void givenFileNameAndFieldNameAndFieldValue_WhenCorrect_ReplaceFieldValue() throws AddressBookExceptions, IOException
   {
      Assert.assertEquals("Mumbai",behavior.editData("firstFile",789456123,"City","Mumbai"));
   }

   @Test
   public void givenFileNameAndFieldNameAndFieldValue_WhenFieldNameIncorrect_DoesNotReplaceFieldValue() throws IOException, AddressBookExceptions
   {
      Assert.assertNotEquals("Mumbai",behavior.editData("firstFile",789456123,"city","Mumbai"));

   }

   @Test
   public void givenFileNameAndUniqueMobileNumber_WhenExists_DeletesTheData() throws IOException, AddressBookExceptions
   {
      Assert.assertTrue(behavior.deleteData("firstFile",789456123));
   }

   @Test
   public void givenFileNameAndMobileNumber_WhenDoesNotExist_DeletesNothing() throws IOException, AddressBookExceptions
   {
      Assert.assertFalse(behavior.deleteData("firstFile",789456783));
   }
}
