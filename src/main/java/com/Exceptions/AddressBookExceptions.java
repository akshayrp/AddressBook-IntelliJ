package com.Exceptions;

public class AddressBookExceptions extends Exception
{
   public exceptionType exceptionType;

   public enum exceptionType
   {
      NULL_FILENAME, IO_EXCEPTION, EMPTY_FILE, FILE_NOT_FOUND
   }

   public AddressBookExceptions()
   {

   }

   public AddressBookExceptions(exceptionType type, String message)
   {
      super(message);
      this.exceptionType = type;
   }
}
