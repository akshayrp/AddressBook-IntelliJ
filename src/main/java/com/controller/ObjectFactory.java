package com.controller;

import com.Exceptions.AddressBookExceptions;
import com.google.gson.Gson;
import com.model.AddressBook;
import com.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory
{
   Gson gson = new Gson();
   Person personData = new Person();
   AddressBook bookData = new AddressBook();
   AddressBookExceptions addressBookExceptions = new AddressBookExceptions();
   String filePath = "/home/admin1/IdeaProjects/AddressBook/src/main/java/com/JsonFiles";
   List<Person> personList = new ArrayList<Person>();

}
