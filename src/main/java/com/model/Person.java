package com.model;

public class Person
{

   private String firstName,lastName,address,city,state;
   private int zip;
   private long phoneNumber;

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public int getZip()
   {
      return zip;
   }

   public void setZip(int zip)
   {
      this.zip = zip;
   }

   public long getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(long phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }

}
