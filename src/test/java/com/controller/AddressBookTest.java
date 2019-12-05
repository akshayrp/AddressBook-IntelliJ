package com.controller;

import org.junit.Assert;
import org.junit.Test;


public class AddressBookTest
{
   AddressBookServices service = new AddressBookServices();

   @Test
   public void givenNewFileName_WhenCorrect_CreatesNewJsonFile()
   {
      Assert.assertTrue(service.newAddressBook("firstFile"));
   }

   @Test
   public void givenNewFileName_WhenStringNull_throwsException()
   {
      Assert.assertFalse(service.newAddressBook(null));
   }
}
