package com.controller;

import com.Exceptions.AddressBookExceptions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileNotFoundException;


public class AddressBookTest
{
   AddressBookServices service = new AddressBookServices();


   @Test
   public void givenNewFileName_WhenCorrect_CreatesNewJsonFile() throws AddressBookExceptions
   {
      Assert.assertTrue(service.newAddressBook("firstFile"));
   }

   @Test
   public void givenNewFileName_WhenStringNull_throwsException() throws AddressBookExceptions
   {
      Assert.assertFalse(service.newAddressBook(null));
   }

   @Test
   public void givenNewFileName_WhenStringEmpty_throwsException() throws AddressBookExceptions
   {
      Assert.assertFalse(service.newAddressBook(""));
   }

   @Test
   public void givenFileNameAndData_WhenCorrectAndExist_AddDataIntoFile() throws AddressBookExceptions, FileNotFoundException
   {
      Assert.assertTrue(service.addData("firstFile","Akshay","Patwari","Kothrud","Pune","Maharashtra",0114520,789456123));
   }

   @Test
   public void givenFileNameAndData_WhenDataNull_DoesNotAcceptData() throws AddressBookExceptions, FileNotFoundException
   {
      Assert.assertFalse(service.addData("firstFile",null,null,null,null,null,0,0));
   }

   @Test
   public void givenFileName_WhenExist_OpensFileToReadData() throws AddressBookExceptions
   {
      Assert.assertTrue(service.openFile("FirstFile"));
   }

   @Test
   public void givenFileName_WhenDoNotExist_HandlesException() throws AddressBookExceptions
   {
      Assert.assertFalse(service.openFile("abc"));
   }
}
