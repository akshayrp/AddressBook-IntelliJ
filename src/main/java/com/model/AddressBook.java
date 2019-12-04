package com.model;

import java.util.List;

public class AddressBook
{

   private List<Person> personsList;

   public List<Person> getPersonsList()
   {
      return personsList;
   }

   public void setPersonsList(List<Person> personsList)
   {
      this.personsList = personsList;
   }
}
