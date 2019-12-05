package com.controller;

import com.Exceptions.AddressBookExceptions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;



public class AddressBookTest
{
   AddressBookServices service = new AddressBookServices();


   @Test
   public void givenNewFileName_WhenCorrect_CreatesNewJsonFile() throws AddressBookExceptions
   {
      Assert.assertTrue(service.newAddressBook("FirstFile"));
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
   public void givenFileNameAndData_WhenCorrectAndExist_AddDataIntoFile() throws AddressBookExceptions
   {
      Assert.assertTrue(service.addData("FirstFile","Akshay","Patwari","Kothrud","Pune","Maharashtra",0114520,789456123));
   }

   @Test
   public void givenFileNameAndData_WhenDataNull_DoesNotAcceptData() throws AddressBookExceptions
   {
      Assert.assertFalse(service.addData("firstFile",null,null,null,null,null,0,0));
   }


}
