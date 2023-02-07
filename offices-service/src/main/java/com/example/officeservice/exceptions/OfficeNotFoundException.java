package com.example.officeservice.exceptions;

public class OfficeNotFoundException extends Exception {


    public  OfficeNotFoundException (Long officeId) {
        super("Office with Id : "+officeId+" Not Found");
    }
}
