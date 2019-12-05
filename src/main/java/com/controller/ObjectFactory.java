package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.google.gson.Gson;
import com.model.AddressBook;
import com.model.Person;

public class ObjectFactory
{
   Gson gson = new Gson();
   Person personData = new Person();
   AddressBook bookData = new AddressBook();
   AddressBookExceptions addressBookExceptions = new AddressBookExceptions();
}
