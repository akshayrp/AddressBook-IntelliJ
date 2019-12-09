package com.Exceptions;

public class
AddressBookExceptions extends Exception
{
   public exceptionType exceptionType;


   public AddressBookExceptions()
   {
   }

   public enum exceptionType
   {
      NULL_FILENAME, IO_EXCEPTION, NO_SUCH_FIELD, CANNOT_ACCESS_FILE_DATA, FILE_NOT_FOUND
   }

   public AddressBookExceptions(exceptionType type, String message)
   {
      super(message);
      this.exceptionType = type;
   }
}
